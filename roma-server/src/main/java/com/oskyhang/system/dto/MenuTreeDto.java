package com.oskyhang.system.dto;

import com.oskyhang.system.entity.SysMenu;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: hanggle
 * @date: 2018/11/25
 */
@Data
public class MenuTreeDto {
    private Long id;
    private String label;
    private Integer level;
    private List<MenuTreeDto> children;

    public MenuTreeDto() {
    }
    public MenuTreeDto(SysMenu sysMenu) {
        this.id = sysMenu.getId();
        this.label = sysMenu.getName();
        this.level = sysMenu.getLevel();
    }
}
