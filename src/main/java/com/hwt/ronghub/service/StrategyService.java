package com.hwt.ronghub.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hwt.ronghub.dto.StrategyDetailDto;
import com.hwt.ronghub.dto.StrategyListDto;
import com.hwt.ronghub.dto.StrategySearchDto;
import com.hwt.ronghub.entity.Strategy;
import com.hwt.ronghub.entity.StrategyImage;
import com.hwt.ronghub.mapper.StrategyImageMapper;
import com.hwt.ronghub.mapper.StrategyMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther hwtzzZ
 * 创建于  2024-10-09
 * @Version: 1.0
 */
@Service
public class StrategyService {

    @Autowired
    private StrategyMapper strategyMapper;
    @Autowired
    private StrategyImageMapper strategyImageMapper;

    public List<Strategy> getAllStrategy(Strategy strategy) {
        if (strategy.getTitle().equals("全部")) {
            return strategyMapper.selectList(new LambdaQueryWrapper<Strategy>()
                    .eq(Strategy::getAudit , "2"));
        }
        return strategyMapper.selectList(new LambdaQueryWrapper<Strategy>()
                .like(Strategy::getTitle, strategy.getTitle())
                .eq(Strategy::getAudit , "2"));
    }

    public List<StrategyListDto> getAllStrategyList() {
        return strategyMapper.getAllStrategyList();
    }

    public List<StrategyListDto> selectStrategy(StrategySearchDto strategy) {
        return strategyMapper.selectStrategy(strategy);
    }

    public StrategyDetailDto getStrategyById(Integer strategyId) {
        StrategyDetailDto s = strategyMapper.getStrategyById(strategyId);
        StrategyDetailDto dto = new StrategyDetailDto();
        BeanUtils.copyProperties(s, dto);
        //查找景区的图片
        List<StrategyImage> strategyImages = strategyImageMapper
                .selectList(new LambdaQueryWrapper<StrategyImage>()
                        .eq(StrategyImage::getStrategyId, strategyId));
        //过滤图片信息
        List<String> collect = strategyImages.stream()
                .map(StrategyImage::getImage)
                .collect(Collectors.toList());
        dto.setImages(collect);
        return dto;
    }


    public void updateStrategyView(Integer strategyId) {
        Strategy strategy = strategyMapper.selectById(strategyId);
        strategy.setView(strategy.getView() + 1);
        strategyMapper.updateById(strategy);
    }

    public void addStrategy(Strategy strategy) {
        strategy.setPublishTime(new Date());
        strategyMapper.insert(strategy);
    }

    public List<Strategy> getMyStrategy(Integer userId) {
        return strategyMapper.selectList(new LambdaQueryWrapper<Strategy>().eq(Strategy::getUserId , userId));
    }

    public void deleteMyStrategy(Integer strategyId) {
        strategyMapper.deleteById(strategyId);
    }

    public List<StrategyListDto> updateStrategyFlag(Integer strategyId, Integer flag) {
        Strategy strategy = strategyMapper.selectById(strategyId);
        strategy.setAudit(flag);
        strategyMapper.updateById(strategy);
        return getAllStrategyList();
    }
}
