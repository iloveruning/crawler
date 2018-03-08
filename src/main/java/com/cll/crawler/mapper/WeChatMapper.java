package com.cll.crawler.mapper;

import com.cll.crawler.entity.WeChat;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chenliangliang
 * @date 2017/12/11
 */
@Mapper
@Component
public interface WeChatMapper {

   int save(List<WeChat> list);

}
