package com.qlp.core.orm;


import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public class MyRepositoryImpl<T, ID extends Serializable>
  extends SimpleJpaRepository<T, ID> implements MyRepository<T, ID> {

  @SuppressWarnings("unused")
private final EntityManager entityManager;

  @SuppressWarnings({ "rawtypes", "unchecked" })
public MyRepositoryImpl(JpaEntityInformation entityInformation,
                          EntityManager entityManager) {
    super(entityInformation, entityManager);
    this.entityManager = entityManager;
  }

}


