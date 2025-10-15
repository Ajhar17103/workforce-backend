package com.workforce.service.impl;

import com.workforce.dto.report.ProjectReportDto;
import com.workforce.dto.report.UserSummaryDto;
import com.workforce.dto.report.UserTaskReportDto;
import com.workforce.entity.master.Project;
import com.workforce.entity.master.User;
import com.workforce.entity.task_board.Task;
import com.workforce.enums.TaskStatus;
import com.workforce.repository.ProjectRepository;
import com.workforce.repository.TaskRepository;
import com.workforce.repository.UserRepository;
import com.workforce.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportServiceImpl implements ReportService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Override
    public List<ProjectReportDto> getProjectReport() {

        List<Project> projects = projectRepository.findAll();


        return projects.stream().map(project -> {
            ProjectReportDto dto = new ProjectReportDto();

            dto.setId(project.getId());
            dto.setName(project.getName());
            dto.setDescription(project.getDescription());
            dto.setStatus(project.getStatus());
            dto.setStartDate(project.getStartDate());
            dto.setEndDate(project.getEndDate());

            List<UserSummaryDto> assignedUsers = project.getAssignUser().stream()
                    .map(user -> {
                        UserSummaryDto userSummary = new UserSummaryDto();
                        userSummary.setId(user.getId());
                        userSummary.setName(user.getName());

                        if (user.getDesignation()!=null && user.getDesignation().getDepartment() != null) {
                            userSummary.setDepartmentId(user.getDesignation().getDepartment().getId());
                            userSummary.setDepartmentName(user.getDesignation().getDepartment().getName());
                        }

                        if (user.getDesignation() != null) {
                            userSummary.setDesignationId(user.getDesignation().getId());
                            userSummary.setDesignationName(user.getDesignation().getName());
                        }

                        if (user.getRole() != null) {
                            userSummary.setRoleId(user.getRole().getId());
                            userSummary.setRoleName(user.getRole().getName());
                        }



                        return userSummary;
                    })
                    .collect(Collectors.toList());

            dto.setAssignedUsers(assignedUsers);


            List<Task> projectTasks = taskRepository.findByProject(project);

            int totalTasks = projectTasks.size();
            int completedTasks = (int) projectTasks.stream()
                    .filter(t -> t.getTaskStatus() == TaskStatus.COMPLETED)
                    .count();
            int inProgressTasks = (int) projectTasks.stream()
                    .filter(t -> t.getTaskStatus() == TaskStatus.IN_PROGRESS)
                    .count();
            int onHoldTasks = (int) projectTasks.stream()
                    .filter(t -> t.getTaskStatus() == TaskStatus.HOLD)
                    .count();

            dto.setTotalTasks(totalTasks);
            dto.setCompletedTasks(completedTasks);
            dto.setInProgressTasks(inProgressTasks);
            dto.setOnHoldTasks(onHoldTasks);

            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserTaskReportDto> getUserTaskReport() {
        List<User> users = userRepository.findAll();

        return users.stream().map(user -> {
            List<Task> userTasks = taskRepository.findByUser(user);

            long completed = userTasks.stream().filter(t -> t.getTaskStatus() == TaskStatus.COMPLETED).count();
            long inProgress = userTasks.stream().filter(t -> t.getTaskStatus() == TaskStatus.IN_PROGRESS).count();
            long pending = userTasks.stream().filter(t -> t.getTaskStatus() == TaskStatus.TO_DO).count();

            Double allocatedTime = userTasks.stream()
                    .map(Task::getEstimatedTime)
                    .filter(Objects::nonNull)
                    .mapToDouble(s -> {
                        try {
                            return Double.parseDouble(s);
                        } catch (NumberFormatException e) {
                            return 0.0;
                        }
                    })
                    .sum();

            Double spentTime = userTasks.stream()
                    .filter(t -> t.getStartDate() != null && t.getEndDate() != null)
                    .mapToDouble(t -> Duration.between(t.getStartDate(), t.getEndDate()).toMinutes() / 60.0)
                    .sum();


            UserTaskReportDto dto = new UserTaskReportDto();
            dto.setUserId(user.getId().toString());
            dto.setUserName(user.getName());
            dto.setDesignationId(user.getDesignation().getId());
            dto.setDesignationName(user.getDesignation().getName());
            dto.setTotalTasks(userTasks.size());
            dto.setCompletedTasks((int) completed);
            dto.setInProgressTasks((int) inProgress);
            dto.setPendingTasks((int) pending);
            dto.setAllocatedTime(allocatedTime);
            dto.setSpentTime(spentTime);

            return dto;
        }).collect(Collectors.toList());
    }

}
