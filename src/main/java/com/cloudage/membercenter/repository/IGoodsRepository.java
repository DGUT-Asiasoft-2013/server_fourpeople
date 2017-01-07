package com.cloudage.membercenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.Goods;

@Repository
public interface IGoodsRepository extends PagingAndSortingRepository<Goods, Integer>{
	@Query("from Goods goods where goods.mall.user.id = ?1")
	Page<Goods> findAllByUserId(Integer userid,  Pageable page);
	@Query("from Goods goods where goods.mall.id = ?1")
	Page<Goods> getGoodsByMall(Integer mallId, Pageable page);
}
