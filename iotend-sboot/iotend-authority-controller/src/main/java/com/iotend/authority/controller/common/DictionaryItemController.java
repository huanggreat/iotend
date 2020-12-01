package com.iotend.authority.controller.common;


import com.iotend.authority.dto.common.DictionaryItemSaveDTO;
import com.iotend.authority.dto.common.DictionaryItemUpdateDTO;
import com.iotend.authority.entity.common.DictionaryItem;
import com.iotend.authority.service.common.DictionaryItemService;
import com.iotend.base.controller.SuperCacheController;
import com.iotend.base.request.PageParams;
import com.iotend.database.mybatis.conditions.query.QueryWrap;
import com.iotend.security.annotation.PreAuth;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 字典项
 * </p>
 *
 * @author huang
 * @date 2019-07-22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/dictionaryItem")
@Api(value = "DictionaryItem", tags = "字典项")
@PreAuth(replace = "dict:")
public class DictionaryItemController extends SuperCacheController<DictionaryItemService, Long, DictionaryItem, DictionaryItem, DictionaryItemSaveDTO, DictionaryItemUpdateDTO> {
    @Override
    public QueryWrap<DictionaryItem> handlerWrapper(DictionaryItem model, PageParams<DictionaryItem> params) {
        QueryWrap<DictionaryItem> wrapper = super.handlerWrapper(model, params);
        wrapper.lambda().ignore(DictionaryItem::setDictionaryType)
                .eq(DictionaryItem::getDictionaryType, model.getDictionaryType());
        return wrapper;
    }

}
