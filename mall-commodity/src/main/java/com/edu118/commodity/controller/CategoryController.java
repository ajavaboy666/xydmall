package com.edu118.commodity.controller;

import com.edu118.common.config.RedisUtils;
import com.edu118.common.entity.commodity.CategoryEntity;
import com.edu118.common.service.commodity.CategoryService;
import com.edu118.common.utils.PageUtils;
import com.edu118.common.utils.R;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
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
@Log4j2
@CacheConfig(cacheNames="commodity")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
   // @Autowired
    //private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private RedisUtils xydRedisUtils;
    /**
     * 查询菜单分类数据  以tree形式返回
     */
    @RequestMapping("/list/tree")
    //@RedisQueryCache(key = "mallRedisKeys.COMMODITY_CATEGORY_LIST_TREE",type = RedisDataType.LIST)
    @Cacheable(key = "'category:'+#root.methodName")
    public R listTree() {
//        Object redisCache = redisTemplate.opsForValue().get("commodity:category:list_tree");
//        if (redisCache != null) {
//            log.trace("缓存的数据为：{}",redisCache);
//            return R.ok().put("datas",redisCache);
//        }

        List<CategoryEntity> categoryAll = categoryService.list();
        //获取一级菜单
        List<CategoryEntity> oneCategory = getChildren(categoryAll, 1);
        //获取二级菜单
        List<CategoryEntity> twoCategory = getChildren(categoryAll, 2);
        //获取三级菜单
        List<CategoryEntity> threeCategory = getChildren(categoryAll, 3);
        twoCategory = setChildrens(twoCategory, threeCategory);
        oneCategory = setChildrens(oneCategory, twoCategory);
//        xydRedisUtils.lSet(
//                "mallRedisKeys.COMMODITY_CATEGORY_LIST_TREE",
//                oneCategory);
//        redisTemplate.opsForValue().set("commodity:category:list_tree",oneCategory);
//        log.trace("未缓存的数据为：{}",oneCategory);
        return R.ok().put("datas", oneCategory);
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

        return R.ok("数据保存成功").put("data",category);
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
