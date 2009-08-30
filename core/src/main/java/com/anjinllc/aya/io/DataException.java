package com.anjinllc.aya.io;

import com.anjinllc.aya.core.AyaException;

/**
 * @author Bryan Turner
 * @since 0.1
 */
public class DataException extends AyaException {
    public DataException(String message) {
        super(message);
    }

    public DataException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataException(Throwable cause) {
        super(cause);
    }
}
