package com.cll.crawler.mapper;

import com.cll.crawler.entity.Exam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author chenliangliang
 * @date 2017/12/11
 */
@Mapper
@Component
public interface ExamMapper {

   int save(Exam exam);


}
