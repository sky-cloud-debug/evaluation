package com.evaluation.system.Service.Impl;

import com.evaluation.system.Service.fileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;


@Service
public class fileServiceImpl implements fileService {

    /**
     * 文件上传
     * @create song
     * @param file
     * @param path
     * @return
     */
    @Override
    public String unloadFile(MultipartFile file,String path){
        if(file.isEmpty()){
            return "文件为空";
        }
        String filename=file.getOriginalFilename();
        //加时间戳

        File dest=new File(path);

        if(dest.exists()){
            return "文件已经存在";
        }

        //判断父文件夹是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }

        try{
            file.transferTo(dest);//保存文件

        }catch(IOException e){
            System.out.println(e);
        }

        return "上传成功";
    }

    /**
     * 下载文件
     * @create song
     * @param response
     * @param filePathname
     * @return
     */
    @Override
    public String downloadFile(HttpServletResponse response,String filePathname){
        File file=new File(filePathname);
        if(file.exists()){
            return "文件不存在";
        }
        response.reset();
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition","attachment;fileName="+filePathname);

        try {
            InputStream inputStream=new FileInputStream(filePathname);
            OutputStream os=response.getOutputStream();

            byte[] buff =new byte[1024];
            int len=-1;
            while((len=inputStream.read(buff))>0){
                os.write(buff,0,len);
            }
            os.flush();
            os.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "下载失败";
        } catch (IOException e) {
            e.printStackTrace();
            return "下载失败";
        }
        return "下载成功";

    }

    @Override
    public String delFile(String path){
        String resultInfo = null;
        String sb=path;
        File file = new File(sb);
        if (file.exists()) {
            if (file.delete()) {
                resultInfo =  "1-删除成功";
            } else {
                resultInfo =  "0-删除失败";
            }
        } else {
            resultInfo = "文件不存在！";
        }

        return resultInfo;
    }


}
