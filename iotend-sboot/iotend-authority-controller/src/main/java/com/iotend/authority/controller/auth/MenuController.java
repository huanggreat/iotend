package com.iotend.authority.controller.auth;

import com.iotend.authority.dto.auth.MenuSaveDTO;
import com.iotend.authority.dto.auth.MenuUpdateDTO;
import com.iotend.authority.entity.auth.Menu;
import com.iotend.authority.service.auth.MenuService;
import com.iotend.base.R;
import com.iotend.base.controller.SuperCacheController;
import com.iotend.database.mybatis.conditions.Wraps;
import com.iotend.log.annotation.SysLog;
import com.iotend.security.annotation.PreAuth;
import com.iotend.utils.BeanPlusUtil;
import com.iotend.utils.TreeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 前端控制器
 * 菜单
 * </p>
 *
 * @author huang
 * @date 2019-07-22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/menu")
@Api(value = "Menu", tags = "菜单")
@PreAuth(replace = "menu:")
public class MenuController extends SuperCacheController<MenuService, Long, Menu, Menu, MenuSaveDTO, MenuUpdateDTO> {

    @Override
    public R<Menu> handlerSave(MenuSaveDTO menuSaveDTO) {
        Menu menu = BeanPlusUtil.toBean(menuSaveDTO, Menu.class);
        baseService.saveWithCache(menu);
        return success(menu);
    }

    @Override
    public R<Menu> handlerUpdate(MenuUpdateDTO model) {
        Menu menu = BeanPlusUtil.toBean(model, Menu.class);
        baseService.updateWithCache(menu);
        return success(menu);
    }

    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        baseService.removeByIdWithCache(ids);
        return success();
    }

    /**
     * 查询系统中所有的的菜单树结构， 不用缓存，因为该接口很少会使用，就算使用，也会管理员维护菜单时使用
     *
     * @return
     */
    @ApiOperation(value = "查询系统所有的菜单", notes = "查询系统所有的菜单")
    @GetMapping("/tree")
    @SysLog("查询系统所有的菜单")
    public R<List<Menu>> allTree() {
        List<Menu> list = baseService.list(Wraps.<Menu>lbQ().orderByAsc(Menu::getSortValue));
        return success(TreeUtil.buildTree(list));
    }
}
