package com.thrashplay.luna.util;

/**
 * @author Sean Kleinjung
 */
public class Assert {
    public static void notNull(Object value, String name) {
        if (value == null) {
            throw new IllegalArgumentException(String.format("%s cannot be null", name));
        }
    }
}
