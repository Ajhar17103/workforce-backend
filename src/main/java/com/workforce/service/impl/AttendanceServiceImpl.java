package com.workforce.service.impl;

import com.workforce.dto.attendance.AttendanceDto;
import com.workforce.entity.attendance.Attendance;
import com.workforce.entity.attendance.AttendanceEvent;
import com.workforce.entity.master.User;
import com.workforce.enums.AttendanceType;
import com.workforce.exception.DataAlreadyExistsException;
import com.workforce.exception.DataNotFoundException;
import com.workforce.mapper.AttendanceMapper;
import com.workforce.param.attendance.AttendanceEventParam;
import com.workforce.param.attendance.AttendanceParam;
import com.workforce.repository.*;
import com.workforce.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;
    private final AttendanceMapper attendanceMapper;
    private final AttendanceEventRepository attendanceEventRepository;

    @Override
    @Transactional
    public AttendanceDto create(AttendanceParam param) {
        return entityToDto(createReturnEntity(param));
    }

    @Override
    public AttendanceDto getById(UUID id) {
        return entityToDto(getEntityById(id));
    }

    @Override
    public List<AttendanceDto> getAll() {
        return attendanceRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AttendanceDto update(AttendanceParam param) {
        Attendance entity = getEntityById(param.getId());
        entity = paramToEntity(param, entity);
        entity.setActive(true);
        return entityToDto(attendanceRepository.save(entity));
    }

    @Override
    @Transactional
    public AttendanceDto statusUpdate(UUID id) {
        Attendance entity = getEntityById(id);
        entity.setActive(!entity.getActive());
        entity = attendanceRepository.save(entity);
        return entityToDto(entity);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Attendance entity = getEntityById(id);
        attendanceRepository.delete(entity);
    }

    @Override
    public List<AttendanceDto> getAttendanceByUserId(UUID userId) {
        return attendanceRepository.findByUserId(userId)
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceDto getAttendanceByUserIdAndWorkDate(AttendanceParam param) {
        Attendance attendance = attendanceRepository.findByUserIdAndWorkDate(param.getUserId(), param.getWorkDate());
        return entityToDto(attendance);
    }

    private Attendance getEntityById(UUID id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Attendance not found with id: " + id));
    }

    private Attendance createReturnEntity(AttendanceParam param) {
        Attendance entity = new Attendance();
        entity = paramToEntity(param, entity);
        entity.setActive(true);
        return attendanceRepository.save(entity);
    }

    private AttendanceDto entityToDto(Attendance entity) {
        return attendanceMapper.entityToDto(entity);
    }

    private Attendance paramToEntity(AttendanceParam param, Attendance entity) {

        if (param.getWorkDate() != null && param.getUserId() != null) {
            Optional<Attendance> existingAttendance = Optional.ofNullable(
                    attendanceRepository.findByUserIdAndWorkDate(param.getUserId(), param.getWorkDate())
            );

            if (existingAttendance.isPresent()
                    && (entity.getId() == null || !existingAttendance.get().getId().equals(entity.getId()))) {
                throw new DataAlreadyExistsException("Attendance already given");
            }

            if (param.getEvents() != null && !param.getEvents().isEmpty()) {
                Set<AttendanceType> seenTypes = new HashSet<>();
                for (AttendanceEventParam ev : param.getEvents()) {
                    if (!seenTypes.add(ev.getType())) {
                        throw new DataAlreadyExistsException("Duplicate event in request: " + ev.getType());
                    }
                }

                if (existingAttendance.isPresent() && entity.getId() != null) {
                    UUID attendanceId = existingAttendance.get().getId();
                    for (AttendanceEventParam ev : param.getEvents()) {
                        attendanceEventRepository.findByAttendanceIdAndType(attendanceId, ev.getType())
                                .ifPresent(e -> {
                                    throw new DataAlreadyExistsException("Duplicate event found: " + ev.getType());
                                });
                    }
                }
            }

            entity.setWorkDate(param.getWorkDate());

        }

        if (param.getUserId() != null) {
            User user = userRepository.findById(param.getUserId())
                    .orElseThrow(() -> new DataNotFoundException("User not found with id: " + param.getUserId()));
            entity.setUser(user);
        }

        entity = attendanceMapper.paramToEntity(param, entity);
        return entity;
    }
}