package com.anjinllc.aya.io.core;

import java.io.IOException;

/**
 * @author Bryan Turner
 * @since 0.1
 */
public abstract class VoidIOCallback implements IOCallback<Object> {
    protected VoidIOCallback() {

    }

    public Object doWithIO() throws IOException {
        doIO();
        
        return null;
    }

    protected abstract void doIO() throws IOException; 
}
