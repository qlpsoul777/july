package com.qlp.core.orm;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;



@NoRepositoryBean
public interface MyRepository<T, ID extends Serializable>
  extends CrudRepository<T, ID> {
	
}

