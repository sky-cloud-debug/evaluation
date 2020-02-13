package com.evaluation.system.util;

import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @Author 王智创
 * @Description 文件操作相关工具类
 * @Date 2020/2/11 18：23
 * @vsersion 1.0.0
 **/
public class FileUtils {


    /**
     * @return void
     * @Author 王智创
     * @Description 文件上传
     * @Date 2020/2/11 18：23
     * @Param [file, filePath, fileName]
     **/
    public static Boolean uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        FileOutputStream out = null;
        try {
            File targetFile = new File(filePath);
            //如果目录不存在，创建目录
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            out = new FileOutputStream(filePath + fileName);
            out.write(file);
            out.flush();
            //写入成功
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            //写入失败
            return false;
        } finally {
            out.close();
        }
    }
}