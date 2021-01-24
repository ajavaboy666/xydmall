package com.edu118.commodity.controller;

import com.edu118.common.entity.commodity.CategoryEntity;
import com.edu118.common.service.commodity.CategoryService;
import com.edu118.common.utils.PageUtils;
import com.edu118.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 商品三级分类
 *
 * @author huangsw
 * @email 512300199@qq.com
 * @date 2021-01-22 20:47:49
 */
@RestController
@RequestMapping("commodity/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 查询菜单分类数据  以tree形式返回
     */
    @RequestMapping("/list/tree")
    public R listTree() {
        List<CategoryEntity> categoryAll = categoryService.list();
        //获取一级菜单
        List<CategoryEntity> oneCategory = getChildren(categoryAll, 1);
        //获取二级菜单
        List<CategoryEntity> twoCategory = getChildren(categoryAll, 2);
        //获取三级菜单
        List<CategoryEntity> threeCategory = getChildren(categoryAll, 3);
        twoCategory = setChildrens(twoCategory, threeCategory);
        oneCategory = setChildrens(oneCategory, twoCategory);
        return R.ok().put("菜单分类数据", oneCategory);
    }

    /**
     * 设置子父分类级别
     */

    private List<CategoryEntity> setChildrens(List<CategoryEntity> par, List<CategoryEntity> chi) {
        return par.stream().map(category -> {
            Long catId = category.getCatId();
            category.setChildren(chi.stream().filter(ce
                    -> ce.getParentCid().equals(catId)).collect(Collectors.toList()));
            return category;
        }).collect(Collectors.toList());
    }


    /**
     * 使用java8特性stream方法返回集合
     */

    private List<CategoryEntity> getChildren(List<CategoryEntity> list, int level) {
        return list.stream().filter(category -> category.getCatLevel() == level).collect(Collectors.toList());
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = categoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
    public R info(@PathVariable("catId") Long catId) {
        CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("category", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CategoryEntity category) {
        categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CategoryEntity category) {
        categoryService.updateById(category);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] catIds) {
        categoryService.removeByIds(Arrays.asList(catIds));

        return R.ok();
    }

}
