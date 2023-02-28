package com.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CommonRepository <E, ID, R extends JpaRepository<E, ID>>  {

    protected R repo;

    public CommonRepository(R repo) {
        this.repo = repo;
    }
}
