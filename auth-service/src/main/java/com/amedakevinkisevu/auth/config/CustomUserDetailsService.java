package com.amedakevinkisevu.auth.config;

import com.amedakevinkisevu.auth.entity.UserCredentials;
import com.amedakevinkisevu.auth.repository.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private  UserCredentialsRepository credentialsRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredentials> userCredential =credentialsRepository.findByName(username);
        return userCredential
                .map(CustomUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("user not found with name  :"+username));
    }
}
