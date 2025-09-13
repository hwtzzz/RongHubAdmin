package com.hwt.ronghub.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hwt.ronghub.entity.ScenicImage;
import com.hwt.ronghub.mapper.ScenicImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 创建于  2025-03-17
 *
 * @Version: 1.0
 */
@Service
public class ScenicImageService {

    @Autowired
    ScenicImageMapper scenicImageMapper;
    @Autowired
    ScenicService scenicService;

    public List<ScenicImage> getScenicImageList(Integer scenicId) {
        return scenicImageMapper.selectList(new LambdaQueryWrapper<ScenicImage>()
                .eq(ScenicImage::getScenicId, scenicId));
    }

    public List<ScenicImage> deleteScenicImage(Integer id) {
        ScenicImage scenicImage = scenicImageMapper.selectById(id);
        scenicImageMapper.deleteById(id);
        return getScenicImageList(scenicImage.getScenicId());
    }

    public List<ScenicImage> addImage(MultipartFile file, Integer scenicId) {
        String s = scenicService.uploadAvatar2(file);
        ScenicImage scenicImage = new ScenicImage();
        scenicImage.setImage(s);
        scenicImage.setScenicId(scenicId);
        scenicImageMapper.insert(scenicImage);
        return  getScenicImageList(scenicId);
    }
}
