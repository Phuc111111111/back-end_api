package com.covid.service;

import com.covid.repository.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public abstract class CommonService <E, ID, R extends JpaRepository<E, ID>> extends CommonRepository<E, ID, R > {

    public CommonService(R repo) {
        super(repo);
    }

    public Optional<E> findById(ID id) {
      return repo.findById(id);
    }

    public void deleteById(ID id) {
        repo.deleteById(id);
    }

    public E save(E s) {
       return repo.save(s);
    }
}
