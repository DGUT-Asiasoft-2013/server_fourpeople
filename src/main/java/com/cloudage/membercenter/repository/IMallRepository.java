package com.cloudage.membercenter.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.Mall;
import com.cloudage.membercenter.entity.User;
@Repository
public interface IMallRepository extends PagingAndSortingRepository<Mall, Integer>{
	@Query("from Mall mall where mall.user = ?1")
	Mall ishaveshop(User user);
    @Query("from Mall mall where mall.user.id=?1")
	Mall findMallByUserId(Integer userid);

}
