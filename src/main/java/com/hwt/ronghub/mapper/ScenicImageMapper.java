package com.hwt.ronghub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwt.ronghub.dto.ScenicListDto;
import com.hwt.ronghub.entity.ScenicImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther hwtzzZ
 * 创建于  2024-11-08
 * @Version: 1.0
 */
@Mapper
public interface ScenicImageMapper extends BaseMapper<ScenicImage> {

    @Select("SELECT s.name as text,s.scenicId as value FROM scenic s")
    List<ScenicListDto> getScenicList();
}
