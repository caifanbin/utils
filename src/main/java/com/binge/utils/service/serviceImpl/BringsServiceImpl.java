package com.binge.utils.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.binge.utils.common.constrant.RequestParams;
import com.binge.utils.freeInterface.BringsWinQuery;
import com.binge.utils.service.BringsService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Authror fanbin CAI
 * @Date 2020/10/29 10:42
 */
public class BringsServiceImpl implements BringsService {

    @Resource
    private BringsWinQuery bringsWinQuery;
    @Resource
    private RequestParams requestParams;


    /**
     * 中奖结果查询
     * @param reqlist
     * @return
     */
    @Override
    public String winQuery(List<String> reqlist) {

        List<Map<String, String>> reslist = new ArrayList<>();
        reqlist.forEach(list->{
            String dlt = bringsWinQuery.queryLottery(requestParams.getKey(), "dlt", list);
            Map reqMap = (Map) JSONObject.parse(dlt);
            Map resMap = new HashMap();
            if (reqMap.containsKey("result")) {
                Map map = (Map) JSONObject.parse(reqMap.get("result").toString());
                resMap.put("开奖日期", map.get("lottery_date").toString());
                resMap.put("开奖号码", map.get("real_lottery_res").toString());
                resMap.put("投注号码", map.get("lottery_res").toString());
                resMap.put("已中红球", map.get("hit_red_ball_num").toString());
                resMap.put("已中蓝球", map.get("hit_blue_ball_num").toString());
                resMap.put("中奖信息", map.get("prize_msg").toString());
                if (map.containsKey("lottery_prize")) {
                    if (map.get("lottery_prize").toString() != null && !map.get("lottery_prize").toString().equals("null")) {
                        JSONArray jsonArray = JSONArray.parseArray(map.get("lottery_prize").toString());
                        jsonArray.forEach(attr -> {
                            JSONObject jsonObject = (JSONObject) attr;
                            resMap.put("中奖名称", jsonObject.get("prize_name").toString());
                            resMap.put("中奖条件", jsonObject.get("prize_require").toString());
                            resMap.put("奖金", jsonObject.get("prize_money").toString());
                        });

                    }
                }
            }
            reslist.add(resMap);
        });
        String s = JSON.toJSONString(reslist);
        return s;
    }
}
