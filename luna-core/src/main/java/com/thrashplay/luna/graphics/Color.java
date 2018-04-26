package com.thrashplay.luna.graphics;

import com.thrashplay.luna.LunaException;

/**
 * @author Sean Kleinjung
 */
public class Color {
    public static int pack(int alpha, int red, int green, int blue) {
        checkRange("alpha", alpha);
        checkRange("red", red);
        checkRange("green", green);
        checkRange("blue", blue);

        return alpha << 24 | red << 16 | green << 8 | blue;
    }

    private static void checkRange(String whichComponent, int value) {
        if (value < 0 || value > 255) {
            throw new LunaException(String.format("Color component '%s' must be >= 0 and <= 255 (was %d)", whichComponent, value));
        }
    }
}
