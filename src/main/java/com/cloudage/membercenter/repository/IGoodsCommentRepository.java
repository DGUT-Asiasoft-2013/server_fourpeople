package com.cloudage.membercenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.GoodsComment;

@Repository
public interface IGoodsCommentRepository extends PagingAndSortingRepository<GoodsComment, Integer>{
	@Query("from GoodsComment goodsComment where goodsComment.goods.id=?1")
	List<GoodsComment> findGoodsCommentByGoodsId(Integer currentGoodsId);

}
