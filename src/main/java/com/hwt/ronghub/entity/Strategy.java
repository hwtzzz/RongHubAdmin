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
 * 个人旅游攻略
 *
 * @Auther hwtzzZ
 * 创建于  2024-10-09
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Strategy {

    // 旅游攻略编号
    @TableId(value = "strategyId", type = IdType.AUTO)
    private Integer strategyId;

    // 发布人编号
    private Integer userId;

    // 攻略标题
    private String title;

    // 攻略内容
    private String content;

    // 发布时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConsts.dateTimeFormatPattern, timezone = "GMT+8")
    @DateTimeFormat(pattern = AppConsts.dateTimeFormatPattern)
    private Date publishTime;

    // 攻略的照片（可能以逗号分隔的字符串表示多张照片）
    private String image;

    //关联的景区id
    private Integer scenicId;

    //浏览量
    private Integer view;

    //审核是否通过 1未审核 2审核通过 3审核未通过
    private Integer audit;

}
