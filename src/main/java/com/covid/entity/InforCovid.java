package com.covid.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class InforCovid extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numberOfNfections;
    private Integer numberRecoveredCase;
    private Integer numberOfDeaths;

}
