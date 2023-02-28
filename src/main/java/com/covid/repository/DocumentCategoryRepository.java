package com.covid.repository;

import com.covid.entity.DocumentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentCategoryRepository extends JpaRepository<DocumentCategory,Long> {
    boolean existsByName(String name) ;

    boolean existsByNameAndIdNot(String name, Long id) ;
}
