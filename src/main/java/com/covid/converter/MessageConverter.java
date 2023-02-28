package com.covid.converter;

import com.covid.entity.Message;
import com.covid.model.MessageDTO;
import org.mapstruct.Mapper;

@Mapper
public interface MessageConverter extends CommonConverter<Message, MessageDTO> {
}
