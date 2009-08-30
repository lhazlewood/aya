package com.anjinllc.aya.io.support;

import com.anjinllc.aya.io.DataException;
import com.anjinllc.aya.io.UncategorizedDataException;

import java.io.IOException;

/**
 * @author Bryan Turner
 * @since 0.1
 */
public abstract class AbstractFallbackIOExceptionTranslator implements IOExceptionTranslator {
    private IOExceptionTranslator fallbackTranslator;

    protected AbstractFallbackIOExceptionTranslator() {

    }

    public IOExceptionTranslator getFallbackTranslator() {
        return fallbackTranslator;
    }

    public void setFallbackTranslator(IOExceptionTranslator fallbackTranslator) {
        this.fallbackTranslator = fallbackTranslator;
    }

    public DataException translate(String operation, IOException e) {
        if (e == null) {
            throw new NullPointerException("e");
        }

        DataException translated = doTranslate(operation, e);
        if (translated != null) {
            return translated;
        }

        IOExceptionTranslator fallback = getFallbackTranslator();
        if (fallback != null) {
            translated = getFallbackTranslator().translate(operation, e);
        }

        if (translated == null) {
            translated = new UncategorizedDataException(e, operation);
        }
        return translated;
    }
    
    protected abstract DataException doTranslate(String operation, IOException e);
}
