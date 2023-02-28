package com.covid.converter;

import com.covid.entity.InforCovid;
import com.covid.model.InfoCovidDTO;
import com.covid.model.request.InfoCovidRequestDTO;
import org.mapstruct.Mapper;


@Mapper
public interface InfoCovidConverter extends CommonConverter<InforCovid, InfoCovidDTO> {
    InforCovid toEntity(InfoCovidRequestDTO model);
}
