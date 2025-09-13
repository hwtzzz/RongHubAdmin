package com.hwt.ronghub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hwt.ronghub.config.AppConsts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 活动评论
 *
 * @Auther hwtzzZ
 * 创建于  2024-11-24
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityComment {

    // 活动的编号
    @TableId(value = "activityCommentId", type = IdType.AUTO)
    private Integer activityCommentId;

    //发布人
    private Integer userId;

    //评论内容
    private String content;

    //评论时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConsts.dateFormatPattern, timezone = "GMT+8")
    @DateTimeFormat(pattern = AppConsts.dateFormatPattern)
    private Date sendTime;

}
