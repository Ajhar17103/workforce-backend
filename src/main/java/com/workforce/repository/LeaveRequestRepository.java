package com.workforce.repository;

import com.workforce.entity.leave.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, UUID> {
    List<LeaveRequest> findByUserId(UUID id);

    @Query("SELECT lr.user.id FROM LeaveRequest lr " +
            "WHERE :today BETWEEN lr.fromDate AND lr.toDate")
    List<UUID> findUserIdsOnLeave(@Param("today") LocalDate today);

    List<LeaveRequest> findByFromDateLessThanEqualAndToDateGreaterThanEqual(
            LocalDate today1,
            LocalDate today2
    );
}