package com.zhr.service;

/**
 * @author 20179
 */
public interface VodService {
    String uploadVideo();

    void removeVideo(String fileId);

    String sign();
}
