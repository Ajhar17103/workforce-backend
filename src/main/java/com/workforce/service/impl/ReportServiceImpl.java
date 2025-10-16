package com.workforce.service.impl;

import com.workforce.dto.report.DailyAttendanceReportDto;
import com.workforce.dto.report.ProjectReportDto;
import com.workforce.dto.report.UserSummaryDto;
import com.workforce.dto.report.UserTaskReportDto;
import com.workforce.entity.attendance.Attendance;
import com.workforce.entity.attendance.AttendanceEvent;
import com.workforce.entity.master.Project;
import com.workforce.entity.master.User;
import com.workforce.entity.task_board.Task;
import com.workforce.enums.AttendanceDailyStatus;
import com.workforce.enums.AttendanceType;
import com.workforce.enums.TaskStatus;
import com.workforce.repository.*;
import com.workforce.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportServiceImpl implements ReportService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final AttendanceRepository attendanceRepository;
    private final LeaveRequestRepository leaveRequestRepository;


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
    public List<UserTaskReportDto> getTodayUserTaskReport() {
        LocalDate today = LocalDate.now();
        List<User> users = userRepository.findAll();

        return users.stream().map(user -> {
            List<Task> userTasks = taskRepository.findByUser(user).stream()
                    .filter(t -> t.getStartDate() != null && t.getStartDate().toLocalDate().isEqual(today))
                    .toList();

            long completed = userTasks.stream()
                    .filter(t -> t.getTaskStatus() == TaskStatus.COMPLETED)
                    .count();
            long inProgress = userTasks.stream()
                    .filter(t -> t.getTaskStatus() == TaskStatus.IN_PROGRESS)
                    .count();
            long pending = userTasks.stream()
                    .filter(t -> t.getTaskStatus() == TaskStatus.TO_DO)
                    .count();

            double allocatedTime = userTasks.stream()
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

            double spentTime = userTasks.stream()
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

    @Override
    public List<DailyAttendanceReportDto> getDailyAttendanceReport() {
        LocalDate today = LocalDate.now();

        List<User> allUsers = userRepository.findAll();
        List<Attendance> todayAttendances = attendanceRepository.findAllByWorkDate(today);

        Map<UUID, Attendance> attendanceMap = todayAttendances.stream()
                .collect(Collectors.toMap(a -> a.getUser().getId(), a -> a));

        List<UUID> leaveUserIds = leaveRequestRepository.findUserIdsOnLeave(today);

        List<DailyAttendanceReportDto> summary = new ArrayList<>();

        for (User user : allUsers) {
            Attendance attendance = attendanceMap.get(user.getId());
            DailyAttendanceReportDto.DailyAttendanceReportDtoBuilder<?, ?> builder = DailyAttendanceReportDto.builder()
                    .userId(user.getId())
                    .userName(user.getName())
                    .workDate(today);

            if (leaveUserIds.contains(user.getId())) {
                builder.status(AttendanceDailyStatus.ON_LEAVE).build();
                summary.add(builder.build());
                continue;
            }

            if (attendance != null && !attendance.getEvents().isEmpty()) {
                LocalDateTime checkIn = attendance.getEvents().stream()
                        .filter(e -> e.getType() == AttendanceType.DAY_START)
                        .map(AttendanceEvent::getTimestamp)
                        .min(LocalDateTime::compareTo)
                        .orElse(null);

                LocalDateTime checkOut = attendance.getEvents().stream()
                        .filter(e -> e.getType() == AttendanceType.DAY_END)
                        .map(AttendanceEvent::getTimestamp)
                        .max(LocalDateTime::compareTo)
                        .orElse(null);

                String checkInTime = checkIn != null ? checkIn.toLocalTime().toString() : "-";
                String checkOutTime = checkOut != null ? checkOut.toLocalTime().toString() : "-";

                AttendanceDailyStatus status;
                if (checkIn != null && checkIn.toLocalTime().isAfter(LocalTime.of(9, 0))) {
                    status = AttendanceDailyStatus.LATE;
                } else {
                    status = AttendanceDailyStatus.REGULAR;
                }

                long durationMinutes = 0;
                if (checkIn != null && checkOut != null) {
                    durationMinutes = Duration.between(checkIn, checkOut).toMinutes();
                }else if(checkIn != null) {
                    durationMinutes = Duration.between(checkIn, LocalDateTime.now()).toMinutes();
                }

                String duration = durationMinutes > 0
                        ? String.format("%dh %02dm", durationMinutes / 60, durationMinutes % 60)
                        : "-";

                builder.checkInTime(checkInTime)
                        .checkOutTime(checkOutTime)
                        .status(status)
                        .duration(duration);

            } else {
                LocalTime now = LocalTime.now();
                AttendanceDailyStatus status = now.isAfter(LocalTime.of(10, 0))
                        ? AttendanceDailyStatus.ABSENT
                        : null;
                builder.status(status)
                        .checkInTime("-")
                        .checkOutTime("-")
                        .duration("-");
            }

            summary.add(builder.build());
        }

        return summary;
    }
}
