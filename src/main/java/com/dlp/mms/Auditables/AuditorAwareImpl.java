package com.dlp.mms.Auditables;
import com.dlp.mms.Entities.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String>{
    @Override
    public Optional<String> getCurrentAuditor() {

    AuditorAware<String> auditor = auditorProvider();
    if(auditor != null && auditor.getCurrentAuditor().isPresent()){
        return auditor.getCurrentAuditor().get().describeConstable();
    }
    return Optional.of("Unknown");
    }

    @Bean
    public AuditorAware<String> auditorProvider() {

        return () -> {
            Authentication authentication
                    = SecurityContextHolder.getContext().getAuthentication();
            return Optional.ofNullable(authentication)
                    .map(Authentication::getName);
        };
    }
}

