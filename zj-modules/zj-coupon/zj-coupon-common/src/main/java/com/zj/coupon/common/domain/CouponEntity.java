package com.zj.coupon.common.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zj.common.core.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author zj
 * @create 2022-08-28 20:28
 */
@Data
@TableName("coupon_coupon")
@NoArgsConstructor
@AllArgsConstructor
public class CouponEntity {

    /**
     * 优惠劵ID
     */
    @TableId(type = IdType.AUTO)
    @Excel(name = "优惠劵ID", cellType = Excel.ColumnType.NUMERIC)
    private Integer couponId;
    /**
     * 优惠劵名称
     */
    @Excel(name = "优惠劵名称")
    private String couponName;
    /**
     * 满足金额
     */
    @Excel(name = "满足金额")
    private BigDecimal couponFull;
    /**
     * 优惠金额
     */
    @Excel(name = "优惠金额")
    private BigDecimal couponReduce;
    /**
     * 优惠劵数量
     */
    @Excel(name = "优惠劵数量", cellType = Excel.ColumnType.NUMERIC)
    private Integer couponNum;

    public CouponEntity getDefaultCoupon(){
        return new CouponEntity(
                4,"安慰奖10元优惠券",new BigDecimal("10.00"),new BigDecimal("10.00"),null
        );
    }
}
