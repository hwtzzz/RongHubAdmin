package com.hwt.ronghub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwt.ronghub.dto.ActivityCommentList;
import com.hwt.ronghub.entity.ActivityLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 创建于  2025-01-14
 *
 * @Version: 1.0
 */
@Mapper
public interface ActivityLikeMapper extends BaseMapper<ActivityLike> {


    @Select("" +
            "SELECT\n" +
            "\ta.activityCommentId,\n" +
            "\ta.content,\n" +
            "\ta.sendTime,\n" +
            "\ta.userId,\n" +
            "\tu.nickname,\n" +
            "\tu.avatar \n" +
            "FROM\n" +
            "\tactivity_comment a\n" +
            "\tLEFT JOIN USER u ON u.userId = a.userId" +
            "")
    List<ActivityCommentList> getActivityComment();
}
