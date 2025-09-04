package com.workforce.repository;


import com.workforce.entity.attendance.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {
    List<Attendance> findByUserId(UUID userId);

    Attendance findByUserIdAndWorkDate(UUID userId, LocalDate workDate);

}