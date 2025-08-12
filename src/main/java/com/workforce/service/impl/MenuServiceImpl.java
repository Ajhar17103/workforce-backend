package com.workforce.service;

import com.workforce.dto.master.MenuDto;
import com.workforce.entity.master.Menu;
import com.workforce.mapper.MenuMapper;
import com.workforce.param.master.MenuParam;
import com.workforce.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    @Override
    public MenuDto create(MenuParam param) throws Exception {
        return null;
    }

    @Override
    public MenuDto getById(UUID id) {
        return null;
    }

    @Override
    public Page<MenuDto> getAll(Specification<Menu> specification, Pageable pageable) {
        return null;
    }

    @Override
    public List<MenuDto> getAll(Specification<Menu> specification, Sort sort) {
        return List.of();
    }

    @Override
    public MenuDto update(MenuParam param) throws Exception {
        return null;
    }

    @Override
    public MenuDto statusUpdate(UUID id) throws Exception {
        return null;
    }

    @Override
    public void delete(UUID id) throws Exception {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found with id: " + id));
        menuRepository.delete(menu);
    }
}
