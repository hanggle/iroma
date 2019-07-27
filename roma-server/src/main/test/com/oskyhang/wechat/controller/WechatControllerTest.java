package com.oskyhang.wechat.controller;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * description: <br/>
 * author: zh <br/>
 * date: 2018/6/10 <br/>
 */
public class WechatControllerTest {

    public static void main(String[] args){
        int[] arr = {12, 12, 2, 2, 2, 2, 15, 5, 5, 3, 5, 1};
        count(arr);
    }

    public static void count(int [] arr){
        int[] myArr = sort(arr);
        int times = 0;
        if (myArr.length == 0) {
            return;
        }
        int num = myArr[0];

        for (int i = 0; i < myArr.length; i++) {
            if (num == myArr[i]) {
                times = times + 1;
            }else{
                System.out.println(num + "出现了" + times + "次");
                num = myArr[i];
                times = 1;
            }
            if (i == myArr.length - 1) {
                System.out.println(num + "出现了" + times + "次");
            }
        }
    }
    // 正序冒泡排序
    public static int[] sort(int [] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int z = arr[i];
                    arr[i] = arr[j];
                    arr[j] = z;
                }
            }
        }
        return arr;
    }

    @Test
    public void mm() {
        int[] arr = {12, 12, 2, 2, 2, 2, 15, 5, 5, 3, 5, 1};
        Map<Integer, Integer> map = new HashMap<>();
        System.out.println(123);
        /*for (int i = 0; i < arr.length; i++) {
            int val = arr[i];
            if (map.get(val) != null) {
                Integer times = map.get(val);
                map.put(val, times + 1);
            }else{
                map.put(val, 1);
            }
        }*/
        fun();

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "出现了" + entry.getValue() + "次");
        }
    }

    private void fun() {
        System.out.println(123);
    }
}