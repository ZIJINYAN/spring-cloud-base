package com.zj.system.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.zj.system.common.domain.GoodsEntity;
import com.zj.system.common.domain.model.GoodsItemVo;
import com.zj.system.common.domain.request.GoodsSearchVo;

import java.util.List;

/**
 * @author zj
 * @create 2022-08-28 20:47
 */

public interface GoodsMapper extends MPJBaseMapper<GoodsEntity> {
    List<GoodsItemVo> goodsList(GoodsSearchVo goodsSearchVo);
}
