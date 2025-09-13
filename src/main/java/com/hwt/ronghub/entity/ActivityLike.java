package com.hwt.ronghub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建于  2025-01-14
 *
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityLike {

    @TableId(value = "likeId", type = IdType.AUTO)
    private Integer likeId;

    private Integer userId;

    private Integer activityId;

}
