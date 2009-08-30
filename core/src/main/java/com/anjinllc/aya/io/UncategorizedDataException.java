package com.anjinllc.aya.io;

/**
 * {@link DataException} thrown when an unexpected, uncategorized form of exception is thrown while interacting with
 * a {@link DataPump} or a {@link DataSink}.
 *
 * @author Bryan Turner
 * @since 0.1
 */
public class UncategorizedDataException extends DataException {
    private final String operation;

    public UncategorizedDataException(Throwable cause, String operation) {
        super(cause);
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}
