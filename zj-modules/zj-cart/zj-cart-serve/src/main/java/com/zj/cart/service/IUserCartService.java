package com.zj.cart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zj.cart.common.domain.UserCartEntity;
import com.zj.system.common.vo.GoodsItemVo;

/**
 * @author zj
 * @create 2022-08-28 21:02
 */
public interface IUserCartService extends IService<UserCartEntity> {


    void addCart(GoodsItemVo goodsItemVo);

    void removeCartGoodsItem(String goodsId);
}
