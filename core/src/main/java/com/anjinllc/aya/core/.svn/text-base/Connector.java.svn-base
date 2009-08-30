package com.anjinllc.aya.core;

import java.util.concurrent.ExecutorService;

/**
 * @author Bryan Turner
 * @author Les Hazlewood
 * @author Allan Ditzel
 * @since 0.1
 */
public interface Connector {
    public Connection accept();

    public ConnectorController accept(ConnectionCallback callback);

    public ConnectorController getController();

    public void setExecutorService(ExecutorService executorService);
}
