package com.hwt.ronghub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 区县信息
 *
 * @Auther hwtzzZ
 * 创建于  2024-10-09
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class District {

    // 区县id
    @TableId(value = "districtId", type = IdType.AUTO)
    private Integer districtId;

    // 区县名称
    private String name;

    // 区县编号
    private Integer adcode;

}
