package com.smr.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.smr.domain.BaseModel;
import com.smr.service.GenericService;

import java.util.List;


public abstract class AbstractFormController<T extends BaseModel> {

    public abstract GenericService getGenericService();

    @RequestMapping(
            value = "objects/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> findById(@PathVariable("id") Long id) {
        T t = (T)getGenericService().findById(id);
        if (t == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    @RequestMapping(
            value = "objects",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> findAll() {
        List<T> list = getGenericService().findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(
            value = "objects/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<T> delete(@PathVariable("id") Long id) {

        T t = (T)getGenericService().findById(id);
        if (t == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        getGenericService().delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "objects",
            method = RequestMethod.DELETE)
    public ResponseEntity<T> deleteAll() {
        getGenericService().deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}