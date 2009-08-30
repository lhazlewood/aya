package com.anjinllc.aya.net;

import com.anjinllc.aya.core.Connection;
import com.anjinllc.aya.core.ConnectionCallback;
import com.anjinllc.aya.core.Connector;
import com.anjinllc.aya.core.ConnectorController;
import com.anjinllc.aya.io.core.IOCallback;
import com.anjinllc.aya.io.core.VoidIOCallback;
import com.anjinllc.aya.io.core.support.IOSupport;
import com.anjinllc.aya.support.Destroyable;
import com.anjinllc.aya.support.Initializable;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;

/**
 * @author Bryan Turner
 * @since 0.1 
 */
public class SocketConnector extends IOSupport implements Connector, Destroyable, Initializable {
    private ConnectorController controller;
    private ExecutorService executorService;
    private ServerSocket serverSocket;

    private int port;

    public SocketConnector() {
        controller = new ConnectorController() {
            private volatile boolean stopped;

            public boolean isStopped() {
                return stopped;
            }

            public void stop() {
                stopped = true;
            }
        };
        port = 8080;
    }

    public Connection accept() {
        return execute("accept", new IOCallback<Connection>() {
            public Connection doWithIO() throws IOException {
                return new SocketConnection(SocketConnector.this, serverSocket.accept());
            }
        });
    }

    public ConnectorController accept(final ConnectionCallback callback) {
        executorService.submit(new Runnable() {
            public void run() {
                callback.doWithConnection(accept());
            }
        });
        return getController();
    }

    public ConnectorController getController() {
        return controller;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void destroy() {
        getController().stop();

        execute("destroy", new VoidIOCallback() {
            @Override
            protected void doIO() throws IOException {
                serverSocket.close();
            }
        });
    }

    public void init() {
        execute("init", new VoidIOCallback() {
            @Override
            protected void doIO() throws IOException {
                serverSocket = new ServerSocket(port);
            }
        });
    }
}
