package com.hwt.ronghub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwt.ronghub.dto.StrategyReviewListDto;
import com.hwt.ronghub.entity.StrategyReview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther hwtzzZ
 * 创建于  2024-10-09
 * @Version: 1.0
 */
@Mapper
public interface StrategyReviewMapper extends BaseMapper<StrategyReview> {


    @Select("" +
            "SELECT\n" +
            "\ts.strategyReviewId,\n" +
            "\ts.content,\n" +
            "\ts.contentTime,\n" +
            "\ts.strategyId,\n" +
            "\tu.nickname,\n" +
            "\tu.avatar,\n" +
            "\ts.userId \n" +
            "FROM\n" +
            "\tstrategy_review s\n" +
            "\tLEFT JOIN USER u ON u.userId = s.userId \n" +
            "WHERE\n" +
            "\ts.strategyId = #{strategyReviewId}" +
            "\torder by s.strategyReviewId desc")
    List<StrategyReviewListDto> getStrategyReviewById(Integer strategyReviewId);
}
