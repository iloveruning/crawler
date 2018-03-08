package com.cll.crawler.dao;

import com.cll.crawler.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author chenliangliang
 * @date: 2017/12/10
 */
@Repository
public interface JobRepository extends JpaRepository<Job,String> {

    @Query(value = "update job set info=:info where id=:id",nativeQuery = true)
    void updateInfoById(@Param("info") String info, @Param("id") String id);
}
