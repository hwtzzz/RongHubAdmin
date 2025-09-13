package com.hwt.ronghub.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hwt.ronghub.dto.PageDtoUtil;
import com.hwt.ronghub.entity.GourmetSpecialties;
import com.hwt.ronghub.service.GourmetSpecialtiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther hwtzzZ
 * 创建于  2024-11-11
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/gs")
public class GourmetSpecialtiesController {

    @Autowired
    private GourmetSpecialtiesService gourmetSpecialtiesService;

    @GetMapping("/getGSByType/{gsType}")
    public List<GourmetSpecialties> getGSByType(@PathVariable Integer gsType) {
        return gourmetSpecialtiesService.getGourmetSpecialtiesByType(gsType);
    }

    /**
     * 获取所有美食特产分页
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/getGSList/{currentPage}/{pageSize}")
    public Page<GourmetSpecialties> getGSList(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        return gourmetSpecialtiesService.getGSList(currentPage, pageSize);
    }

    /**
     * 修改美食特产信息
     *
     * @param gs
     */
    @PostMapping("/updateGS")
    public void updateGS(@RequestBody GourmetSpecialties gs) {
        gourmetSpecialtiesService.updateGS(gs);
    }

    /**
     * 添加美食特产
     *
     * @param gs
     * @return
     */
    @PostMapping("/addGS")
    public Page<GourmetSpecialties> addGS(@RequestBody GourmetSpecialties gs) {
        return gourmetSpecialtiesService.addGS(gs);
    }

    /**
     * 删除美食特产信息
     *
     * @param gourmetSpecialtiesId
     * @return
     */
    @GetMapping("/deleteGS/{gourmetSpecialtiesId}")
    public Page<GourmetSpecialties> deleteGS(@PathVariable Integer gourmetSpecialtiesId) {
        return gourmetSpecialtiesService.deleteGS(gourmetSpecialtiesId);
    }

    /**
     * 查找美食特产
     *
     * @param name
     * @return
     */
    @GetMapping("/searchGS/{name}")
    public List<GourmetSpecialties> searchGS(@PathVariable String name) {
        return gourmetSpecialtiesService.searchGS(name);
    }

    /**
     * 获取特产美食信息
     * @param gourmetSpecialtiesId
     * @return
     */
    @GetMapping("/getGSBygourmetSpecialtiesId/{gourmetSpecialtiesId}")
    public GourmetSpecialties getGSBygourmetSpecialtiesId(@PathVariable Integer gourmetSpecialtiesId) {
        return gourmetSpecialtiesService.getGSBygourmetSpecialtiesId(gourmetSpecialtiesId);
    }

}
