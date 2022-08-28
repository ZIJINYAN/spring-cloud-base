package com.zj.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zj.system.common.domain.GoodsEntity;
import com.zj.system.mapper.GoodsMapper;
import com.zj.system.service.IGoodsService;
import org.springframework.stereotype.Service;

/**
 * @author zj
 * @create 2022-08-28 20:46
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, GoodsEntity> implements IGoodsService {
}
