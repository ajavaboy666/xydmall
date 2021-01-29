package com.edu118.externalservice.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.edu118.common.utils.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 *
 * @Date 2021-01-26 20:28
 * @Author huangshaowu
 *
 */
@RestController
@RequestMapping("/oss")
public class OSSController {
    @Value("${alibaba.cloud.access-key}")
    String accessKeyId; // 请填写您的AccessKeyId。
    @Value("${alibaba.cloud.secret-key}")
    String accessKey; // 请填写您的AccessKeySecret。
    @Value("${alibaba.cloud.oss.endpoint}")
    String endpoint; // 请填写您的 endpoint。
    @Value("${alibaba.cloud.oss.bucket}")
    String bucket; // 请填写您的 bucketname 。
    @Value("${alibaba.cloud.oss.host}")
    String host; // host的格式为 bucketname.endpoint
    @PostMapping("/policy")
    public R ossPolicy() {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKey);
        Map<String, Object> respMap = null;
        // 用户上传文件时指定的前缀。
        String dir = LocalDate.now().format(DateTimeFormatter.ISO_DATE) + "/";
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            // PostObject请求最大可支持的文件大小为5 GB
            // 即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);
            respMap = new LinkedHashMap<>();
            respMap.put("ossaccessKeyId", accessKeyId);
            respMap.put("policy", encodedPolicy);
            respMap.put("key", "");
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("success_action_status", 200);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return R.ok().put("data", respMap);
    }
}

