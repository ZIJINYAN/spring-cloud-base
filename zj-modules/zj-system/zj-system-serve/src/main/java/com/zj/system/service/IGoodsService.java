package com.zj.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zj.system.common.domain.GoodsEntity;
import com.zj.system.common.domain.model.GoodsItemVo;
import com.zj.system.common.domain.request.GoodsSearchVo;

import java.util.List;

/**
 * @author zj
 * @create 2022-08-28 20:46
 */
public interface IGoodsService extends IService<GoodsEntity> {

    List<GoodsItemVo> goodsList(GoodsSearchVo goodsSearchVo);
}
