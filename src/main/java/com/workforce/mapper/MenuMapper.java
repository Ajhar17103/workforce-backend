package com.workforce.mapper;



import com.workforce.entity.master.Menu;
import com.workforce.param.master.MenuParam;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuMapper {

    public Menu toMenu(final MenuParam request) {
        return Menu.builder()
                .name(request.getName())
                .menuType(request.getMenuType())
                .parentId(request.getParentId())
                .icon(request.getIcon())
                .path(request.getPath())
                .build();
    }

    public void mergeMenuInfo(final Menu menu, final MenuParam request) {
        if (StringUtils.isNotBlank(request.getName()) && !menu.getName().equals(request.getName())) {
            menu.setName(request.getName());
        }
        if (StringUtils.isNotBlank(request.getMenuType()) && !menu.getMenuType().equals(request.getMenuType())) {
            menu.setMenuType(request.getMenuType());
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


