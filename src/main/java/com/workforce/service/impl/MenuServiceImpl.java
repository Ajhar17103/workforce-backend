package com.workforce.service.impl;

import com.workforce.dto.master.MenuDto;
import com.workforce.entity.master.Menu;
import com.workforce.mapper.MenuMapper;
import com.workforce.param.master.MenuParam;
import com.workforce.repository.MenuRepository;
import com.workforce.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    @Override
    @Transactional
    public MenuDto create(MenuParam param) throws Exception {
        Menu menu = menuMapper.toEntity(param);
        menu = menuRepository.save(menu);
        return entityToDto(menu);
    }

    @Override
    public MenuDto getById(UUID id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found with id: " + id));
        return entityToDto(menu);
    }

    @Override
    public List<MenuDto> getAll() {
        List<Menu> menus = menuRepository.findAll();
        return menus.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MenuDto update(MenuParam param) throws Exception {
        Menu existingMenu = menuRepository.findById(param.getId())
                .orElseThrow(() -> new RuntimeException("Menu not found with id: " + param.getId()));

        menuMapper.mergeMenuInfo(existingMenu, param);
        Menu updatedMenu = menuRepository.save(existingMenu);
        return entityToDto(updatedMenu);
    }

    @Override
    @Transactional
    public MenuDto statusUpdate(UUID id) throws Exception {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found with id: " + id));
        menu.setActive(!menu.getActive());
        menu = menuRepository.save(menu);
        return entityToDto(menu);
    }

    @Override
    @Transactional
    public void delete(UUID id) throws Exception {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found with id: " + id));
        menuRepository.delete(menu);
    }

    private MenuDto entityToDto(Menu entity) {
        return menuMapper.toDto(entity);
    }
}
