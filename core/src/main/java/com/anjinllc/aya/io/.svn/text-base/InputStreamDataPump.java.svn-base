package com.anjinllc.aya.io;

import com.anjinllc.aya.io.core.IOCallback;
import com.anjinllc.aya.io.core.VoidIOCallback;

import java.io.IOException;
import java.io.InputStream;

/**
 * A {@link DataPump} implementation wrapping a standard Java {@code InputStream}. Any {@code IOException}s thrown
 * while interacting with the stream are translated via
 *
 * @author Bryan Turner
 * @since 0.1
 */
public class InputStreamDataPump extends DataComponent implements DataPump {
    private final InputStream stream;

    public InputStreamDataPump(InputStream stream) {
        if (stream == null) {
            throw new NullPointerException("stream");
        }
        this.stream = stream;
    }

    public int available() {
        return execute("available", new IOCallback<Integer>() {
            public Integer doWithIO() throws IOException {
                return stream.read();
            }
        });
    }

    public void close() throws IOException {
        stream.close();
    }

    public void mark(int limit) {
        stream.mark(limit);
    }

    public boolean markSupported() {
        return stream.markSupported();
    }

    public int read(final byte[] b) {
        return execute("read", new IOCallback<Integer>() {
            public Integer doWithIO() throws IOException {
                return stream.read(b);
            }
        });
    }

    public int read(final byte[] b, final int offset, final int length) {
        return execute("read", new IOCallback<Integer>() {
            public Integer doWithIO() throws IOException {
                return stream.read(b, offset, length);
            }
        });
    }

    public void reset() {
        execute("reset", new VoidIOCallback() {
            protected void doIO() throws IOException {
                stream.reset();
            }
        });
    }

    public long skip(final long n) {
        return execute("skip", new IOCallback<Long>() {
            public Long doWithIO() throws IOException {
                return stream.skip(n);
            }
        });
    }
}
