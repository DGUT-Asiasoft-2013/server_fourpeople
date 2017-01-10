package com.cloudage.membercenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.Mall;
import com.cloudage.membercenter.entity.MallLike;

@Repository
public interface IMallLikeRepository extends PagingAndSortingRepository<MallLike, MallLike.Key>{
	@Query("select count(*) from MallLike mallLike where mallLike.id.user.id = ?1 and mallLike.id.mall.id = ?2") 
	int checkLikesExsists(Integer id, int mallId);
	@Query("from Mall mall1,MallLike mallLike where mallLike.id.user.id = ?1 and mallLike.id.mall.id =mall1.id ")
	List<Mall> getFavorite(Integer userId);

}
