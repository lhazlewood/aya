package com.anjinllc.aya.io;

import java.io.Closeable;

/**
 * Describes an input pump from which bytes can be read.
 *
 * TODO: Incomplete. This initial starting point is patterned after InputStream
 *
 * @author Bryan Turner
 * @since 0.1
 */
public interface DataPump extends Closeable {
    public int available();

    public void mark(int limit);

    public boolean markSupported();

    public int read(byte[] b);

    public int read(byte[] b, int offset, int length);

    public void reset();

    public long skip(long n);
}
