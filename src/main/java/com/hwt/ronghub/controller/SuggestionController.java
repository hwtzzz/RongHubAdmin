package com.hwt.ronghub.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hwt.ronghub.entity.Suggestion;
import com.hwt.ronghub.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 创建于  2025-02-10
 *
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/suggestion")
public class SuggestionController {

    @Autowired
    private SuggestionService suggestionService;

    /**
     * 用户添加建议
     *
     * @param suggestion
     */
    @PostMapping("/addSuggestion")
    public void addSuggestion(@RequestBody Suggestion suggestion) {
        suggestionService.addSuggestion(suggestion);
    }

    /**
     * 获取用户建议列表
     *
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/getAllSuggestion/{current}/{size}")
    public Page<Suggestion> getAllSuggestion(@PathVariable Integer current, @PathVariable Integer size) {
        return suggestionService.getAllSuggestion(current, size);
    }

    /**
     * 修改建议的内容（状态）
     * @param suggestionId
     * @return
     */
    @GetMapping("/updateSuggestionFlag/{suggestionId}")
    public Page<Suggestion> updateSuggestionFlag(@PathVariable Integer suggestionId) {
        return suggestionService.updateSuggestionFlag(suggestionId);
    }

    /**
     * 根据状态获取建议
     * @param flag
     * @return
     */
    @GetMapping("/getSuggestionByFlag/{flag}")
    public List<Suggestion> getSuggestionByFlag(@PathVariable Integer flag) {
        return suggestionService.getSuggestionByFlag(flag);
    }


}
