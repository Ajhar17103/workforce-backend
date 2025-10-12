package com.workforce.repository;


import com.workforce.entity.leave.AllocatedLeave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AllocatedLeaveRepository extends JpaRepository<AllocatedLeave, UUID> {
    Optional<AllocatedLeave> findByUserId(UUID userId);
    Optional<AllocatedLeave> findByUserIdAndFiscalYear(UUID userId, String fiscalYear);

}