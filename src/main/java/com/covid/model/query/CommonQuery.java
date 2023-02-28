package com.covid.model.query;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;


@Getter
@Setter

public class CommonQuery {

    @Min(value = 1, message = "page.min")
    Integer page = 1;

    @Min(value = 1, message = "page.size.min")
    Integer size = 10;

    String keyword;
}
