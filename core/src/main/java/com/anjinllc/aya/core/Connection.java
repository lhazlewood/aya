package com.anjinllc.aya.core;

import com.anjinllc.aya.io.DataPump;
import com.anjinllc.aya.io.DataSink;

/**
 * @author Bryan Turner
 * @author Les Hazlewood
 * @author Allan Ditzel
 * @since 0.1
 */
public interface Connection {
    public Connector getConnector();

    public DataPump getDataPump();

    public DataSink getDataSink();
}
