package com.zj.system.common.domain.request;

import com.zj.system.common.domain.GoodsEntity;
import lombok.Data;

/**
 * @author zj
 * @create 2022-08-29 9:28
 */
@Data
public class GoodsSearchVo{

    private Integer pageNum = 1;

    private Integer pageSize = 5;

    private GoodsEntity goodsEntity;

}
