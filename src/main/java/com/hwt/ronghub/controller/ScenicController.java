package com.hwt.ronghub.controller;

import com.hwt.ronghub.config.wrapResult.DontWrapResult;
import com.hwt.ronghub.dto.IndexListDto;
import com.hwt.ronghub.dto.ScenicDetailDto;
import com.hwt.ronghub.dto.ScenicListDto;
import com.hwt.ronghub.entity.Scenic;
import com.hwt.ronghub.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Auther hwtzzZ
 * 创建于  2024-10-09
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/scenic")
public class ScenicController {

    @Autowired
    private ScenicService scenicService;

    /**
     * 根据景区类型获取景区列表
     * @param scenic
     * @return
     */
    @PostMapping("/getAllScenicByType")
    public List<Scenic> getAllScenicByType(@RequestBody Scenic scenic){
        return scenicService.getAllScenicByType(scenic);
    }

    /**
     * 获取首页的景区列表
     * @return
     */
    @GetMapping("/getIndexList")
    public IndexListDto getIndexList(){
        return scenicService.getIndexList();
    }

    /**
     * 获取景区详情
     * @param scenicId
     * @return
     */
    @GetMapping("/getScenicById/{scenicId}")
    public ScenicDetailDto getScenicById(@PathVariable Integer scenicId){
        return scenicService.getScenicById(scenicId);
    }

    /**
     * 模糊搜索景区
     * @param scenicName
     * @return
     */
    @GetMapping("/getScenicByName/{scenicName}")
    public List<Scenic> getScenicByName(@PathVariable String scenicName){
        return scenicService.getScenicByName(scenicName);
    }

    /**
     * 获取景区列表
     * @return
     */
    @GetMapping("/getScenicList")
    public List<ScenicListDto> getScenicList(){
        return scenicService.getScenicList();
    }

    /**
     * 修改景区的头像
     * @param file
     * @param scenicId
     * @return
     */
    @DontWrapResult
    @PostMapping("/updateScenicCover")
    public String updateMyAvatar(@RequestParam("file") MultipartFile file,
                                 @RequestParam("scenicId") Integer scenicId) {
        return scenicService.updateMyAvatar(file, scenicId);
    }

    /**
     * 修改景区信息
     * @param scenic
     * @return
     */
    @PostMapping("/updateScenic")
    public ScenicDetailDto updateScenic(@RequestBody Scenic scenic){
        return scenicService.updateScenic(scenic);
    }

    /**
     * 获取所有的景区
     * @return
     */
    @GetMapping("/getAllScenic")
    public List<Scenic> getAllScenic(){
        return scenicService.getAllScenic();
    }

    /**
     * 添加景区
     * @param scenic
     * @return
     */
    @PostMapping("/addScenic")
    public List<Scenic> addScenic(@RequestBody Scenic scenic){
        return scenicService.addScenic(scenic);
    }

}
