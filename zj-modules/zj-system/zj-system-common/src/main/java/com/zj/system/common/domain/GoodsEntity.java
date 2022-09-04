package com.zj.system.common.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zj
 * @create 2022-08-28 20:25
 */
@Data
@TableName("system_goods")
public class GoodsEntity {

    /**
     * 商品ID
     */
    @TableId(type = IdType.AUTO)
    private Integer goodsId;
    /**
     * 商品编号
     */
    private String goodsCode;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品类型
     */
    private Integer goodsType;
    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;
    /**
     * 商品描述
     */
    private String goodsDesc;
    /**
     * 商品销量
     */
    private Integer goodsSales;
    /**
     * 商品状态
     */
    private String goodsStatus;


}
