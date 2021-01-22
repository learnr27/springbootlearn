package com.bannad927.learn03;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: chengbinbin
 * @date: 2021.1.20
 **/
@Slf4j
public class Test03 {

    public static void main(String[] args) {


    }

    public static void xx(){

    }


    private static void TwoSumDemo2() {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        log.info(":{}", towSum2(nums, target));
    }

    public static int[] towSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int partnerNumber = target - nums[i];
            if (map.containsKey(partnerNumber)) {
                return new int[]{map.get(partnerNumber), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    private static void TwoSumDemo1() {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 22;
        log.info(":{}", towSum1(nums, target));
    }

    public static int[] towSum1(int[] nums, int targetNum) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (targetNum - nums[i] == nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static void intern() {
        String sb = new StringBuilder("redis").append("5.0").toString();
        System.out.println(sb);
        System.out.println(sb.intern());
        System.out.println(sb == sb.intern());

        String sb1 = new StringBuilder("ja").append("va").toString();
        System.out.println(sb1);
        System.out.println(sb1.intern());
        System.out.println(sb1 == sb1.intern());
    }
}
