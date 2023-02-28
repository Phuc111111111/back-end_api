package com.covid.model.request;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class InfoCovidRequestDTO {

    @NotNull
    private Integer numberOfNfections;

    @NotNull
    private Integer numberRecoveredCase;

    @NotNull
    private Integer numberOfDeaths;

}
