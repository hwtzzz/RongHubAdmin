package com.hwt.ronghub.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hwt.ronghub.entity.GourmetSpecialties;
import com.hwt.ronghub.mapper.GourmetSpecialtiesMapper;
import com.hwt.ronghub.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Auther hwtzzZ
 * 创建于  2024-11-11
 * @Version: 1.0
 */
@Service
public class GourmetSpecialtiesService {

    @Autowired
    private GourmetSpecialtiesMapper gourmetSpecialtiesMapper;

    public List<GourmetSpecialties> getGourmetSpecialtiesByType(Integer gsType) {
        return gourmetSpecialtiesMapper.selectList(
                new LambdaQueryWrapper<GourmetSpecialties>()
                        .eq(GourmetSpecialties::getGsType, gsType));
    }

    public Page<GourmetSpecialties> getGSList(Integer currentPage, Integer pageSize) {
        Page<GourmetSpecialties> gourmetSpecialtiesPage = new Page<>(currentPage, pageSize);
        return gourmetSpecialtiesMapper.selectPage(gourmetSpecialtiesPage, null);
    }

    public void updateGS(GourmetSpecialties gs) {
        gourmetSpecialtiesMapper.updateById(gs);
    }

    public Page<GourmetSpecialties> addGS(GourmetSpecialties gs) {
        gs.setPublicTime(new Date());
        gourmetSpecialtiesMapper.insert(gs);
        return getGSList(1, 5);
    }

    public Page<GourmetSpecialties> deleteGS(Integer gourmetSpecialtiesId) {
        gourmetSpecialtiesMapper.deleteById(gourmetSpecialtiesId);
        return getGSList(1, 5);
    }

    public List<GourmetSpecialties> searchGS(String name) {
        return gourmetSpecialtiesMapper.selectList(new LambdaQueryWrapper<GourmetSpecialties>()
                .like(GourmetSpecialties::getName, name));
    }

    public GourmetSpecialties getGSBygourmetSpecialtiesId(Integer gourmetSpecialtiesId) {
        return gourmetSpecialtiesMapper.selectById(gourmetSpecialtiesId);
    }
}
