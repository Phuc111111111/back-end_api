package com.covid.service;


import com.covid.entity.InforCovid;
import com.covid.entity.QUser;
import com.covid.entity.User;
import com.covid.exception.CommonException;
import com.covid.model.enums.UserRole;
import com.covid.model.enums.UserStatus;
import com.covid.model.query.CommonQuery;
import com.covid.model.request.UpdateUserDTO;
import com.covid.repository.InfoCovidRepository;
import com.covid.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService extends CommonService<User, Long, UserRepository> {

    private EntityManager entityManager;
    private PasswordEncoder passwordEncoder;

    public UserService(EntityManager entityManager, UserRepository repo) {
        super(repo);
        this.entityManager = entityManager;
        this.passwordEncoder = new BCryptPasswordEncoder();;
    }

    public Page<User>  get(CommonQuery model) {
        Pageable pageable = PageRequest.of(model.getPage() - 1, model.getSize());
        JPAQuery<User> query = new JPAQuery(entityManager);
        setQueryCondition(query, model);

        List<User> users = query.limit(pageable.getPageSize()).offset(pageable.getOffset())
                .orderBy(QUser.user.createdDate.desc()).fetch();
        Long count = query.fetchCount();

        return  new PageImpl<>(users, pageable, count);
    }

    public List<User> findByRole(Enum role) {
        List<User> user = repo.findByRole(role);
        return user ;
    }

    private void setQueryCondition(JPAQuery query, CommonQuery model) {
        query.select(QUser.user).from(QUser.user);
        if (StringUtils.isNotBlank(model.getKeyword())) {
            query.where(QUser.user.fullName.contains(model.getKeyword()));
        }
    }

    public boolean existsUserByUsername(String username) {
        return repo.existsUserByUsername(username);
    }

    public User findByUserName(String username) throws CommonException {
        User user = repo.findByUsername(username).orElse(null);
        if (Objects.isNull(user)) {
            throw new CommonException("not.found");
        }
        return user ;
    }

    public Optional<User> findByUsernameAndStatus(String username, UserStatus status) {
       return repo.findByUsernameAndStatus(username, status);
    }

    public boolean passwordMatched(String password, User user) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    public void save(UpdateUserDTO model, Long id) throws CommonException {
        User user = repo.findById(id).orElse(null);
        if (Objects.isNull(user)) {
            throw new CommonException("not.found");
        }
        user.setAddress(model.getAddress());
        user.setDescription(model.getDescription());
        user.setEmail(model.getEmail());
        user.setFullName(model.getFullName());
        user.setPhone(model.getPhone());
        user.setStatus(model.getStatus());
        user.setRole(model.getRole());
        user.setImage(model.getImage());
        repo.save(user);
    }

    public String encodePassword(String raw) {
        return passwordEncoder.encode(raw);
    }

    public List<User> findAllByRole(UserRole role) {
        return repo.findAllByRole(role);
    }

}
