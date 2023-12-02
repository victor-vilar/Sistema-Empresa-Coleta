package com.victorvilar.projetoempresa.services.interfaces;

import com.victorvilar.projetoempresa.controllers.interfaces.SystemController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Interface with a method to search the entity by customer
 *
 * @param <C> class of CreateDto
 * @param <U> class of UpdateDto
 * @param <R> class of ResponseDto
 */
public interface EntityOfCustomerService<C,U,R> extends SystemService<C,U,R> {
    public List<R> getAllByCustomerId(@PathVariable String customerId);
}
