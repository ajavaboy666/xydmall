package com.edu118.commodity.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu118.common.utils.PageUtils;
import com.edu118.common.utils.Query;

import com.edu118.commodity.dao.CategoryBrandRelationDao;
import com.edu118.common.entity.commodity.CategoryBrandRelationEntity;
import com.edu118.common.service.commodity.CategoryBrandRelationService;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<CategoryBrandRelationEntity> qw = new QueryWrapper<>();
        String brandId = (String) params.get("brandId");
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            qw.or(qWrapper -> {
                qWrapper.eq("catelog_id", key)
                        .or().like("brand_name", key)
                        .or().like("catelog_name", key);
            });
        }
        if (!StringUtils.isEmpty(brandId)) {
            qw.eq("brand_id", brandId);
        }
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                qw
        );
        return new PageUtils(page);
    }
}