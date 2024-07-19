package com.example.shared.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IGenericService<T> {

    T add(T entity);

    T update(long id, Map<Object,Object> fields);

    boolean delete(long id);



}