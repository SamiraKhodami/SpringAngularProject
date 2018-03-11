package com.smr.service;

import java.util.List;
import com.smr.domain.BaseModel;
import com.smr.dto.BaseDTO;

public interface GenericService<T extends BaseModel> {

    T save(T t);

    T findById(long id);

    List<T> findAll();

    T update(T t);

    void delete(Long id);

    void deleteAll();
}