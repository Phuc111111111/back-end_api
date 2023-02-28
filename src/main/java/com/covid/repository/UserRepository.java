package com.covid.repository;

import com.covid.entity.User;
import com.covid.model.enums.UserRole;
import com.covid.model.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> , QuerydslPredicateExecutor<User> {

    boolean existsUserByUsername(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndStatus(String username, UserStatus status);
    List<User> findByRole(Enum role);

    List<User> findAllByRole(UserRole role);


}
