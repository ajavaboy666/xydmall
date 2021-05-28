package com.edu118.common.service.commodity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu118.common.utils.PageUtils;
import com.edu118.common.entity.commodity.SpuImagesEntity;

import java.util.Map;

/**
 * spu图片
 *
 * @author huangsw
 * @email 512300199@qq.com
 * @date 2021-01-22 20:21:23
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

