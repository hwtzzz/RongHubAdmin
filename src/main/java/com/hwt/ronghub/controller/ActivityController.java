package com.hwt.ronghub.controller;

import com.hwt.ronghub.dto.ActivityCommentList;
import com.hwt.ronghub.dto.ActivityListDto;
import com.hwt.ronghub.entity.Activity;
import com.hwt.ronghub.entity.ActivityComment;
import com.hwt.ronghub.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther hwtzzZ
 * 创建于  2024-11-24
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * 获取当前最热的四条活动信息
     * @return List<ActivityListDto>
     */
    @GetMapping("/getHotActivity/{userId}")
    public List<ActivityListDto> getHotActivity(@PathVariable Integer userId){
        return activityService.getHotActivity(userId);
    }

    /**
     * 获取最新的活动信息
     * @return List<ActivityListDto>
     */
    @GetMapping("/getNewActivity/{userId}")
    public List<ActivityListDto> getNewActivity(@PathVariable Integer userId){
        return activityService.getNewActivity(userId);
    }

    /**
     * 获取活动评论
     * @return
     */
    @GetMapping("/getActivityComment")
    public List<ActivityCommentList> getActivityComment(){
        return activityService.getActivityComment();
    }

    /**
     * 添加活动评论
     * @param activityComment
     * @return
     */
    @PostMapping("/addActivityComment")
    public List<ActivityCommentList> addActivityComment(@RequestBody ActivityComment activityComment){
        return activityService.addActivityComment(activityComment);
    }

    /**
     * 点赞或取消点赞活动
     * @param userId
     * @param activityId
     * @return
     */
    @GetMapping("/likeActivityOrCancel/{userId}/{activityId}/{type}")
    public List<ActivityListDto> likeActivityOrCancel(@PathVariable Integer userId , @PathVariable Integer activityId , @PathVariable Integer type){
        System.out.println(type);
        return activityService.likeActivityOrCancel(userId , activityId , type);
    }

    /**
     * 获取我喜欢的活动信息
     * @param userId
     * @return
     */
    @GetMapping("/getMyLikeActivity/{userId}")
    public List<Activity> getMyLikeActivity(@PathVariable Integer userId){
        return activityService.getMyLikeActivity(userId);
    }


}
