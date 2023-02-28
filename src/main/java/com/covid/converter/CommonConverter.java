package com.covid.converter;

public interface CommonConverter<E, M> {

    E toEntity(M model);
    M toModel(E entity);

}
