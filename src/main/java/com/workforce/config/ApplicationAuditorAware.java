package com.workforce.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class ApplicationAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
//        final Authentication authentication = SecurityContextHolder.getContext()
//                .getAuthentication();
//
//        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
//            return Optional.empty();
//        }
//
//        final User user = (User) authentication.getPrincipal();
//        return Optional.ofNullable(user.getId());
        return Optional.of("admin");
    }
}