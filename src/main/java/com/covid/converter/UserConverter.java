package com.covid.converter;

import com.covid.entity.User;
import com.covid.model.UserDTO;
import com.covid.model.enums.UserRole;
import com.covid.model.enums.UserStatus;
import com.covid.model.request.AddUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(imports = {UserStatus.class, UserRole.class})
public interface UserConverter  extends CommonConverter<User, UserDTO> {

    @Mapping(target = "status", expression = "java(UserStatus.ACTIVE)")
    User toUserDTO(AddUserDTO addUserDTO);


    @Mapping(target = "status", expression = "java(UserStatus.ACTIVE)")
    @Mapping(target = "role", expression = "java(UserRole.USER)")
    User toUserDTOForRegister(AddUserDTO addUserDTO);
}
