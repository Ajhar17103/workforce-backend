package com.workforce.repository;


import com.workforce.entity.daily_standup.DailyStandup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DailyStandupRepository extends JpaRepository<DailyStandup, UUID> {
    List<DailyStandup> findByUserId(UUID userId);
    Optional<DailyStandup> findByUserIdAndDate(UUID userId, LocalDate date);
    List<DailyStandup> findByDate(LocalDate date);

}