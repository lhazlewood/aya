package com.anjinllc.aya.core;

import com.anjinllc.aya.net.SocketConnector;
import com.anjinllc.aya.support.Destroyable;
import com.anjinllc.aya.support.Initializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * TODO - Class JavaDoc
 *
 * @author Les Hazlewood
 * @author Bryan Turner
 * @since 0.1
 */
public class Server implements Destroyable, Initializable, Runnable {
    private static final Logger log = LoggerFactory.getLogger(Server.class);

    private final Object monitor;

    private List<Connector> connectors;
    private ExecutorService pool;

    private int numThreads = 1;

    public Server() {
        monitor = new Object();
    }

    public void addConnector(Connector connector) {
        if (connector == null) {
            throw new NullPointerException("connector");
        }

        if (connectors == null) {
            connectors = new ArrayList<Connector>();
        }
        connectors.add(connector);
    }

    public List<Connector> getConnectors() {
        return connectors;
    }

    public void setConnectors(List<Connector> connectors) {
        this.connectors = connectors;
    }

    public int getNumThreads() {
        return numThreads;
    }

    public void setNumThreads(int numThreads) {
        this.numThreads = numThreads;
    }

    public ExecutorService getPool() {
        return pool;
    }

    public void setPool(ExecutorService pool) {
        this.pool = pool;
    }

    protected void ensurePool() {
        if (this.pool == null) {
            this.pool = Executors.newFixedThreadPool(getNumThreads());
        }
    }

    public void init() {
        ensurePool();

        for (Connector connector : connectors) {
            connector.setExecutorService(getPool());

            if (connector instanceof Initializable) {
                ((Initializable) connector).init();
            }
        }
    }

    public void destroy() {
        destroyPool();

        for (Connector connector : connectors) {
            if (connector instanceof Destroyable) {
                ((Destroyable) connector).destroy();
            }
        }
    }

    protected void destroyPool() {
        ExecutorService pool = getPool();
        if ( pool != null ) {
            destroyPool(pool);
        }
    }

    protected void destroyPool(ExecutorService pool) {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                    log.error("Pool did not terminate.");
                }
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

    public void run() {
        final ExecutorService pool = getPool();

        for (final Connector connector : connectors) {
            connector.accept(new ConnectionCallback() {
                public void doWithConnection(Connection connection) {
                    if (connection instanceof Runnable) {
                        pool.execute((Runnable) connection);
                    }

                    synchronized(monitor) {
                        monitor.notify();
                    }
                }
            });
        }

        synchronized(monitor) {
            try {
                monitor.wait();
            } catch (InterruptedException ignored) {

            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.addConnector(new SocketConnector());
            server.init();
            server.run();
        } finally {
            server.destroy();
        }
    }
}
