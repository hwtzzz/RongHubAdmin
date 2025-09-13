package com.hwt.ronghub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwt.ronghub.entity.Suggestion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.Mapping;

/**
 * 创建于  2025-02-10
 *
 * @Version: 1.0
 */
@Mapper
public interface SuggestionMapper extends BaseMapper<Suggestion> {
}
