package com.zj.cart.common.domain.vo;

import com.zj.cart.common.domain.CartGoodsEntity;
import com.zj.cart.common.domain.UserCartEntity;
import lombok.Data;

import java.util.List;

/**
 * @author zj
 * @create 2022-08-28 23:26
 */
@Data
public class UserCartVo {

    private List<CartGoodsEntity> cartGoodsEntityList;

    private UserCartEntity userCartEntity;

}
