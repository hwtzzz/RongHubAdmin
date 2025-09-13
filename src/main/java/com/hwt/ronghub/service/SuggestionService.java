package com.hwt.ronghub.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hwt.ronghub.entity.Suggestion;
import com.hwt.ronghub.mapper.SuggestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 创建于  2025-02-10
 *
 * @Version: 1.0
 */
@Service
public class SuggestionService {

    @Autowired
    private SuggestionMapper suggestionMapper;

    public void addSuggestion(Suggestion suggestion) {
        suggestion.setSuggestionTime(new Date());
        suggestion.setFlag(1);
        suggestionMapper.insert(suggestion);
    }

    public Page<Suggestion> getAllSuggestion(Integer current, Integer size) {
        Page<Suggestion> suggestionPage = new Page<>(current, size);
        return suggestionMapper.selectPage(suggestionPage , null);
    }

    public Page<Suggestion> updateSuggestionFlag(Integer suggestionId) {
        Suggestion suggestion = suggestionMapper.selectById(suggestionId);
        suggestion.setFlag(2);
        suggestionMapper.updateById(suggestion);
        return getAllSuggestion(1,5);
    }

    public List<Suggestion> getSuggestionByFlag(Integer flag) {
        return suggestionMapper.selectList(new LambdaQueryWrapper<Suggestion>().eq(Suggestion::getFlag , flag));
    }
}
