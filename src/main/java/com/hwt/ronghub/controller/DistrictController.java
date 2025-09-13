package com.hwt.ronghub.controller;

import com.hwt.ronghub.entity.District;
import com.hwt.ronghub.mapper.DistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther hwtzzZ
 * 创建于  2024-10-09
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/district")
public class DistrictController {

    @Autowired
    DistrictMapper districtMapper;

    /**
     * 获取所有区县信息
     * @return
     */
    @GetMapping("/getAllDistrict")
    public List<District> getAllDistrict(){
        return districtMapper.selectList(null);
    }

}
