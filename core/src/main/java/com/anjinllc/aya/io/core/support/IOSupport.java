package com.anjinllc.aya.io.core.support;

import com.anjinllc.aya.io.core.IOCallback;
import com.anjinllc.aya.io.core.IOOperations;
import com.anjinllc.aya.io.core.IOTemplate;

/**
 * @author Bryan Turner
 * @since 0.1
 */
public class IOSupport implements IOOperations {
    private IOTemplate template;

    public IOSupport() {

    }

    public IOSupport(IOTemplate template) {
        this.template = template;
    }

    public IOTemplate getIOTemplate() {
        if (template == null) {
            template = new IOTemplate();
        }
        return template;
    }

    public void setIOTemplate(IOTemplate template) {
        this.template = template;
    }

    public <T> T execute(IOCallback<T> callback) {
        return getIOTemplate().execute(callback);
    }

    public <T> T execute(String operation, IOCallback<T> callback) {
        return getIOTemplate().execute(operation, callback);
    }
}
