package com.iotend.uid.service;

/**
 * Represents a worker id assigner for {@link com.baidu.fsg.uid.impl.DefaultUidGenerator}
 *
 * @author yutianbao
 */
public interface WorkerIdAssigner {

    /**
     * Assign worker id for {@link com.baidu.fsg.uid.impl.DefaultUidGenerator}
     *
     * @return assigned worker id
     */
    long assignWorkerId();

}
