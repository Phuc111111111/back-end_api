package com.covid.processor;

import com.covid.converter.UserConverter;
import com.covid.entity.User;
import com.covid.exception.CommonException;
import com.covid.model.enums.UserStatus;
import com.covid.model.request.AuthDTO;
import com.covid.security.jwt.JwtUtils;
import com.covid.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthProcessor {

    JwtUtils jwtUtils;
    UserService userService;
    UserConverter converter;

    public AuthProcessor(JwtUtils jwtUtils, UserService userService, UserConverter converter) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.converter = converter;
    }



    public String auth(AuthDTO authDTO) throws CommonException {
        User user = userService.findByUsernameAndStatus(authDTO.getUsername(), UserStatus.ACTIVE).orElse(null);
        validateUser(user, authDTO);
        return jwtUtils.generateJwtToken(converter.toModel(user));
    }

    private void validateUser(User user, AuthDTO authDTO) throws CommonException {
        if (Objects.isNull(user)) {
            throw new CommonException("access.denied");
        }

        if (!userService.passwordMatched(authDTO.getPassword(), user)) {
            throw new CommonException("access.denied");
        }
    }

}
