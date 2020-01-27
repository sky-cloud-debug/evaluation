package com.evaluation.system.Service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface fileService {

    public String delFile(String path);

    public String downloadFile(HttpServletResponse response, String filePathname);

    public String unloadFile(MultipartFile file, String path);
}
