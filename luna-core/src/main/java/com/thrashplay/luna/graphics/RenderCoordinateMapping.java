package com.thrashplay.luna.graphics;


import com.thrashplay.luna.geom.Point;
import com.thrashplay.luna.geom.Rectangle;

/**
 * Helper class providing calculations used to map the coordinates of a logical scene being rendered to and from the
 * physical device coordinates.
 *
 * @author Sean Kleinjung
 */
public class RenderCoordinateMapping {
    private int sceneWidth;
    private int sceneHeight;
    private int screenWidth;
    private int screenHeight;

    private Rectangle sceneBoundsInScreenCoordinates;

    public RenderCoordinateMapping(int sceneWidth, int sceneHeight, int screenWidth, int screenHeight) {
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public static Point convertScreenCoordinateToSceneCoordinate(Point destination, int inputX, int inputY, int screenWidth, int screenHeight, int sceneWidth, int sceneHeight) {
        float xScale = (float) screenWidth / sceneWidth;
        float yScale = (float) screenHeight / sceneHeight;
        float scale = Math.min(xScale, yScale);
        float scaledWidth = sceneWidth * scale;
        float scaledHeight = sceneHeight * scale;

        int x, y;
        if (xScale < yScale) {
            y = (int) ((screenHeight - scaledHeight) / 2);
            x = 0;
        } else {
            x = (int) ((screenWidth - scaledWidth) / 2);
            y = 0;
        }

        destination.set((int) ((inputX - x) / scale), (int) ((inputY - y) / scale));
        return destination;
    }

    public Rectangle getSceneBoundsInScreenCoordinates() {
        if (sceneBoundsInScreenCoordinates == null) {
            float xScale = (float) screenWidth / sceneWidth;
            float yScale = (float) screenHeight / sceneHeight;
            float scale = getScale();
            float scaledWidth = sceneWidth * scale;
            float scaledHeight = sceneHeight * scale;
            if (xScale < yScale) {
                int y = (int) ((screenHeight - scaledHeight) / 2);
                sceneBoundsInScreenCoordinates = new Rectangle(0, y, (int) scaledWidth, (int) scaledHeight);
            } else {
                int x = (int) ((screenWidth - scaledWidth) / 2);
                sceneBoundsInScreenCoordinates = new Rectangle(x, 0, (int) scaledWidth, (int) scaledHeight);
            }
        }

        return sceneBoundsInScreenCoordinates;
    }

    public Point convertScreenCoordinateToSceneCoordinate(Point pointToConvert, Point destination) {
        float scale = getScale();
        Rectangle bounds = getSceneBoundsInScreenCoordinates();
        destination.set((int) ((pointToConvert.getX() - bounds.getLeft()) / scale),
                (int) ((pointToConvert.getY() - bounds.getTop()) / scale));
        return destination;

    }

    public Point convertScreenCoordinateToSceneCoordinate(Point pointToConvert) {
        return convertScreenCoordinateToSceneCoordinate(pointToConvert, new Point());
    }

    private float getScale() {
        float xScale = (float) screenWidth / sceneWidth;
        float yScale = (float) screenHeight / sceneHeight;
        return Math.min(xScale, yScale);
    }
}
