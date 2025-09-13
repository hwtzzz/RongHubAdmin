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
 * 攻略评论
 *
 * @Auther hwtzzZ
 * 创建于  2024-10-09
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StrategyReview {

    // 攻略评论的编号
    @TableId(value = "strategyReviewId", type = IdType.AUTO)
    private Integer strategyReviewId;

    // 评论内容
    private String content;

    // 评论时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConsts.dateTimeMinuteFormatPattern, timezone = "GMT+8")
    @DateTimeFormat(pattern = AppConsts.dateTimeMinuteFormatPattern)
    private Date contentTime;

    // 评论人编号
    private Integer userId;

    // 此评论属于哪一个攻略
    private Integer strategyId;

}
