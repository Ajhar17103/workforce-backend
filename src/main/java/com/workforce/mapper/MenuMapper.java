package com.workforce.mapper;

import com.workforce.dto.master.MenuDto;
import com.workforce.entity.master.Menu;
import com.workforce.param.master.MenuParam;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuMapper {

    public Menu toEntity(final MenuParam request) {
        return Menu.builder()
                .parentId(request.getParentId())
                .parentMenu(request.getParentMenu())
                .name(request.getName())
                .icon(request.getIcon())
                .path(request.getPath())
                .active(true)
                .build();
    }

    public MenuDto toDto(final Menu menu) {
        if (menu == null) {
            return null;
        }
        MenuDto dto = new MenuDto();
        dto.setId(menu.getId());
        dto.setParentId(menu.getParentId());
        dto.setName(menu.getName());
        dto.setParentMenu(menu.getParentMenu());
        dto.setIcon(menu.getIcon());
        dto.setPath(menu.getPath());
        dto.setActive(menu.getActive());
        dto.setCreatedAt(menu.getCreatedAt());
        dto.setUpdatedAt(menu.getUpdatedAt());
        return dto;
    }

    public void mergeMenuInfo(final Menu menu, final MenuParam request) {
        if (StringUtils.isNotBlank(request.getName()) && !menu.getName().equals(request.getName())) {
            menu.setName(request.getName());
        }
        if (StringUtils.isNotBlank(request.getParentMenu()) && !menu.getParentMenu().equals(request.getParentMenu())) {
            menu.setParentMenu(request.getParentMenu());
        }
        if (request.getParentId() != null && !request.getParentId().equals(menu.getParentId())) {
            menu.setParentId(request.getParentId());
        }
        if (StringUtils.isNotBlank(request.getIcon()) && !request.getIcon().equals(menu.getIcon())) {
            menu.setIcon(request.getIcon());
        }
        if (StringUtils.isNotBlank(request.getPath()) && !request.getPath().equals(menu.getPath())) {
            menu.setPath(request.getPath());
        }
    }


}
