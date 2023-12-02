package com.victorvilar.projetoempresa.controllers.interfaces;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Interface with basic methods for rest controllers
 *
 * @param <C> class of CreateDto
 * @param <U> class of UpdateDto
 * @param <R> class of ResponseDto
 */
public interface SystemController<C,U,R> {


    @GetMapping("/")
    public ResponseEntity<List<R>> getAll();

    @GetMapping("/{id}")
    public ResponseEntity<R> getById(@PathVariable Long id);

    @PostMapping("/")
    public ResponseEntity<R> save(@Valid @RequestBody C createDto);

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id);

    @PutMapping()
    public ResponseEntity<R> update(@Valid @RequestBody U updateDto);

}
