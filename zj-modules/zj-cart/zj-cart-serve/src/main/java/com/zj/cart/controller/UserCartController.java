package com.zj.cart.controller;

import com.zj.cart.service.IUserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zj
 * @create 2022-08-28 21:01
 */
@RestController
@RequestMapping("/user/cart")
public class UserCartController {
    @Autowired
    private IUserCartService userCartService;
}
