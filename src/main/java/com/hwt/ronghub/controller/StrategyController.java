package com.hwt.ronghub.controller;

import com.hwt.ronghub.dto.*;
import com.hwt.ronghub.entity.Strategy;
import com.hwt.ronghub.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther hwtzzZ
 * 创建于  2024-10-09
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/strategy")
public class StrategyController {

    @Autowired
    private StrategyService strategyService;

    /**
     * 小程序前端搜索攻略
     *
     * @param strategy 攻略名称
     * @return 搜索后的攻略列表
     */
    @PostMapping("/getAllStrategy")
    public List<Strategy> getAllStrategy(@RequestBody Strategy strategy) {
        return strategyService.getAllStrategy(strategy);
    }

    /**
     * 管理后台获取攻略列表
     *
     * @param current 第几页
     * @param size    数量
     * @return 列表
     */
    @GetMapping("/getAllStrategyList/{current}/{size}")
    public PageDtoUtil getAllStrategyList(@PathVariable Integer current, @PathVariable Integer size) {
        List<StrategyListDto> allStrategyList = strategyService.getAllStrategyList();
        return PageDtoUtil.getPageDto(allStrategyList, current, size);
    }

    /**
     * 管理系统查询
     *
     * @param strategy 传来的字段字段
     * @return 攻略列表
     */
    @PostMapping("/selectStrategy")
    public List<StrategyListDto> getAllStrategyList(@RequestBody StrategySearchDto strategy) {
        return strategyService.selectStrategy(strategy);
    }

    /**
     * 根据id查询攻略信息
     * @param strategyId
     * @return
     */
    @GetMapping("/getStrategyById/{strategyId}")
    public StrategyDetailDto getStrategyById(@PathVariable Integer strategyId) {
        return strategyService.getStrategyById(strategyId);
    }

    /**
     * 攻略浏览量加1
     * @param strategyId
     */
    @GetMapping("/updateStrategyView/{strategyId}")
    public void updateStrategyView(@PathVariable Integer strategyId) {
        strategyService.updateStrategyView(strategyId);
    }

    /**
     * 添加攻略
     * @param strategy
     */
    @PostMapping("/addStrategy")
    public void addStrategy(@RequestBody Strategy strategy){
        strategyService.addStrategy(strategy);
    }

    /**
     * 获取用户的攻略
     * @param userId
     * @return
     */
    @GetMapping("/getMyStrategy/{userId}")
    public List<Strategy> getMyStrategy(@PathVariable Integer userId){
        return strategyService.getMyStrategy(userId);
    }

    /**
     * 删除我的攻略
     * @param strategyId
     */
    @GetMapping("/deleteMyStrategy/{strategyId}")
    public void deleteMyStrategy(@PathVariable Integer strategyId){
        strategyService.deleteMyStrategy(strategyId);
    }

    /**
     * 更新攻略的状态
     * @param strategyId
     * @param flag
     * @return
     */
    @GetMapping("/updateStrategyFlag/{strategyId}/{flag}")
    public PageDtoUtil updateStrategyFlag(@PathVariable Integer strategyId , @PathVariable Integer flag){
        List<StrategyListDto> allStrategyList = strategyService.updateStrategyFlag(strategyId , flag);
        return PageDtoUtil.getPageDto(allStrategyList, 1, 5);
    }

}
