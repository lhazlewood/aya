package com.anjinllc.aya.io;

import com.anjinllc.aya.io.core.IOTemplate;
import com.anjinllc.aya.io.core.support.IOSupport;

/**
 * @author Bryan Turner
 * @since 0.1
 */
public abstract class DataComponent extends IOSupport {
    protected DataComponent() {

    }

    protected DataComponent(IOTemplate template) {
        super(template);
    }
}
