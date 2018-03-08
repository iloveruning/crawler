package com.cll.crawler.dao;

import com.cll.crawler.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author chenliangliang
 * @date: 2017/12/6
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer> {
}
