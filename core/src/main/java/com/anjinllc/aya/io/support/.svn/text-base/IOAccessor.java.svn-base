package com.anjinllc.aya.io.support;

/**
 * @author Bryan Turner
 * @since 0.1
 */
public abstract class IOAccessor implements IOExceptionTranslatorAware {
    private IOExceptionTranslator translator;

    public IOAccessor() {

    }

    public IOExceptionTranslator getIOExceptionTranslator() {
        if (translator == null) {
            translator = new SubclassIOExceptionTranslator();
        }
        return translator;
    }

    public void setIOExceptionTranslator(IOExceptionTranslator translator) {
        this.translator = translator;
    }
}
