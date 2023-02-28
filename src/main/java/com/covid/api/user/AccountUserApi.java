package com.covid.api.user;

import com.covid.exception.CommonException;
import com.covid.model.UserDTO;
import com.covid.model.query.CommonQuery;
import com.covid.model.request.AddUserDTO;
import com.covid.processor.UserProcessor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/user/accounts")
public class AccountUserApi {

    UserProcessor processor;

    public AccountUserApi(UserProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id) throws CommonException {
        return processor.findById(id);
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody AddUserDTO userDTO) throws CommonException {
         processor.register(userDTO);
    }

    @GetMapping("/doctor")
    public List<UserDTO> getAllDoctors() throws CommonException {
       return processor.getAllDoctors();
    }

}