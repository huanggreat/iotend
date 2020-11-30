package com.iotend.authority.dao.auth;

import com.iotend.authority.dto.auth.ResourceQueryDTO;
import com.iotend.authority.entity.auth.Resource;
import com.iotend.base.mapper.SuperMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 资源
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Repository
public interface ResourceMapper extends SuperMapper<Resource> {
    /**
     * 查询 拥有的资源
     *
     * @param resource
     * @return
     */
    List<Resource> findVisibleResource(ResourceQueryDTO resource);

    /**
     * 根据唯一索引 保存或修改资源
     *
     * @param resource
     * @return
     */
    int saveOrUpdateUnique(Resource resource);

    List<Long> findMenuIdByResourceId(@Param("resourceIdList") List<Long> resourceIdList);
}
