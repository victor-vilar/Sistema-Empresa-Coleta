package com.victorvilar.projetoempresa.controllers.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


/**
 * Interface with a method to search the entity by customer
 *
 * @param <C> class of CreateDto
 * @param <U> class of UpdateDto
 * @param <R> class of ResponseDto
 */
public interface EntityOfCustomerController<C,U,R> extends SystemController<C,U,R> {

    @GetMapping("by-customer/{customerId}")
    public ResponseEntity<List<R>> getAllByCustomerId(@PathVariable String customerId);
}
