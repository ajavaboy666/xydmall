package com.edu118.member.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu118.common.entity.member.MemberCollectSubjectEntity;
import com.edu118.common.service.member.MemberCollectSubjectService;
import com.edu118.common.utils.PageUtils;
import com.edu118.common.utils.R;


/**
 * 会员收藏的专题活动
 *
 * @author huangsw
 * @email 512300199@qq.com
 * @date 2021-01-22 20:50:59
 */
@RestController
@RequestMapping("member/membercollectsubject")
public class MemberCollectSubjectController {
    @Autowired
    private MemberCollectSubjectService memberCollectSubjectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberCollectSubjectService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        MemberCollectSubjectEntity memberCollectSubject = memberCollectSubjectService.getById(id);

        return R.ok().put("memberCollectSubject", memberCollectSubject);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MemberCollectSubjectEntity memberCollectSubject) {
        memberCollectSubjectService.save(memberCollectSubject);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MemberCollectSubjectEntity memberCollectSubject) {
        memberCollectSubjectService.updateById(memberCollectSubject);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        memberCollectSubjectService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
