package com.workforce.service;

import com.workforce.dto.master.MenuDto;
import com.workforce.entity.master.Menu;
import com.workforce.param.master.MenuParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface MenuService {

    MenuDto create(MenuParam param) throws Exception;

    MenuDto getById(UUID id);

    Page<MenuDto> getAll(Specification<Menu> specification, Pageable pageable);

    List<MenuDto> getAll(Specification<Menu> specification, Sort sort);

    MenuDto update(MenuParam param) throws Exception;

    MenuDto statusUpdate(UUID id) throws Exception;

    void delete(UUID id) throws Exception;
}
