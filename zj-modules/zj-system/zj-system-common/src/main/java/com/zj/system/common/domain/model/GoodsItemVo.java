package com.zj.system.common.domain.model;

import com.zj.system.common.domain.GoodsEntity;
import com.zj.system.common.domain.GoodsTypeEntity;
import lombok.Data;

/**
 * @author zj
 * @create 2022-08-29 9:47
 */
@Data
public class GoodsItemVo {

    private GoodsEntity goodsEntity;

    private GoodsTypeEntity goodsTypeEntity;
}
