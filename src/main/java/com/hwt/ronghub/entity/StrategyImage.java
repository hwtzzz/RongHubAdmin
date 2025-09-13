package com.hwt.ronghub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther hwtzzZ
 * 创建于  2024-11-22
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StrategyImage {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer strategyId;

    private String image;

}
