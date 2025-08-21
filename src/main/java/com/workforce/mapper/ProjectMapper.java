package com.workforce.mapper;

import com.workforce.dto.master.ProjectDto;
import com.workforce.entity.master.Project;
import com.workforce.entity.master.User;
import com.workforce.exception.DataNotFoundException;
import com.workforce.param.master.ProjectParam;
import com.workforce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProjectMapper {
    private final UserRepository userRepository;

    public Project paramToEntity(ProjectParam param, Project entity) {
        entity.setName(param.getName());
        entity.setDescription(param.getDescription());
        entity.setStatus(param.getStatus());
        entity.setStartDate(param.getStartDate());
        entity.setEndDate(param.getEndDate());

        if (param.getAssignUser() != null) {
            List<User> users = param.getAssignUser().stream()
                    .map(userId -> userRepository.findById(userId)
                            .orElseThrow(() -> new DataNotFoundException("User not found: " + userId)))
                    .collect(Collectors.toCollection(ArrayList::new));
            entity.setAssignUser(users);
        } else {
            entity.setAssignUser(new ArrayList<>());
        }

        return entity;
    }

    public ProjectDto entityToDto(Project entity) {
        if (entity == null) return null;

        ProjectDto dto = new ProjectDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());

        if (entity.getAssignUser() != null) {
            dto.setAssignUser(entity.getAssignUser().stream()
                    .map(User::getId)
                    .collect(Collectors.toCollection(ArrayList::new)));
        } else {
            dto.setAssignUser(new ArrayList<>());
        }

        return dto;
    }
}
