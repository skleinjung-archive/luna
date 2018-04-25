package com.thrashplay.luna;

/**
 * @author Sean Kleinjung
 */
public class LunaException extends RuntimeException {
    public LunaException() {
    }

    public LunaException(String message) {
        super(message);
    }

    public LunaException(String message, Throwable cause) {
        super(message, cause);
    }

    public LunaException(Throwable cause) {
        super(cause);
    }
}
