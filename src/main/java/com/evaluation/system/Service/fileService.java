package com.evaluation.system.Service;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

public interface fileService {

    //删除文件
    public String delFile(String path);

    //下载文件
    public String downloadFile(HttpServletResponse response, String filePathname);

    //上传文件
    public String unloadFile(MultipartFile file, String path) ;

    //获取excel数据
    public List getExcel(InputStream in,String filename) throws Exception;

    //判断file格式
    public Workbook getWorkbook(InputStream in,String filename) throws Exception;

    public String deletephoto(String route);
}
