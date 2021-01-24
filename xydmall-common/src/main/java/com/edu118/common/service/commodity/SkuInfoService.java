package com.edu118.common.service.commodity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu118.common.utils.PageUtils;
import com.edu118.common.entity.commodity.SkuInfoEntity;

import java.util.Map;

/**
 * sku信息
 *
 * @author huangsw
 * @email 512300199@qq.com
 * @date 2021-01-22 20:21:23
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

