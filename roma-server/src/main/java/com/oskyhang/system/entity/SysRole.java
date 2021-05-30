package com.oskyhang.system.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString(callSuper = true)
public class SysRole extends BaseDo{

    private String role;

    private String description;

    private Boolean available;

}