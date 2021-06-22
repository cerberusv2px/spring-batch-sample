package com.sujin.spring.core;

import java.util.Optional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.sujin.spring.core.exception.PersistenceConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class CrudController<E extends BaseEntity, D, I> {

    protected static final String SUCCESS = "success";
    protected static final String ERROR = "error";

    protected static final String SUCCESS_MESSAGE = "Operation Successful!";
    protected static final String VALIDATION_MESSAGE = "Validation Failed!";
    private static final String ERROR_MESSAGE = "Operation Failed!";

    protected final Service<E, I> service;
    protected final BaseMapper<E, D> mapper;

    protected final String templateDirectory;

    protected CrudController(String templateDirectory, Service<E, I> service,
        BaseMapper<E, D> mapper) {

        this.templateDirectory = templateDirectory;

        this.service = service;
        this.mapper = mapper;
    }

    protected abstract String getDefaultUrl();

    protected boolean canCreate() {
        return true;
    }

    protected boolean canUpdate() {
        return true;
    }

    protected boolean canDelete() {
        return true;
    }

    @GetMapping("")
    public String index(Model model) {

        model.addAttribute("records", mapper.mapEntitiesToDtos(service.findAll()));

        return templateDirectory + "index";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("dto") D dto) {

        if (!canCreate()) {
            throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED,
                "Create is not allowed in " + getDefaultUrl());
        }

        return templateDirectory + "create";
    }

    @PostMapping("/create")
    public String save(@ModelAttribute("dto") @Valid D dto,
        BindingResult validationResult,
        Model model,
        RedirectAttributes redirect, Authentication auth) {

        if (!canCreate()) {
            throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED,
                "Create is not allowed in " + getDefaultUrl());
        }

        validateBeforeCreate(dto, validationResult);

        if (validationResult.hasErrors()) {
            model.addAttribute(ERROR, VALIDATION_MESSAGE);
            return templateDirectory + "create";
        }

        //final User u = (User) auth.getPrincipal();

        final E entity = mapper.mapDtoToEntity(dto);
        //entity.setCreatedBy(u.getId());

        try {
            beforeSave(entity);

            final E saved = service.save(entity);

            afterCreate(saved);

            redirect.addFlashAttribute(SUCCESS, SUCCESS_MESSAGE);

            return "redirect:" + getRedirectUrl(saved);
        } catch (PersistenceConstraintViolationException e) {
            log.error("Error while saving record {}", entity, e);

            model.addAttribute(ERROR,
                "Error occurred while saving records (" + e.getLocalizedMessage() + ")");
            return templateDirectory + "create";
        }
    }

    @GetMapping("/{id}/update")
    public String edit(@PathVariable I id,
        @ModelAttribute("dto") D dto,
        Model model, RedirectAttributes redirect) {
        final Optional<E> entity = service.findOne(id);

        if (!canUpdate()) {
            throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED,
                "Update is not allowed in " + getDefaultUrl());
        }

        if (entity.isPresent()) {
            final D editDto = mapper.mapEntityToDto(entity.get());
            beforeUpdateFormRender(editDto);

            model.addAttribute("dto", editDto);
            return templateDirectory + "update";
        }

        redirect.addFlashAttribute(ERROR, ERROR_MESSAGE);

        return "redirect:" + getDefaultUrl();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable I id,
        @ModelAttribute("dto") @Valid D dto,
        BindingResult validationResult,
        Model model, RedirectAttributes redirect,
        Authentication auth) {

        if (!canUpdate()) {
            throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED,
                "Update is not allowed in " + getDefaultUrl());
        }

        validateBeforeUpdate(dto, validationResult);

        if (validationResult.hasErrors()) {
            model.addAttribute(ERROR, VALIDATION_MESSAGE);
            return templateDirectory + "update";
        }

        final E entity = mapper.mapDtoToEntity(dto);

//        final User u = (User) auth.getPrincipal();
//        entity.setModifiedBy(u.getId());

        try {
            beforeSave(entity);

            final E saved = service.save(entity);
            afterUpdate(saved);

            redirect.addFlashAttribute(SUCCESS, SUCCESS_MESSAGE);

            return "redirect:" + getRedirectUrl(saved);
        } catch (PersistenceConstraintViolationException e) {
            log.error("Error while saving record {}", entity, e);

            model.addAttribute(ERROR,
                "Error occurred while saving records (" + e.getLocalizedMessage() + ")");
            return templateDirectory + "create";
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable I id, RedirectAttributes redirect) {

        if (!canDelete()) {
            throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED,
                "Delete is not allowed in " + getDefaultUrl());
        }

        final Optional<E> optional = service.findOne(id);

        if (optional.isPresent()) {
            service.deleteById(id);

            afterDelete(optional.get());
            redirect.addFlashAttribute(SUCCESS, SUCCESS_MESSAGE);
            return "redirect:" + getRedirectUrl(optional.get());
        }

        redirect.addFlashAttribute(SUCCESS, SUCCESS_MESSAGE);

        return "redirect:" + getDefaultUrl();
    }

    protected String getRedirectUrl(E entity) {
        return getDefaultUrl();
    }

    protected void validateBeforeCreate(D dto, BindingResult validationResult) {
    }

    protected void validateBeforeUpdate(D dto, BindingResult validationResult) {
    }

    protected void beforeUpdateFormRender(D dto) {
    }

    protected void beforeSave(E entity) {
    }

    protected void afterCreate(E entity) {
    }

    protected void afterUpdate(E entity) {
    }

    protected void afterDelete(E entity) {
    }
}
