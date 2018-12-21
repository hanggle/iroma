package com.hanggle.frames.util;

/**
 * @description: 获去ID
 * @author: hanggle
 * @date: 2018/12/21
 */
public class IdUtil {

    /**
     *  获去ID
     * @return id
     */
    public static long getNextId() {
        return IdGenerator.get().nextId();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            System.out.println(getNextId());
        }
    }
}
