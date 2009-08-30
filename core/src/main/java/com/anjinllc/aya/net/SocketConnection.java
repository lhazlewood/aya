package com.anjinllc.aya.net;

import com.anjinllc.aya.core.Connector;
import com.anjinllc.aya.io.DataPump;
import com.anjinllc.aya.io.DataSink;
import com.anjinllc.aya.io.InputStreamDataPump;
import com.anjinllc.aya.io.OutputStreamDataSink;
import com.anjinllc.aya.io.core.IOCallback;
import com.anjinllc.aya.io.core.support.IOSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;

/**
 * TODO - Class JavaDoc
 *
 * @author Les Hazlewood
 * @author Bryan Turner
 * @since 0.1
 */
public class SocketConnection extends IOSupport implements NetworkConnection, Runnable {
    private static final Logger log = LoggerFactory.getLogger(SocketConnection.class);

    private SocketConnector connector;
    private Socket socket;

    public SocketConnection(SocketConnector connector, Socket socket) {
        this.connector = connector;
        this.socket = socket;
    }

    public Connector getConnector() {
        return connector;
    }

    public DataPump getDataPump() {
        return execute("getDataPump", new IOCallback<DataPump>() {
            public DataPump doWithIO() throws IOException {
                return new InputStreamDataPump(socket.getInputStream());
            }
        });
    }

    public DataSink getDataSink() {
        return execute("getDataSink", new IOCallback<DataSink>() {
            public DataSink doWithIO() throws IOException {
                return new OutputStreamDataSink(socket.getOutputStream());
            }
        });
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void run() {
        try {
            DataPump pump = getDataPump();

            DataSink sink = getDataSink();
            long time = System.currentTimeMillis();
            sink.write(("HTTP/1.1 200 OK\n\nAya Web Server - " + time + "\n").getBytes());
            sink.close();

            pump.close();
        } catch (IOException e) {
            log.error("IO error encountered while processing.", e);
        }
    }
}
