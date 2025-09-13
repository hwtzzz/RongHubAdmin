package com.hwt.ronghub.dto;

import com.hwt.ronghub.entity.Scenic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Auther hwtzzZ
 * 创建于  2024-11-08
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScenicDetailDto extends Scenic {

    private List<String> images;

}
