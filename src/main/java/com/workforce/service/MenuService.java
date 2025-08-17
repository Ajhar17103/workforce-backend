package com.workforce.service;

import com.workforce.dto.master.MenuDto;
import com.workforce.param.master.MenuParam;

import java.util.List;
import java.util.UUID;

public interface MenuService {

    MenuDto create(MenuParam param) throws Exception;

    MenuDto getById(UUID id);

    List<MenuDto> getAll();

    MenuDto update(MenuParam param) throws Exception;

    MenuDto statusUpdate(UUID id) throws Exception;

    void delete(UUID id) throws Exception;
}
