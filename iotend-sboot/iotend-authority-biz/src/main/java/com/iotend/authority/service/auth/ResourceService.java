package com.iotend.authority.service.auth;

import com.iotend.authority.dto.auth.ResourceQueryDTO;
import com.iotend.authority.entity.auth.Resource;
import com.iotend.base.service.SuperCacheService;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 资源
 * </p>
 *
 * @author huang
 * @date 2019-07-03
 */
public interface ResourceService extends SuperCacheService<Resource> {

    /**
     * 查询 拥有的资源
     *
     * @param resource
     * @return
     */
    List<Resource> findVisibleResource(ResourceQueryDTO resource);

    /**
     * 根据ID删除
     *
     * @param ids
     * @return
     */
    boolean removeByIdWithCache(List<Long> ids);

    /**
     * 保存
     *
     * @param resource
     * @return
     */
    boolean saveWithCache(Resource resource);


    /**
     * 根据菜单id删除资源
     *
     * @param menuIds
     */
    void removeByMenuIdWithCache(List<Long> menuIds);

    /**
     * 根据资源id 查询菜单id
     *
     * @param resourceIdList
     * @return
     */
    List<Long> findMenuIdByResourceId(List<Long> resourceIdList);
}