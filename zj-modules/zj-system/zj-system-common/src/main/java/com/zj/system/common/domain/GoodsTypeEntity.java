package com.zj.system.common.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zj
 * @create 2022-08-28 20:24
 */
@TableName("system_goods_type")
@Data
public class GoodsTypeEntity {
    /**
     * 商品类型ID
     */
    @TableId
    private Integer goodsTypeId;
    /**
     * 商品类型名称
     */
    private String goodsTypeName;

}
