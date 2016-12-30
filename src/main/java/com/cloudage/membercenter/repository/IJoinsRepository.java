package com.cloudage.membercenter.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.Joins;

@Repository
public interface IJoinsRepository extends PagingAndSortingRepository<Joins,String>{

}
