package com.workforce.mapper;

import com.workforce.dto.attendance.AttendanceDto;
import com.workforce.dto.attendance.AttendanceEventDto;
import com.workforce.entity.attendance.Attendance;
import com.workforce.entity.attendance.AttendanceEvent;
import com.workforce.param.attendance.AttendanceEventParam;
import com.workforce.param.attendance.AttendanceParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AttendanceMapper {

    public Attendance paramToEntity(AttendanceParam param, Attendance entity) {
        if (param == null) return null;

        if (param.getEvents() != null && !param.getEvents().isEmpty()) {
            for (AttendanceEventParam evParam : param.getEvents()) {
                AttendanceEvent ev = new AttendanceEvent();
                ev.setAttendance(entity);
                ev.setType(evParam.getType());
                ev.setTimestamp(evParam.getTimestamp());
                ev.setLat(evParam.getLat());
                ev.setLng(evParam.getLng());
                ev.setRemarks(evParam.getRemarks());
                entity.getEvents().add(ev);
            }
        }

        return entity;
    }

    public AttendanceDto entityToDto(Attendance entity) {
        if (entity == null) return null;

        AttendanceDto dto = new AttendanceDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);
        dto.setWorkDate(entity.getWorkDate());
        dto.setStaySeconds(entity.getStaySeconds());
        dto.setStatus(entity.getStatus());

        if (entity.getEvents() != null && !entity.getEvents().isEmpty()) {
            List<AttendanceEventDto> eventDtos = entity.getEvents().stream().map(event -> {
                AttendanceEventDto evDto = new AttendanceEventDto();
                evDto.setId(event.getId());
                evDto.setAttendanceId(entity.getId());
                evDto.setType(event.getType());
                evDto.setTimestamp(event.getTimestamp());
                evDto.setLat(event.getLat());
                evDto.setLng(event.getLng());
                evDto.setRemarks(event.getRemarks());
                return evDto;
            }).toList();
            dto.setEvents(eventDtos);
        }

        return dto;
    }
}
