package com.hwt.ronghub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 景区信息
 *
 * @Auther hwtzzZ
 * 创建于  2024-10-09
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scenic {

    // 景区编号
    @TableId(value = "scenicId", type = IdType.AUTO)
    private Integer scenicId;

    // 景区名称
    private String name;

    // 地址
    private String address;

    // 景区封面头像
    private String cover;

    // 景区分类
    private String scenicType;

    // 景区介绍
    private String content;

    // 经度
    private Double lon;

    // 纬度
    private Double lat;

    // 属于哪一个区县的编号
    private Integer adcode;

    //开放时间
    private String openTime;

    //景区浏览数量
    private Integer view;

    private String level;

}
