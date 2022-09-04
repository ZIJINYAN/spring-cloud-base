package com.zj.system.common.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zj
 * @create 2022-09-02 11:33
 */
@Data
public class GoodsItemVo {
    /**
     * 商品类型ID
     */
    private Integer goodsTypeId;
    /**
     * 商品类型名称
     */
    private String goodsTypeName;
    /**
     * 商品ID
     */
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
