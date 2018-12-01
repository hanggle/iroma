package com.oskyhang.system.dto;

import com.oskyhang.system.entity.BdMenu;
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
    private List<MenuTreeDto> children;

    public MenuTreeDto() {
    }
    public MenuTreeDto(BdMenu bdMenu) {
        this.id = bdMenu.getId();
        this.label = bdMenu.getName();
    }
}
