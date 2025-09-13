package com.hwt.ronghub.controller;

import com.hwt.ronghub.dto.StrategyReviewListDto;
import com.hwt.ronghub.entity.StrategyReview;
import com.hwt.ronghub.service.StrategyReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther hwtzzZ
 * 创建于  2024-10-09
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/strategyReview")
public class StrategyReviewController {

    @Autowired
    private StrategyReviewService strategyReviewService;

    /**
     * 根据攻略id查找评论信息
     *
     * @param strategyReviewId 攻略id
     * @return 评论列表
     */
    @GetMapping("/getStrategyReviewById/{strategyReviewId}")
    public List<StrategyReviewListDto> getStrategyReviewById(@PathVariable Integer strategyReviewId) {
        return strategyReviewService.getStrategyReviewById(strategyReviewId);
    }

    /**
     * 添加攻略评论
     *
     * @param strategyReview 评论信息
     * @return 评论列表
     */
    @PostMapping("/addStrategyReview")
    public List<StrategyReviewListDto> addStrategyReview(@RequestBody StrategyReview strategyReview) {
        return strategyReviewService.addStrategyReview(strategyReview);
    }

}
