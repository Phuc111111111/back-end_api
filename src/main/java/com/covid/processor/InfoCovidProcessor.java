package com.covid.processor;

import com.covid.converter.InfoCovidConverter;
import com.covid.entity.InforCovid;
import com.covid.exception.CommonException;
import com.covid.model.InfoCovidDTO;
import com.covid.model.query.CommonQuery;
import com.covid.model.request.InfoCovidRequestDTO;
import com.covid.service.InfoCovidService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.persistence.Tuple;
import java.util.Objects;

@Component
public class InfoCovidProcessor {

    private InfoCovidService service ;
    private InfoCovidConverter infoCovidConverter ;

    public InfoCovidProcessor(InfoCovidService infoCovidService,
                              InfoCovidConverter infoCovidConverter) {
        this.service = infoCovidService;
        this.infoCovidConverter = infoCovidConverter;
    }

    public void add(InfoCovidRequestDTO infoCovidDTO) throws CommonException {
        service.save(infoCovidConverter.toEntity(infoCovidDTO));
    }

    public void update (InfoCovidRequestDTO infoCovidDTO, Long id) throws CommonException {
        service.update(infoCovidDTO, id);
    }

    public void delete(Long id) throws CommonException {
        InforCovid inforCovid = service.findById(id).orElse(null);
        if(Objects.isNull(inforCovid)){
            throw  new CommonException("notFound.infoCovid") ;
        }
        service.deleteById(id);
    }

    public Page<InfoCovidDTO> get(CommonQuery model) {
        return service.get(model).map(infoCovidConverter::toModel);
    }

    public InfoCovidDTO findById(Long id){
        return infoCovidConverter.toModel(service.findById(id).get()) ;
    }

    public InfoCovidDTO getDetail() {

        Tuple data = service.getDetail();
        return InfoCovidDTO.builder()
                .numberOfDeaths(Integer.parseInt(data.get("numberOfDeaths", Long.class).toString()))
                .numberRecoveredCase(Integer.parseInt(data.get("numberRecoveredCase", Long.class).toString()))
                .numberOfNfections(Integer.parseInt(data.get("numberOfNfections", Long.class).toString()))
                .build();
    }
}
