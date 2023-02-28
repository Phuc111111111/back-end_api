package com.covid.api.admin;


import com.covid.exception.CommonException;
import com.covid.model.UserDTO;
import com.covid.model.query.CommonQuery;
import com.covid.model.request.AddUserDTO;
import com.covid.model.request.UpdateUserDTO;
import com.covid.processor.UserProcessor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/accounts")
public class AccountAdminApi {

    UserProcessor processor;

    public AccountAdminApi(UserProcessor processor) {
        this.processor = processor;
    }


    @GetMapping
    public Page<UserDTO> get(CommonQuery model) {
        return processor.get(model);
    }


    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id) throws CommonException {
        return processor.findById(id) ;
    }


    @PostMapping
    public void add(@RequestBody @Valid AddUserDTO model) throws CommonException {
        processor.add(model);
    }

    @PutMapping("/{id}")
    public void edit(@Valid @RequestBody UpdateUserDTO model, @PathVariable Long id) throws CommonException {
        processor.edit(model, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws CommonException {
        processor.delete(id);
    }

    @PutMapping("/{id}/image")
    public void updateImage(@PathVariable Long id, @RequestParam MultipartFile file) throws CommonException {
        processor.updateImage(id, file);
    }



}
