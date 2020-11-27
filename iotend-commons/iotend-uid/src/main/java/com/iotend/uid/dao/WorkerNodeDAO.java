package com.iotend.uid.dao;

import com.baidu.fsg.uid.worker.entity.WorkerNodeEntity;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * DAO for M_WORKER_NODE
 *
 * @author yutianbao
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface WorkerNodeDAO {

    /**
     * Get {@link WorkerNodeEntity} by node host
     *
     * @param host
     * @param port
     * @return
     */
//    @Select("SELECT ID, HOST_NAME, PORT, TYPE,LAUNCH_DATE, MODIFIED, CREATED FROM WORKER_NODE WHERE HOST_NAME = #{host} AND PORT = #{port}")
//    WorkerNodeEntity getWorkerNodeByHostPort(@Param("host") String host, @Param("port") String port);

    /**
     * Add {@link WorkerNodeEntity}
     *
     * @param workerNodeEntity
     */
    @Insert("INSERT INTO WORKER_NODE(HOST_NAME,PORT, TYPE, LAUNCH_DATE,MODIFIED,CREATED) " +
            "VALUES (#{hostName},#{port},#{type},#{launchDate},NOW(),NOW())")
    void addWorkerNode(WorkerNodeEntity workerNodeEntity);


}

