package com.anjinllc.aya.io;

import java.io.Closeable;
import java.io.Flushable;

/**
 * Describes an output sink to which bytes can be written.
 *
 * TODO: Incomplete. This initial starting point is patterned after OutputStream
 *
 * @author Bryan Turner
 * @since 0.1
 */
public interface DataSink extends Closeable, Flushable {
    public void write(byte[] b);

    public void write(byte[] b, int offset, int length);
}
