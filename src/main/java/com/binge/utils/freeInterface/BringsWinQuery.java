package com.binge.utils.freeInterface;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 大乐透中奖结果查询
 * @Authror fanbin CAI
 * @Date 2020/10/29 10:36
 */
@FeignClient(name = "github-client", url = "http://apis.juhe.cn")
public interface BringsWinQuery {

    @PostMapping("/lottery/bonus")
    public String queryLottery(@RequestParam("key") String key, @RequestParam("lottery_id") String lottery_id, @RequestParam("lottery_res") String lottery_res);

}
