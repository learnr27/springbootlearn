package com.bannad927.examples;

import cn.hutool.core.lang.WeightRandom;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author chengbb
 * @date 2020.8.6
 */
@Slf4j
public class Test {

    public static void main(String[] args) {



        Long rewardCouponFirstId = 886L;
        Long rewardCouponSecondId = 776L;
        Long rewardCouponThirdId = 666L;
        Long rewardCouponFourthId = 556L;
        Long rewardCouponFifthId = 996L;

        List<WeightRandom.WeightObj<JSONObject>> weightList = new ArrayList<WeightRandom.WeightObj<JSONObject>>();
        JSONObject wo = new JSONObject();
        wo.put("num", 5);
        wo.put("id", rewardCouponFirstId);
        WeightRandom.WeightObj<JSONObject> wro = new WeightRandom.WeightObj<JSONObject>(wo, 50);
        weightList.add(wro);

        WeightRandom wr = RandomUtil.weightRandom(weightList);

        for (int i = 0; i < 2; i++) {
            JSONObject resultRandom = (JSONObject) JSONUtil.parse(wr.next());
           log.info("xxxx:{}", wr.next().toString());
           log.info("num:{}", resultRandom.get("num"));
           log.info("id:{}", resultRandom.get("id"));
        }

        List<Long> items = new ArrayList<>();
        items.add(rewardCouponFirstId);
        items.add(rewardCouponSecondId);
        items.add(rewardCouponThirdId);
        items.add(rewardCouponFourthId);
        items.add(rewardCouponFifthId);
        Set<Long> reslut = RandomUtil.randomEleSet(items, 5);
        log.info("reslut:{}", reslut);
        for (Long id : reslut) {
            log.info("id:{}", id);
        }

        log.info("reslut.size:{}", reslut.size());
    }
}
