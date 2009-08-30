package com.anjinllc.aya.io;

import com.anjinllc.aya.io.core.VoidIOCallback;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Bryan Turner
 * @since 0.1
 */
public class OutputStreamDataSink extends DataComponent implements DataSink {
    private final OutputStream stream;

    public OutputStreamDataSink(OutputStream stream) {
        if (stream == null) {
            throw new NullPointerException("stream");
        }
        this.stream = stream;
    }

    public void close() throws IOException {
        stream.close();
    }

    public void flush() throws IOException {
        stream.flush();
    }

    public void write(final byte[] b) {
        execute(new VoidIOCallback() {
            protected void doIO() throws IOException {
                stream.write(b);
            }
        });
    }

    public void write(final byte[] b, final int offset, final int length) {
        execute(new VoidIOCallback() {
            protected void doIO() throws IOException {
                stream.write(b, offset, length);
            }
        });
    }
}
