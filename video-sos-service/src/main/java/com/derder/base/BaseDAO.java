package com.derder.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/18.
 */
@NoRepositoryBean
public interface BaseDAO<T,PK extends Serializable> extends JpaRepository<T, PK>,QueryDslPredicateExecutor<T> {
}
