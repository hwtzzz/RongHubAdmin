package com.hwt.ronghub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 景区的轮播图
 * @Auther hwtzzZ
 * 创建于  2024-11-08
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScenicImage {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer scenicId;

    private String image;

}
