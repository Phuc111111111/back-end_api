package com.covid.processor;


import com.covid.converter.UserConverter;
import com.covid.entity.User;
import com.covid.exception.CommonException;
import com.covid.model.UserDTO;
import com.covid.model.enums.UserRole;
import com.covid.model.enums.UserStatus;
import com.covid.model.query.CommonQuery;
import com.covid.model.request.AddUserDTO;
import com.covid.model.request.UpdateUserDTO;
import com.covid.repository.CommonRepository;
import com.covid.repository.UserRepository;
import com.covid.service.UserService;
import com.covid.utils.ImageUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserProcessor {

    private UserService service;
    private UserConverter converter;
    private ImageUtils imageUtils;

    public UserProcessor(UserService service, UserConverter converter, ImageUtils imageUtils) {
        this.service = service;
        this.converter = converter;
        this.imageUtils = imageUtils;
    }

    public Page<UserDTO> get(CommonQuery model) {
       return service.get(model).map(converter::toModel);
    }

    public void add(AddUserDTO model) throws CommonException {
        validateAdd(model);
        model.setPassword(service.encodePassword(model.getPassword()));
        service.save(converter.toUserDTO(model));
    }

    public void register(AddUserDTO model) throws CommonException {
        validateAdd(model);
        model.setPassword(service.encodePassword(model.getPassword()));
        service.save(converter.toUserDTOForRegister(model));
    }

    public void edit(UpdateUserDTO model, Long id) throws CommonException {
        service.save(model, id);
    }

    public List<UserDTO> getAllDoctors() throws CommonException {
       return service.findAllByRole(UserRole.DOCTOR).stream().map(converter::toModel).collect(Collectors.toList());
    }

    public void updateImage(Long id, MultipartFile file) throws CommonException {
        User user = service.findById(id).orElse(null);
        if (Objects.isNull(user)) {
            throw new CommonException("not.found");
        }
        user.setImage(imageUtils.save(file));
        service.save(user);
    }

    public void uploadImage (MultipartFile file) { imageUtils.save(file); }

    public void delete(Long id) throws CommonException {
        User user = service.findById(id).orElse(null);
        if (Objects.isNull(user)) {
            throw new CommonException("not.found");
        }
        service.deleteById(user.getId());
    }

    private void validateAdd(AddUserDTO model) throws CommonException {
        if (service.existsUserByUsername(model.getUsername())) {
            throw new CommonException("username.duplicated");
        }
    }

    public UserDTO findById (Long id) throws CommonException {
        User user = service.findById(id).orElse(null);
        if (Objects.isNull(user)) {
            throw new CommonException("not.found");
        }
        return converter.toModel(user) ;
    }

    public List<UserDTO> findByRole (Enum role) {
        List<User> users = service.findByRole(role);
        return users.stream().map(item -> converter.toModel(item)).collect(Collectors.toList());
    }

    public UserDTO findActive(String username) throws CommonException {
        User user = service.findByUsernameAndStatus(username, UserStatus.ACTIVE).orElse(null);
        if (Objects.isNull(user)) { throw new CommonException("not.found"); }
        return converter.toModel(user);
    }

}
