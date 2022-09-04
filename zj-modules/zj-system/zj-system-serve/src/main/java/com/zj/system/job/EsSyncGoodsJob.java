package com.zj.system.job;

import com.zj.system.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author zj
 * @create 2022-09-02 15:33
 */
@Component
public class EsSyncGoodsJob {

    @Autowired
    private IGoodsService goodsService;

    @Scheduled(cron = "0 0/2 * * * ?")
    public void GoodsItemStatus(){
        goodsService.esSyncAll();
    }
}
