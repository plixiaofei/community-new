package com.plixiaofei.community.domain.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created on 2022/4/14 by plixiaofei
 */
public class UploadDTO {
    private String username;
    private MultipartFile multipartIcon;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MultipartFile getMultipartIcon() {
        return multipartIcon;
    }

    public void setMultipartIcon(MultipartFile multipartIcon) {
        this.multipartIcon = multipartIcon;
    }
}
