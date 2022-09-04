package com.zj.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zj.system.common.domain.GoodsTypeEntity;
import com.zj.system.mapper.GoodsTypeMapper;
import com.zj.system.service.IGoodsTypeService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author zj
 * @create 2022-08-28 20:49
 */
@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsTypeEntity> implements IGoodsTypeService {

    @Override
    public List<GoodsTypeEntity> goodsTypeList() {
        return this.list();
    }

}
