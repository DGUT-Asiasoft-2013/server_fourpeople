package com.cloudage.membercenter.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.Likes;

@Repository
public interface ILikesRepository extends PagingAndSortingRepository<Likes, Likes.Key> {
	
	@Query("select count(*) from Likes likes where likes.id.article.id = :articleId")
	int likeCountsOfArticle(int articleId);

}
