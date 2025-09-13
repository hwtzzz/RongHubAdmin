package com.hwt.ronghub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 统计页面数据
 *
 * @Auther hwtzzZ
 * 创建于  2024-10-14
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsDataDto {

    //系统总人数
    private Integer systemUserNumber;

    //平台访问量
    private Integer systemVisited;

    //景区数量
    private Integer scenicNumber;

    //平台开放天数
    private long openDayNumber;

    private List<AddressCountDto> list;

}
