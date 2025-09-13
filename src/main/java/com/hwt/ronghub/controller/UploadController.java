package com.hwt.ronghub.controller;

import com.hwt.ronghub.config.AppConsts;
import com.hwt.ronghub.config.wrapResult.DontWrapResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * @创建人 hwtzzz
 * 创建于  2024-11-01
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    /**
     * 上传图片
     * @param file
     * @return
     * @throws IOException
     */
    @DontWrapResult
    public String uploadAvatar(@RequestParam("file") MultipartFile file) throws IOException {
        File fileDir = new File(AppConsts.IMAGE_PATH);
        if(!fileDir.exists()) {
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
        file.transferTo(new File(path));
        //返回文件访问地址
        System.err.println(AppConsts.ACCESS_PATH + uuid + fileSuffix);
        return AppConsts.ACCESS_PATH + uuid + fileSuffix;
    }

    @DontWrapResult
    @PostMapping("/test")
    public String uploadAvatar2(@RequestParam("file") MultipartFile file) throws IOException {
        File fileDir = new File(AppConsts.IMAGE_PATH);
        if(!fileDir.exists()) {
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
        file.transferTo(new File(path));
        //返回文件访问地址
        return AppConsts.ACCESS_PATH + uuid + fileSuffix;
    }

}
