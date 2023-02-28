package com.covid.api.user;

import com.covid.exception.CommonException;
import com.covid.model.InfoCovidDTO;
import com.covid.model.query.CommonQuery;
import com.covid.model.request.InfoCovidRequestDTO;
import com.covid.processor.InfoCovidProcessor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user/info-covid")
public class InfoCovidUserApi {

    private InfoCovidProcessor processor;

    public InfoCovidUserApi(InfoCovidProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/total")
    public InfoCovidDTO getDetail() { return processor.getDetail(); }

}

