package com.covid.api;


import com.covid.exception.CommonException;
import com.covid.model.UserDTO;
import com.covid.model.request.AuthDTO;
import com.covid.processor.AuthProcessor;
import com.covid.processor.UserProcessor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auths")
public class AuthApi {

    UserProcessor processor;
    AuthProcessor authProcessor;

    public AuthApi(UserProcessor processor, AuthProcessor authProcessor) {
        this.processor = processor;
        this.authProcessor = authProcessor;
    }

    @PostMapping("/user")
    public UserDTO findActive(@RequestBody UserDTO userDTO) throws CommonException {
        return processor.findActive(userDTO.getUsername()) ;
    }

    @PostMapping
    public String auth(@Valid @RequestBody AuthDTO authDTO) throws CommonException {
        return authProcessor.auth(authDTO);
    }
}
