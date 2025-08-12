package com.workforce.event;

import com.workforce.entity.auth.Role;
import com.workforce.entity.auth.User;
import com.workforce.repository.RoleRepository;
import com.workforce.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserEventApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (roleRepository.findAll().isEmpty()){
            Role roleAdmin = Role.builder().name("ADMIN").build();
            Role roleUser = Role.builder().name("USER").build();
            roleRepository.saveAll(List.of(roleUser, roleAdmin));
        }
        if (userRepository.findAll().isEmpty()){
            Role role1 = roleRepository.findByName("ADMIN").orElse(null);
            Role role2 = roleRepository.findByName("USER").orElse(null);
            User admin = User.builder()
                    .email("admin@gmail.com")
                    .firstName("Admin")
                    .lastName("User")
                    .phoneNumber("01750349979")
                    .password(passwordEncoder.encode("11223344"))
                    .roles(List.of(role1, role2))
                    .enabled(true)
                    .build();

            User user = User.builder()
                    .email("user@gmail.com")
                    .firstName("User")
                    .lastName("User")
                    .phoneNumber("01750349978")
                    .password(passwordEncoder.encode("11223344"))
                    .roles(List.of(role2))
                    .enabled(true)
                    .build();
            userRepository.saveAll(List.of(admin, user));
        }

    }
}
