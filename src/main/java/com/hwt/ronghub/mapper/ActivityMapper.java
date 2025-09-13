package com.hwt.ronghub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwt.ronghub.dto.ActivityListDto;
import com.hwt.ronghub.entity.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther hwtzzZ
 * 创建于  2024-11-24
 * @Version: 1.0
 */
@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {


    @Select("" +
            "SELECT\n" +
            "\ta.activityId,\n" +
            "\ta.image,\n" +
            "\ta.userId,\n" +
            "\ta.support,\n" +
            "\ta.publishTime,\n" +
            "\tu.nickname,\n" +
            "\tu.avatar \n" +
            "FROM\n" +
            "\tactivity a\n" +
            "\tLEFT JOIN USER u ON a.userId = u.userId \n" +
            "ORDER BY\n" +
            "\ta.support DESC limit 4" +
            "")
    List<ActivityListDto> getHotActivity();

    @Select("" +
            "SELECT\n" +
            "\ta.activityId,\n" +
            "\ta.image,\n" +
            "\ta.userId,\n" +
            "\ta.support,\n" +
            "\ta.publishTime,\n" +
            "\tu.nickname,\n" +
            "\tu.avatar \n" +
            "FROM\n" +
            "\tactivity a\n" +
            "\tLEFT JOIN USER u ON a.userId = u.userId \n" +
            "ORDER BY\n" +
            "\ta.publishTime DESC" +
            "")
    List<ActivityListDto> getNewActivity();

}
