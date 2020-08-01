package com.hanggle.frames.base;

import com.oskyhang.system.dto.LoginUser;

/**
 * 数据
 *
 * @author hanggle
 * @date 2020/2/24
 */

public class DataThreadLocal {
    private static ThreadLocal user = new ThreadLocal<>();

    public static void putCurrentLoginUser(LoginUser loginUser) {
        user.set(loginUser);
    }

    public static LoginUser getCurrentLoginUser() {
        return (LoginUser) user.get();
    }

    public static void removeLoginUser() {
        user.remove();
    }

}
