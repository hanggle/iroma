package com.hanggle.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.Random;

/**
 * @description:
 * @author: hanggle
 * @date: 2019/1/4
 */
@Data
public class TempDto implements Serializable {
    private static final long serialVersionUID = 4739438265323713121L;
    private String name;
    private Integer age;
    private Integer gender;
    private School school;

    public TempDto() {
    }

    public TempDto(String name, Integer age, Integer gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.school = new School(1222);
    }
}
@Data
class School{
    private Integer id;

    public School(Integer id) {
        this.id = id;
    }
}