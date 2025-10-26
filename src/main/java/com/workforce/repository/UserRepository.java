package com.workforce.repository;


import com.workforce.entity.master.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmailIgnoreCase(String email);

    Optional<Object> findByPhone(String phone);

    Optional<User> findByName(String name);
}

