package com.hanggle.frames.util;

import com.hanggle.frames.base.IdGenerator;

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
}
