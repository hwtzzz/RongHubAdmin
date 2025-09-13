package com.hwt.ronghub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hwt.ronghub.config.AppConsts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 创建于  2025-01-14
 *
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityCommentList {

    private Integer activityCommentId;

    // 评论内容
    private String content;

    // 评论时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConsts.dateTimeMinuteFormatPattern, timezone = "GMT+8")
    @DateTimeFormat(pattern = AppConsts.dateTimeMinuteFormatPattern)
    private Date sendTime;

    // 评论人编号
    private Integer userId;

    //用户昵称
    private String nickname;

    //用户头像
    private String avatar;

}
