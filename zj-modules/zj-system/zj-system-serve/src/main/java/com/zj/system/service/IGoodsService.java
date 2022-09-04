package com.zj.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zj.common.core.domain.PageResult;
import com.zj.common.core.domain.Result;
import com.zj.system.common.domain.GoodsEntity;
import com.zj.system.common.request.GoodsRequest;
import com.zj.system.common.vo.GoodsItemVo;

import java.util.List;

/**
 * @author zj
 * @create 2022-08-28 20:46
 */
public interface IGoodsService extends IService<GoodsEntity> {


    public List<GoodsItemVo> goodsList();

    Result<PageResult<GoodsItemVo>> goodsEsList(GoodsRequest goodsRequest);

    public void esSyncAll();

}
