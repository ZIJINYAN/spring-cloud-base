package com.zj.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zj.system.common.domain.GoodsEntity;
import com.zj.system.common.domain.model.GoodsItemVo;
import com.zj.system.common.domain.request.GoodsSearchVo;
import com.zj.system.mapper.GoodsMapper;
import com.zj.system.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zj
 * @create 2022-08-28 20:46
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, GoodsEntity> implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public List<GoodsItemVo> goodsList(GoodsSearchVo goodsSearchVo) {
        List<GoodsItemVo> goodsItemList = null;
        if(redisTemplate.hasKey("goods_all")){
            goodsItemList = redisTemplate.boundHashOps("goods_all").values()
                    .stream().map(goodsItem -> JSONObject.parseObject(goodsItem.toString(), GoodsItemVo.class)
            ).collect(Collectors.toList());
        } else {
            BoundHashOperations<String, Object, Object> goodsItemMap = redisTemplate.boundHashOps("goods_all");
            goodsItemList = goodsMapper.goodsList(goodsSearchVo);
            goodsItemList.stream().forEach(goodsItem -> {
                goodsItemMap.put(goodsItem.getGoodsEntity().getGoodsId().toString(), JSON.toJSONString(goodsItem));
            });
        }
        return goodsItemList;
    }
}
