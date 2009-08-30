package com.anjinllc.aya.io.core;

import com.anjinllc.aya.io.support.IOAccessor;

import java.io.IOException;

/**
 * @author Bryan Turner
 * @since 0.1
 */
public class IOTemplate extends IOAccessor implements IOOperations {
    public IOTemplate() {

    }

    public <T> T execute(IOCallback<T> callback) {
        return execute("execute", callback);
    }

    public <T> T execute(String operation, IOCallback<T> callback) {
        try {
            return callback.doWithIO();
        } catch (IOException e) {
            throw getIOExceptionTranslator().translate(operation, e);
        }
    }
}
