package com.zj.cart.controller;

import com.zj.cart.service.IUserCartService;
import com.zj.common.core.domain.Result;
import com.zj.common.core.exception.BizException;
import com.zj.common.core.utils.StringUtils;
import com.zj.system.common.vo.GoodsItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zj
 * @create 2022-08-28 21:01
 */
@RestController
@RequestMapping("/user/cart")
public class UserCartController {
    @Autowired
    private IUserCartService userCartService;


    @PostMapping("/addCart")
    public Result<?> addCart(@RequestBody GoodsItemVo goodsItemVo){
        userCartService.addCart(goodsItemVo);
        return Result.success(null,"添加成功");
    }

    @PostMapping("/removeCartGoodsItem")
    public Result<?> removeCartGoodsItem(@RequestParam("goodsId")String goodsId){
        if(StringUtils.isEmpty(goodsId)){
            throw new BizException(500, "要删除的商品ID为空");
        }
        userCartService.removeCartGoodsItem(goodsId);
        return Result.success("成功");
    }


}
