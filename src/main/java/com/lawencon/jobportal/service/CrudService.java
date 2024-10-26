package com.lawencon.jobportal.service;

public interface CrudService<T, C, U, R> {
  T getEntityById(String id);

  R getByid(String id);


  void create(C request);

  void update(U request);

  void delete(String id);
}
