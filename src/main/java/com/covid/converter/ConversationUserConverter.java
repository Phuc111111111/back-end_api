package com.covid.converter;

import com.covid.entity.ConversationUser;
import com.covid.model.ConversationUserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ConversationUserConverter extends CommonConverter<ConversationUser, ConversationUserDTO> {
}
