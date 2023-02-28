package com.covid.api.admin;

import com.covid.exception.CommonException;
import com.covid.model.query.CommonQuery;
import com.covid.model.InfoCovidDTO;
import com.covid.model.request.InfoCovidRequestDTO;
import com.covid.processor.InfoCovidProcessor;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/info-covid")
public class InfoCovidApi {

    private InfoCovidProcessor processor;

    public InfoCovidApi(InfoCovidProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    public Page<InfoCovidDTO> get(CommonQuery model) {
        return processor.get(model);
    }

    @GetMapping("/total")
    public InfoCovidDTO getDetail() { return processor.getDetail(); }

    @GetMapping("/{id}")
    public InfoCovidDTO findById(@PathVariable Long id) throws CommonException {
        return processor.findById(id) ;
    }

    @PostMapping
    public void add(@RequestBody @Valid InfoCovidRequestDTO model) throws CommonException {
        processor.add(model);
    }

    @PutMapping("/{id}")
    public void edit(@Valid @RequestBody InfoCovidRequestDTO model, @PathVariable Long id) throws CommonException {
        processor.update(model, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws CommonException {
        processor.delete(id);
    }

}

