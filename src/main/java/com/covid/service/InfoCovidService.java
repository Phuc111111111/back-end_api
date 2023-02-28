package com.covid.service;

import com.covid.entity.Document;
import com.covid.entity.InforCovid;
import com.covid.entity.QInforCovid;
import com.covid.exception.CommonException;
import com.covid.model.query.CommonQuery;
import com.covid.model.request.InfoCovidRequestDTO;
import com.covid.repository.DocumentRepository;
import com.covid.repository.InfoCovidRepository;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.util.List;
import java.util.Objects;

@Service
public class InfoCovidService extends CommonService<InforCovid, Long,  InfoCovidRepository> {

    private EntityManager entityManager ;

    public InfoCovidService(EntityManager entityManager, InfoCovidRepository repo) {
        super(repo);
        this.entityManager = entityManager;
    }

    public Page<InforCovid> get(CommonQuery model) {
        Pageable pageable = PageRequest.of(model.getPage() - 1, model.getSize());
        JPAQuery<InforCovid> query = new JPAQuery(entityManager);
        List<InforCovid> infoCovidDTOS = query.select(QInforCovid.inforCovid).from(QInforCovid.inforCovid)
                .limit(pageable.getPageSize()).offset(pageable.getOffset())
               .fetch();
        Long count = query.fetchCount();

        return  new PageImpl<>(infoCovidDTOS, pageable, count);
    }

    public InforCovid update (InfoCovidRequestDTO infoCovidDTO, Long id) throws CommonException {
        InforCovid inforCovid = repo.findById(id).orElse(null);
        if(Objects.isNull(inforCovid)){
            throw  new CommonException("notFound.InfoCovid") ;
        }
        inforCovid.setNumberOfDeaths(infoCovidDTO.getNumberOfDeaths());
        inforCovid.setNumberOfNfections(infoCovidDTO.getNumberOfNfections());
        inforCovid.setNumberRecoveredCase(infoCovidDTO.getNumberRecoveredCase());
        return repo.save(inforCovid);
    }

    public Tuple getDetail (){
        return repo.getTotalIfoCovid();
    }

}
