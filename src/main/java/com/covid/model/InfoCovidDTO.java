package com.covid.model;

import com.covid.model.BaseDTO;
import com.covid.model.enums.UserRole;
import com.covid.model.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class InfoCovidDTO extends BaseDTO {

    private Long id;
    private Integer numberOfNfections;
    private Integer numberRecoveredCase;
    private Integer numberOfDeaths;

}
