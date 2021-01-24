package com.edu118.common.service.sale;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu118.common.utils.PageUtils;
import com.edu118.common.entity.sale.SkuFullReductionEntity;

import java.util.Map;

/**
 * 商品满减信息
 *
 * @author huangsw
 * @email 512300199@qq.com
 * @date 2021-01-22 20:24:50
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

