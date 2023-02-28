package com.covid.repository;

import com.covid.entity.InforCovid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;

@Repository
public interface InfoCovidRepository extends JpaRepository<InforCovid,Long> {

    @Query(value="select sum(info.numberOfNfections) as numberOfNfections , " +
            "sum(info.numberRecoveredCase) as numberRecoveredCase , " +
            "sum(info.numberOfDeaths) as numberOfDeaths " +
            "from InforCovid as info")
    Tuple getTotalIfoCovid();

}
