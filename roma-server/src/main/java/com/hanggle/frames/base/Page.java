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
public class Page<E> implements Serializable {

    private static final long serialVersionUID = -1404833718627071453L;

    private int total;
    private List<E> item;

    public Page() {
    }

    public Page(int total, List<E> item) {
        this.total = total;
        this.item = item;
    }
}
