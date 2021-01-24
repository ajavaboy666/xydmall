package com.edu118.common.service.commodity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu118.common.utils.PageUtils;
import com.edu118.common.entity.commodity.SkuImagesEntity;

import java.util.Map;

/**
 * sku图片
 *
 * @author huangsw
 * @email 512300199@qq.com
 * @date 2021-01-22 20:21:23
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

