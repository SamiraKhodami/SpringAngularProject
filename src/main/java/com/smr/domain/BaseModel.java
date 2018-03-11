package com.smr.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class BaseModel implements Serializable {
	//we can put all the common functionalities of models here

    private Stream<Field> getAllFieldsInHierarchy(Class classModel){
        Stream<Field> fieldStream = Arrays.stream(classModel.getDeclaredFields());
        if(classModel.getSuperclass() !=null){
            fieldStream = Stream.concat(fieldStream , getAllFieldsInHierarchy(classModel.getSuperclass()));
        }
        return fieldStream;
    }

    @Override
    public String toString() {
        Class classModel = this.getClass();

        return getAllFieldsInHierarchy(classModel)
                .map(f -> {
                    try {
                        f.setAccessible(true);
						
                        return String.format("%s = %s", f.getName(), f.get(this));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return "";
                })
                .collect(Collectors.joining(",", classModel.getSimpleName()+"{", "}"));
    }
}