package com.zj.system.common.request;

import com.zj.system.common.domain.GoodsEntity;
import lombok.Data;

/**
 * @author zj
 * @create 2022-09-02 11:36
 */
@Data
public class GoodsRequest extends GoodsEntity {

    /**
     * 第几页
     */
    private Integer pageNum = 1;
    /**
     * 每页条数
     */
    private Integer pageSize = 5;
}
