package com.hwt.ronghub.controller;

import cn.hutool.http.HttpUtil;
import com.hwt.ronghub.entity.Scenic;
import com.hwt.ronghub.mapper.DistrictMapper;
import com.hwt.ronghub.mapper.ScenicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Auther hwtzzZ
 * 创建于  2024-11-08
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/other")
public class OtherController {

    @Autowired
    private ScenicMapper scenicMapper;

    /**
     * 获取实时天气
     * @param scenicId
     * @return
     */
    @GetMapping("/getScenicBaseWeather/{scenicId}")
    public String getScenicBaseWeather(@PathVariable Integer scenicId){
        Scenic scenic = scenicMapper.selectById(scenicId);
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("key", "XXX");
        paramMap.put("city", scenic.getAdcode());
        paramMap.put("extensions", "base");
        paramMap.put("output", "JSON");
        String result3= HttpUtil.get("https://restapi.amap.com/v3/weather/weatherInfo", paramMap);
        System.out.println(result3);
        return result3;
    }

    /**
     * 获取天气预报
     * @param scenicId
     * @return
     */
    @GetMapping("/getScenicAllWeather/{scenicId}")
    public String getScenicAllWeather(@PathVariable Integer scenicId){
        Scenic scenic = scenicMapper.selectById(scenicId);
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("key", "XXX");
        paramMap.put("city", scenic.getAdcode());
        paramMap.put("extensions", "all");
        paramMap.put("output", "JSON");
        String result3= HttpUtil.get("https://restapi.amap.com/v3/weather/weatherInfo", paramMap);
        System.out.println(result3);
        return result3;
    }

}
