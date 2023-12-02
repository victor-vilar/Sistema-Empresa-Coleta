package com.victorvilar.projetoempresa.services.interfaces;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * Interface with basic crud methods
 *
 * @param <C> class of CreateDto
 * @param <U> class of UpdateDto
 * @param <R> class of ResponseDto
 */
public interface SystemService<C,U,R> {


    public List<R> getAll();
    public R getById( Long id);
    @Transactional
    public R save(@Valid C createDto);
    @Transactional
    public void delete(List<Long> ids);
    @Transactional
    public R update(@Valid U updateDto);
}
