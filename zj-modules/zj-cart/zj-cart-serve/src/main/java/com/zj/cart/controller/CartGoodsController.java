package com.zj.cart.controller;

import com.zj.cart.common.vo.CartItemVo;
import com.zj.cart.service.ICartGoodsService;
import com.zj.common.core.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zj
 * @create 2022-08-28 21:08
 */
@RestController
@RequestMapping("/goods")
public class CartGoodsController {

    @Autowired
    private ICartGoodsService cartGoodsService;

    @PostMapping("/cartList")
    public Result<List<CartItemVo>> cartList(){
        return Result.success(cartGoodsService.cartList(),"成功");
    }
}
