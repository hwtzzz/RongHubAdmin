package com.hwt.ronghub.service;

import com.hwt.ronghub.dto.StrategyReviewListDto;
import com.hwt.ronghub.entity.StrategyReview;
import com.hwt.ronghub.mapper.StrategyReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Auther hwtzzZ
 * 创建于  2024-10-09
 * @Version: 1.0
 */
@Service
public class StrategyReviewService {

    @Autowired
    private StrategyReviewMapper strategyReviewMapper;

    public List<StrategyReviewListDto> getStrategyReviewById(Integer strategyReviewId) {
        return strategyReviewMapper.getStrategyReviewById(strategyReviewId);
    }

    public List<StrategyReviewListDto> addStrategyReview(StrategyReview strategyReview) {
        strategyReview.setContentTime(new Date());
        strategyReviewMapper.insert(strategyReview);
        return getStrategyReviewById(strategyReview.getStrategyId());
    }

}
