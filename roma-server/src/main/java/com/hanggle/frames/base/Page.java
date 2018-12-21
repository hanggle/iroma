package com.hanggle.frames.base;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: hanggle
 * @date: 2018/12/2
 */
@Data
public class Page<T> implements Serializable {

    private static final long serialVersionUID = -1404833718627071453L;

    private Long total;
    private List<T> item;

    public Page() {
    }

    public Page(Long total, List<T> item) {
        this.total = total;
        this.item = item;
    }
}
