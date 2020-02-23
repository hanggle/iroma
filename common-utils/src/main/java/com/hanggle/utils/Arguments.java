package com.hanggle.utils;

import java.util.Collection;
import java.util.Objects;

/**
 * @description:
 * @author: hanggle
 * @date: 2018/12/22
 */
public class Arguments {

    public static boolean notNull(Object o) {
        return o != null;
    }

    public static boolean notEmpty(Collection o) {
        return notNull(o) && o.size() > 0;
    }

}
