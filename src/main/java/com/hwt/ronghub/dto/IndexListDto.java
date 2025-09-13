package com.hwt.ronghub.dto;

import com.hwt.ronghub.entity.Scenic;
import com.hwt.ronghub.entity.Strategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Auther hwtzzZ
 * 创建于  2024-11-04
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndexListDto {

    private List<Strategy> referralStrategyList;

    private List<Scenic> referralScenicList;

}
