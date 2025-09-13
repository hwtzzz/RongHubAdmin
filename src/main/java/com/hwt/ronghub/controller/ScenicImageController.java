package com.hwt.ronghub.controller;

import com.hwt.ronghub.entity.ScenicImage;
import com.hwt.ronghub.service.ScenicImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 创建于  2025-03-17
 *
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/scenicImage")
public class ScenicImageController {

    @Autowired
    ScenicImageService scenicImageService;

    /**
     * 根据景区编号获取景区的图片信息
     * @param scenicId
     * @return
     */
    @GetMapping("/getScenicImageList/{scenicId}")
    public List<ScenicImage> getScenicImageList(@PathVariable Integer scenicId){
        return scenicImageService.getScenicImageList(scenicId);
    }

    /**
     * 删除景区图片
     * @param id
     * @return
     */
    @GetMapping("deleteScenicImage/{id}")
    public List<ScenicImage> deleteScenicImage(@PathVariable Integer id){
        return scenicImageService.deleteScenicImage(id);
    }

    /**
     * 添加景区图片
     * @param file
     * @param scenicId
     * @return
     */
    @PostMapping("addImage")
    public List<ScenicImage> addImage(@RequestParam("file") MultipartFile file,
                                      @RequestParam("scenicId") Integer scenicId){
        return scenicImageService.addImage(file ,scenicId);
    }

}
