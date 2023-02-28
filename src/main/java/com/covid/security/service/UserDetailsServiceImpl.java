package com.covid.security.service;

import com.covid.entity.User;
import com.covid.model.enums.UserStatus;
import com.covid.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameAndStatus(username, UserStatus.ACTIVE)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(user);
    }
}
