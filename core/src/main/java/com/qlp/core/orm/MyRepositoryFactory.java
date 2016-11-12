package com.qlp.core.orm;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;

public class MyRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory{

	private EntityManager entityManager;
	
	public MyRepositoryFactory(EntityManager entityManager) {
		super(entityManager);
		this.entityManager = entityManager;
	}
	
	protected Object getTargetRepository(RepositoryInformation information){
		JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(information.getDomainType());
		return new MyRepositoryImpl<>(entityInformation,this.entityManager);
	}
	
	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
		return MyRepository.class;
	}

}
