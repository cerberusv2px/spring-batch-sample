package com.sujin.spring.core;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.sujin.spring.core.exception.PersistenceConstraintViolationException;
import lombok.extern.slf4j.Slf4j;


/**
 * Base implementation of generic service
 * All the sub-classes can override the available methods where generic operation does
 * not satisfy
 *
 * @param <T> Entity
 * @param <I> type of Entity ID
 *
 * example: CustomerService
 */
@Slf4j
public abstract class BaseService<T, I> implements Service<T, I> {

    protected final BaseRepository<T, I> repository;

    protected BaseService(
        BaseRepository<T, I> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(I i) {
        repository.deleteById(i);
    }

    @Override
    public Page<T> findPageableBySpec(Map<String, String> filterParams, Pageable pageable) {
        final BaseSpecBuilder<T> builder = getSpec(filterParams);
        final Specification<T> spec = builder.build();

        return repository.findAll(spec, pageable);
    }

    @Override
    public List<T> findAllBySpec(Map<String, String> filterParams) {
        final BaseSpecBuilder<T> builder = getSpec(filterParams);
        final Specification<T> spec = builder.build();

        return repository.findAll(spec);
    }

    @Override
    public List<T> findAllBySpec(Map<String, String> filterParams, Sort sort) {
        final BaseSpecBuilder<T> builder = getSpec(filterParams);
        final Specification<T> spec = builder.build();

        return repository.findAll(spec, sort);
    }

    @Override
    public Optional<T> findOneBySpec(Map<String, String> filterParams) {
        final BaseSpecBuilder<T> builder = getSpec(filterParams);
        final Specification<T> spec = builder.build();

        return repository.findOne(spec);
    }

    @Override
    public Optional<T> findOne(I i) {
        return repository.findById(i);
    }

    @Override
    public T save(T t) throws PersistenceConstraintViolationException {
        try {
            return repository.save(t);
        } catch (Exception e) {
            log.error("Error occurred while saving record", e);

            throw new PersistenceConstraintViolationException(e.getLocalizedMessage(), e);
        }

    }

    @Override
    public List<T> saveAll(List<T> list) throws PersistenceConstraintViolationException {
        try {
            return repository.saveAll(list);
        } catch (ConstraintViolationException | PersistenceException e) {
            log.error("Error occurred while saving record", e);

            throw new PersistenceConstraintViolationException(e.getLocalizedMessage(), e);
        }
    }

    protected abstract BaseSpecBuilder<T> getSpec(Map<String, String> filterParams);

}
