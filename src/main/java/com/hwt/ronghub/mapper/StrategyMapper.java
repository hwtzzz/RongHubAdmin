package com.hwt.ronghub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwt.ronghub.dto.StrategyDetailDto;
import com.hwt.ronghub.dto.StrategyListDto;
import com.hwt.ronghub.dto.StrategySearchDto;
import com.hwt.ronghub.entity.Strategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther hwtzzZ
 * 创建于  2024-10-09
 * @Version: 1.0
 */
@Mapper
public interface StrategyMapper extends BaseMapper<Strategy> {

    @Select("" +
            "SELECT\n" +
            "\tstr.strategyId,\n" +
            "\tstr.userId,\n" +
            "\tstr.title,\n" +
            "\tstr.content,\n" +
            "\tstr.publishTime,\n" +
            "\tstr.image,\n" +
            "\tstr.scenicId,\n" +
            "\tstr.VIEW,\n" +
            "\tstr.audit,\n" +
            "\tu.username,\n" +
            "\ts.NAME \n" +
            "FROM\n" +
            "\tstrategy str\n" +
            "\tLEFT JOIN USER u ON str.userId = u.userId\n" +
            "\tLEFT JOIN scenic s ON str.scenicId = s.scenicId" +
            "")
    List<StrategyListDto> getAllStrategyList();


    List<StrategyListDto> selectStrategy(StrategySearchDto dto);

    @Select("" +
            "SELECT\n" +
            "\tstr.strategyId,\n" +
            "\tstr.userId,\n" +
            "\tstr.title,\n" +
            "\tstr.content,\n" +
            "\tstr.publishTime,\n" +
            "\tstr.image,\n" +
            "\tstr.scenicId,\n" +
            "\tstr.VIEW,\n" +
            "\tstr.audit,\n" +
            "\tu.nickname,\n" +
            "\ts.NAME \n" +
            "FROM\n" +
            "\tstrategy str\n" +
            "\tLEFT JOIN USER u ON str.userId = u.userId\n" +
            "\tLEFT JOIN scenic s ON str.scenicId = s.scenicId" +
            " where str.strategyId = #{strategyId}" +
            "")
    StrategyDetailDto getStrategyById(Integer strategyId);
}
