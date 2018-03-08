package com.cll.crawler.mapper;

import com.cll.crawler.entity.Job;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author chenliangliang
 * @date: 2017/12/11
 */
@Mapper
@Component
public interface JobMapper {

    int updateInfoById(@Param("info") String info,@Param("id") String id);

    int isExist(@Param("id") String id);

    int save(Job job);

    int count();

    int updateById(Job job);

    int updateClickAndInfo(@Param("click") String click,@Param("info") String info,@Param("id") String id);
}
