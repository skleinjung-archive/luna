package com.thrashplay.luna.geom;

/**
 * TODO: Add class documentation
 *
 * @author Sean Kleinjung
 */
public class Rectangle {
    private static final Rectangle nonIntersectingRectangle = new Rectangle(0, 0, 0, 0);

    private int left;
    private int top;
    private int width;
    private int height;

    public Rectangle() {
        this(0, 0, 0, 0);
    }

    public Rectangle(int left, int top, int width, int height) {
        setRect(left, top, width, height);
    }

    public void setX(int x) {
        left = x;
    }

    public int getX() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getLeft() {
        return left;
    }

    public void setRight(int right) {
        this.width = right - left + 1;
    }

    public int getRight() {
        return left + width - 1;
    }

    public void setY(int y) {
        top = y;
    }

    public int getY() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getTop() {
        return top;
    }

    public void setBottom(int bottom) {
        height = bottom - top + 1;
    }

    public int getBottom() {
        return top + height - 1;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getCenterY() {
        return getTop() + getHeight() / 2;
    }

    public int getCenterX() {
        return getLeft() + getWidth() / 2;
    }

    public void setRect(int left, int top, int width, int height) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    public boolean intersects(Rectangle other) {
        return left < other.left + other.getWidth()
                && left + getWidth() > other.left
                && getTop() < other.getTop() + other.getHeight()
                && getTop() + getHeight() > other.getTop();
    }

    /**
     * Returns true if this rectangle completely contains the other rectangle.
     * <p>
     *     In case the two rectangles describe an equal area, this method will return true.
     * </p>
     * @param other the rectangle to test
     * @return true if this rectangle contains the other, otherwise false
     */
    public boolean contains(Rectangle other) {
        return other.left >= left &&
                other.getRight() <= getRight() &&
                other.getTop() >= getTop() &&
                other.getBottom() <= getBottom();
    }

    /**
     * Returns the intersection of this rectangle with another.
     * <p>
     *     This method may return a reference to <em>this</em> or <em>other</em> if one of those rectangles perfectly
     *     defines the intersection.
     * </p>
     * <p>
     *     The result will have a width and height of zero if the rectangles do not intersect. In this case, the X and
     *     Y coordinates are undefined
     * </p>
     * @param other the rectangle to intersect with this one
     * @return a rectangle defining the intersection
     */
    public Rectangle getIntersection(Rectangle other) {
        if (!intersects(other)) {
            return nonIntersectingRectangle;
        }

        // if one rectangle completely contains the other, return a reference to the smaller one
        if (contains(other)) {
            return other;
        } else if (other.contains(this)) {
            return this;
        }

        // if we reach this point, the rectangles intersect but neither contains the other
        int left = Math.max(this.left, other.left);
        int right = Math.min(getRight(), other.getRight());
        int top = Math.max(getTop(), other.getTop());
        int bottom = Math.min(getBottom(), other.getBottom());
        return new Rectangle(left, top, right - left + 1, bottom - top + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rectangle rectangle = (Rectangle) o;

        if (height != rectangle.height) return false;
        if (left != rectangle.left) return false;
        if (top != rectangle.top) return false;
        if (width != rectangle.width) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = left;
        result = 31 * result + top;
        result = 31 * result + width;
        result = 31 * result + height;
        return result;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "left=" + left +
                ", top=" + top +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
