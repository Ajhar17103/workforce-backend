package com.workforce.mapper;

import com.workforce.dto.task_board.TaskDto;
import com.workforce.entity.task_board.Task;
import com.workforce.param.task_board.TaskParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskMapper {

    public Task paramToEntity(TaskParam param, Task entity) {
        if (param == null) return null;


        if (param.getName() != null) entity.setName(param.getName());
        if (param.getDescription() != null) entity.setDescription(param.getDescription());
        if (param.getTaskTracker() != null) entity.setTaskTracker(param.getTaskTracker());
        if (param.getPriority() != null) entity.setPriority(param.getPriority());
        if (param.getTaskType() != null) entity.setTaskType(param.getTaskType());
        if (param.getStartDate() != null) entity.setStartDate(param.getStartDate());
        if (param.getEndDate() != null) entity.setEndDate(param.getEndDate());
        if (param.getEstimatedTime() != null) entity.setEstimatedTime(param.getEstimatedTime());
        if (param.getTaskStatus() != null) entity.setTaskStatus(param.getTaskStatus());
        if (param.getChallenges() != null) entity.setChallenges(param.getChallenges());
        if (param.getRemarks() != null) entity.setRemarks(param.getRemarks());

//        if (param.getFile() != null) {
//            try {
//                MultipartFile file = new MultipartFile(System.getProperty("java.io.tmpdir"), param.getFile().getOriginalFilename()) {
//                };
//                param.getFile().transferTo(file);
//                entity.setFile(file);
//            } catch (Exception e) {
//                throw new RuntimeException("Failed to store file", e);
//            }
//        }

        return entity;
    }

    public TaskDto entityToDto(Task entity) {
        if (entity == null) return null;

        TaskDto dto = new TaskDto();
        dto.setId(entity.getId());
        dto.setProjectId(entity.getProject() != null ? entity.getProject().getId() : null);
        dto.setSprintId(entity.getSprint() != null ? entity.getSprint().getId() : null);
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);

        // Extra fields similar to UserMapper style
        dto.setProjectName(entity.getProject() != null ? entity.getProject().getName() : null);
        dto.setSprintName(entity.getSprint() != null ? entity.getSprint().getName() : null);
        dto.setUserName(entity.getUser() != null ? entity.getUser().getName() : null);

        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setTaskTracker(entity.getTaskTracker() != null ? entity.getTaskTracker().name() : null);
        dto.setPriority(entity.getPriority() != null ? entity.getPriority().name() : null);
        dto.setTaskType(entity.getTaskType() != null ? entity.getTaskType().name() : null);

        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setEstimatedTime(entity.getEstimatedTime());
        dto.setTaskStatus(entity.getTaskStatus());
        dto.setChallenges(entity.getChallenges());
        dto.setRemarks(entity.getRemarks());
//        if (entity.getFile() != null) {
//            dto.setFileName(entity.getFile().getName());
//            dto.setFileUrl("/api/tasks/files/" + entity.getFile().getName());
//        }

        return dto;
    }
}


