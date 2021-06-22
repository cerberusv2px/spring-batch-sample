package com.sujin.spring.core;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.sujin.spring.core.exception.PersistenceConstraintViolationException;

/**
 * Generic Service Interface
 *
 * Methods that are generic in multiple scenarios will be added into it and
 * implemented into BaseServiceImpl
 *
 * @param <T> Entity
 * @param <I> type of Entity ID
 */
public interface Service<T, I> {

    T save(T t) throws PersistenceConstraintViolationException;

    List<T> saveAll(List<T> entities) throws PersistenceConstraintViolationException, PersistenceConstraintViolationException;

    Optional<T> findOne(I i);

    List<T> findAll();

    void delete(T entity);

    void deleteById(I i);

    Page<T> findPageableBySpec(Map<String, String> filterParams, Pageable pageable);

    List<T> findAllBySpec(Map<String, String> filterParams);

    public List<T> findAllBySpec(Map<String, String> filterParams, Sort sort);

    Optional<T> findOneBySpec(Map<String, String> filterParams);
}
