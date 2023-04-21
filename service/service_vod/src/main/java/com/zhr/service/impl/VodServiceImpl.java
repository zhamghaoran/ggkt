package com.zhr.service.impl;

import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaRequest;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaResponse;
import com.zhr.exception.GGKTException;
import com.zhr.service.VodService;
import com.zhr.utils.ConstantPropertiesUtil;
import com.zhr.utils.Signature;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author 20179
 */
@Service
@Log4j2
public class VodServiceImpl implements VodService {

    @Override
    public String uploadVideo() {
        // 指定当前id和key
        VodUploadClient client = new VodUploadClient(ConstantPropertiesUtil.ACCESS_KEY_ID,
                ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        // 上传的请求对象
        VodUploadRequest request = new VodUploadRequest();
        // 设置视频文件在本地的路径
        request.setMediaFilePath("C:\\Users\\20179\\Documents\\League of Legends\\Highlights\\12-2_HN1-6643542346_01.webm");
        // 任务流
        request.setProcedure("LongVideoPreset");
        try {
            // 调用方法上传视频
            VodUploadResponse response = client.upload("ap-guangzhou", request);
            String fileId = response.getFileId();
            log.info("Upload FileId = {}", fileId);
            return fileId;
        } catch (Exception e) {
            // 业务方进行异常处理
            log.error("Upload Err", e);
            throw new GGKTException(500, "上传视频失败");
        }
    }

    // 删除腾讯云视频
    @Override
    public void removeVideo(String fileId) {
        // 密钥可前往官网控制台 https://console.cloud.tencent.com/cam/capi 进行获取
        Credential cred = new Credential(ConstantPropertiesUtil.ACCESS_KEY_ID,
                ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        // 实例化一个http选项，可选的，没有特殊需求可以跳过
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("vod.tencentcloudapi.com");
        // 实例化一个client选项，可选的，没有特殊需求可以跳过
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        // 实例化要请求产品的client对象,clientProfile是可选的
        VodClient client = new VodClient(cred, "", clientProfile);
        // 实例化一个请求对象,每个接口都会对应一个request对象
        DeleteMediaRequest req = new DeleteMediaRequest();
        req.setFileId(fileId);
        // 返回的resp是一个DeleteMediaResponse的实例，与请求对象对应
        DeleteMediaResponse resp = null;
        try {
            resp = client.DeleteMedia(req);
        } catch (TencentCloudSDKException e) {
            throw new GGKTException(500, "删除视频失败");
        }
        // 输出json格式的字符串回包
        System.out.println(DeleteMediaResponse.toJsonString(resp));
    }

    @Override
    public String sign() {
        Signature sign = new Signature();
        // 设置 App 的云 API 密钥
        sign.setSecretId(ConstantPropertiesUtil.ACCESS_KEY_ID);
        sign.setSecretKey(ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        sign.setCurrentTime(System.currentTimeMillis() / 1000);
        sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
        sign.setSignValidDuration(3600 * 24 * 2); // 签名有效期：2天
        try {
            String signature = sign.getUploadSignature();
            System.out.println("signature : " + signature);
            return signature;
        } catch (Exception e) {
            System.out.print("获取签名失败");
            e.printStackTrace();
            throw new GGKTException(500,"获取签名失败");
        }

    }
}
