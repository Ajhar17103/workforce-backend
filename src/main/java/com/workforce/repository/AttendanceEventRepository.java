package com.workforce.repository;


import com.workforce.entity.attendance.AttendanceEvent;
import com.workforce.enums.AttendanceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttendanceEventRepository extends JpaRepository<AttendanceEvent, UUID> {
    Optional<AttendanceEvent> findByAttendanceIdAndType(UUID attendanceId, AttendanceType type);
}