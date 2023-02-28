package com.covid.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class InforObjectPageHome {
    private List<InforCategoryDTO> inforCategoryDTOS ;
    private Object infoDetailCovid ;
    private List<UserDTO> listDoctors ;
}
