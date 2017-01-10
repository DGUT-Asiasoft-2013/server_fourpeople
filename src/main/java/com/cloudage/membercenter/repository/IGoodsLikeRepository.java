package com.cloudage.membercenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.Goods;
import com.cloudage.membercenter.entity.GoodsLike;
import com.cloudage.membercenter.entity.MallLike;

@Repository
public interface IGoodsLikeRepository extends PagingAndSortingRepository<GoodsLike, GoodsLike.Key>{
	@Query("select count(*) from GoodsLike goodsLike where goodsLike.id.user.id = ?1 and goodsLike.id.goods.id = ?2") 
	int checkLikesExsists(Integer id, int goodsId);
	@Query("from Goods goods1,GoodsLike goodsLike where goodsLike.id.user.id = ?1 and goodsLike.id.goods.id = goods1.id") 
	List<Goods> getFavorite(Integer userId);

}