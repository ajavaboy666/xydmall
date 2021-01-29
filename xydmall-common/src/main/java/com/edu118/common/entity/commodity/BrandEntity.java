package com.edu118.common.entity.commodity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.edu118.common.valida.AddGroup;
import com.edu118.common.valida.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 品牌
 *
 * @author huangsw
 * @email 512300199@qq.com
 * @date 2021-01-22 20:21:22
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @TableId
    @Null(message = "{insert.not.need.id}" ,groups = {AddGroup.class})
    @NotNull(message = "{commodity.BrandEntity.brandId}",groups = {UpdateGroup.class})
    @Min(value = 1,message = "{commodity.BrandEntity.brandId}",groups = {UpdateGroup.class})
    private Long brandId;
    /**
     * 品牌名
     */
    @NotBlank(message = "{commodity.BrandEntity.name}" ,groups = {AddGroup.class})
    private String name;
    /**
     * 品牌logo地址
     */
    @URL(message = "请提供品牌Logo图片地址！",groups = {AddGroup.class})
    private String logo;
    /**
     * 介绍
     */
    @NotBlank(message = "请提供品牌简述信息！",groups = {AddGroup.class})
    private String descript;
    /**
     * 显示状态[0-不显示；1-显示]
     */
    @NotNull(message = "设置品牌是否可见，0：可见，1：隐藏！",groups = {AddGroup.class,UpdateGroup.class})
    @Range(min = 0,max = 1,message = "设置品牌是否可见，0：隐藏，1可见：！",groups = {AddGroup.class,UpdateGroup.class})
    private Integer showStatus;
    /**
     * 检索首字母
     */
    @NotBlank(message = "请设置品牌搜索简写字母，必须是a-z A-Z其中的一个！",groups = {AddGroup.class})
    @Pattern(regexp = "^[a-zA-Z]$",message = "请设置品牌搜索简写字母，必须是a-z A-Z其中的一个！",groups = {AddGroup.class})
    private String firstLetter;
    /**
     * 排序
     */
    @NotNull(message = "请设置大于等于0的排序数字，数字越小越靠前！",groups = {AddGroup.class})
    @Min(value = 0,message = "请设置大于等于0的排序数字，数字越小越靠前！",groups = {AddGroup.class})
    private Integer sort;

}
