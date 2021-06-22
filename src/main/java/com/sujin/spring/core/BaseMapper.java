package com.sujin.spring.core;

import java.util.List;

/**
 * Conversion between Entity to Dto and vise-versa
 *
 * @param <S> Entity
 * @param <D> Dto
 */
public abstract class BaseMapper<S, D> {

    public static final String SPRING_MODEL = "spring";

    public abstract D mapEntityToDto(S s);

    public abstract S mapDtoToEntity(D d);

    public abstract List<D> mapEntitiesToDtos(List<S> s);

    public abstract List<S> mapDtosToEntities(List<D> d);
}
