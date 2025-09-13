package com.hwt.ronghub.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwt.ronghub.config.AppConsts;
import com.hwt.ronghub.dto.IndexListDto;
import com.hwt.ronghub.dto.ScenicDetailDto;
import com.hwt.ronghub.dto.ScenicListDto;
import com.hwt.ronghub.entity.Scenic;
import com.hwt.ronghub.entity.ScenicImage;
import com.hwt.ronghub.entity.Strategy;
import com.hwt.ronghub.mapper.ScenicImageMapper;
import com.hwt.ronghub.mapper.ScenicMapper;
import com.hwt.ronghub.mapper.StrategyMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Auther hwtzzZ
 * 创建于  2024-10-09
 * @Version: 1.0
 */
@Service
public class ScenicService {

    @Autowired
    private ScenicMapper scenicMapper;
    @Autowired
    private StrategyMapper strategyMapper;
    @Autowired
    private ScenicImageMapper scenicImageMapper;

    public List<Scenic> getAllScenicByType(Scenic scenic) {
        if (scenic.getScenicType().equals("全部")) {
            return scenicMapper.selectList(null);
        }
        return scenicMapper.selectList(new LambdaQueryWrapper<Scenic>()
                .eq(Scenic::getScenicType, scenic.getScenicType()));
    }

    public List<Scenic> getScenicByName(String scenicName) {
        return scenicMapper.selectList(new LambdaQueryWrapper<Scenic>()
                .like(Scenic::getName, scenicName));
    }

    public IndexListDto getIndexList() {
        //查询出推荐的景点
        QueryWrapper<Scenic> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("view");
        wrapper.last("limit 3");
        List<Scenic> scenicList = scenicMapper.selectList(wrapper);
        //查询出推荐的攻略
        List<Strategy> strategyList = strategyMapper.selectList(new LambdaQueryWrapper<Strategy>()
                .eq(Strategy::getAudit, 2)
                .orderByDesc(Strategy::getView)
                .last("limit 3"));
        IndexListDto indexListDto = new IndexListDto();
        indexListDto.setReferralStrategyList(strategyList);
        indexListDto.setReferralScenicList(scenicList);
        return indexListDto;
    }

    public ScenicDetailDto getScenicById(Integer scenicId) {
        Scenic scenic = scenicMapper.selectById(scenicId);
        //找到景点
        ScenicDetailDto dto = new ScenicDetailDto();
        BeanUtils.copyProperties(scenic, dto);
        //查找景点的图片
        List<ScenicImage> scenicImages = scenicImageMapper.selectList(new LambdaQueryWrapper<ScenicImage>().eq(ScenicImage::getScenicId, scenicId));
        List<String> collect = scenicImages.stream().map(ScenicImage::getImage).collect(Collectors.toList());
        dto.setImages(collect);
        return dto;
    }


    public List<ScenicListDto> getScenicList() {
        return scenicImageMapper.getScenicList();
    }

    public String uploadAvatar2(@RequestParam("file") MultipartFile file) {
        File fileDir = new File(AppConsts.IMAGE_PATH);
        if (!fileDir.exists()) {
            //如果没有目录应该创建目录
            fileDir.mkdirs();
        }
        //获取图片后缀
        String fileSuffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
        //生成随机的文件名称 防止重复
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //生成文件保存位置名称
        String path = AppConsts.IMAGE_PATH + uuid + fileSuffix;
        //文件实现上传
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回文件访问地址
        return AppConsts.ACCESS_PATH + uuid + fileSuffix;
    }

    public String updateMyAvatar(MultipartFile file, Integer scenicId) {
        String s = uploadAvatar2(file);
        System.out.println(s);
        Scenic scenic = scenicMapper.selectById(scenicId);
        scenic.setCover(s);
        scenicMapper.updateById(scenic);
        return s;
    }

    public ScenicDetailDto updateScenic(Scenic scenic) {
        scenicMapper.updateById(scenic);
        return getScenicById(scenic.getScenicId());
    }

    public List<Scenic> getAllScenic() {
        return scenicMapper.selectList(null);
    }

    public List<Scenic> addScenic(Scenic scenic) {
        //设置默认浏览量
        scenic.setView(0);
        //设置景区默认图片
        scenic.setCover("http://localhost:8989/image/cd.jpg");
        scenicMapper.insert(scenic);
        return scenicMapper.selectList(null);
    }
}
