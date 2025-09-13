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
 * 活动
 *
 * @Auther hwtzzZ
 * 创建于  2024-11-24
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

    // 活动的编号
    @TableId(value = "activityId", type = IdType.AUTO)
    private Integer activityId;

    //发布人
    private Integer userId;

    //照片
    private String image;

    //点赞量
    private Integer support;

    //发布时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConsts.dateFormatPattern, timezone = "GMT+8")
    @DateTimeFormat(pattern = AppConsts.dateFormatPattern)
    private Date publishTime;

}
