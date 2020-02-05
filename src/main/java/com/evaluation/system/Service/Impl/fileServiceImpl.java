package com.evaluation.system.Service.Impl;

import com.evaluation.system.Service.fileService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class fileServiceImpl implements fileService {

    /**
     * 判断文件格式（后续若判断word等的格式文件，即可写在此处）
     * @create song
     * @param in
     * @param filename
     * @return workbook
     * @throws Exception
     */
    @Override
    public Workbook getWorkbook(InputStream in, String filename) throws Exception {
        Workbook workbook=null;

        String fileType=filename.substring(filename.lastIndexOf("."));
        if(".xls".equals(fileType)){
            workbook=new HSSFWorkbook(in);
        }
        else if(".xlsx".equals(fileType)){
            workbook=new HSSFWorkbook(in);
        }
        else{
            throw new Exception("请上传excel！");
        }
        return workbook;
    }

    /**
     * 提取excel数据
     * @create song
     * @param in
     * @param filename
     * @return List
     * @throws Exception
     */
    @Override
    public List getExcel(InputStream in, String filename) throws Exception {
        List list=new ArrayList();

        Workbook work=this.getWorkbook(in,filename);
        if(work==null){
            throw new Exception("excel为空");
        }

        Sheet sheet=null;
        Row row=null;
        Cell cell=null;

        for(int i=0;i <work.getNumberOfSheets();i++){
            sheet=work.getSheetAt(i);
            if(sheet==null){continue;}

            for(int j=sheet.getFirstRowNum();j<=sheet.getLastRowNum();j++){
                row=sheet.getRow(j);

                if(row==null||row.getFirstCellNum()==j){
                    continue;
                }

                List<Object> li=new ArrayList<>();
                for(int y=row.getFirstCellNum();y<row.getLastCellNum();y++){
                    cell=row.getCell(y);
                    if(cell==null){
                        continue;
                    }
                    li.add(cell);
                }
                list.add(li);
            }
        }
        work.close();
        return list;
    }




    /**
     * 文件上传
     * @create song
     * @param file
     * @param path
     * @return String字符串
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
