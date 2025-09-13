package com.hwt.ronghub.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther hwtzzZ
 * 创建于  2024-11-24
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityListDto {

    // 活动的编号
    private Integer activityId;

    //发布人
    private Integer userId;

    //照片
    private String image;

    //点赞量
    private Integer support;

    //发布人昵称
    private String nickname;

    //发布人头像
    private String avatar;

    //是否点赞 1点赞  2未点赞
    private Integer like;

}
