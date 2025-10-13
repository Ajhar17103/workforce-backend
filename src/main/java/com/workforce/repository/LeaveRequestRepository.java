package com.workforce.repository;

import com.workforce.entity.leave.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, UUID> {
//    Optional<LeaveRequest> findByUserId(UUID userId);
    List<LeaveRequest> findByUserId(UUID id);

}