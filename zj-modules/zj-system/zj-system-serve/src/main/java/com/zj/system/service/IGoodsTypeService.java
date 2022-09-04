package com.zj.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zj.system.common.domain.GoodsTypeEntity;

import java.util.List;

/**
 * @author zj
 * @create 2022-08-28 20:49
 */
public interface IGoodsTypeService extends IService<GoodsTypeEntity> {
    List<GoodsTypeEntity> goodsTypeList();
}

