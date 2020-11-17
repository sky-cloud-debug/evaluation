package com.evaluation.system.util.ExcelUtils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class EntityExcelUtil<T> {

    public abstract void exportData(Row row, T ob);

    public abstract T importData(Row row);

    String title[];

    public static int stringToInt(String data) {
        return Integer.parseInt(data);
    }
    public static Double stringToDouble(String data){return Double.parseDouble(data);}

    public String[] getTitle() {
        return title;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }

    public void setHead(Row row) {
        for (int i = 0; i < title.length; i++) {
            row.createCell(i).setCellValue(title[i]);
        }
    }

    public boolean exportExcel(List<T> list, String path, String sheetName) {
        Workbook wb = null;
        boolean flag=true;
        try {
            if (list.size() == 0 || path == null || path.equals("")) {
                flag=false;
            }

            String[] p = path.split("\\.");
            if (p[p.length - 1].equals("xlsx")) {
                wb = new XSSFWorkbook();
            } else if (p[p.length - 1].equals("xls")) {
                wb = new HSSFWorkbook();
            } else {
                flag=false;
            }

            Sheet sheet = wb.createSheet(sheetName);
            Row row = sheet.createRow(0);
            setHead(row);
            for (int i = 0; i < list.size(); i++) {
                row = sheet.createRow(i + 1);
                exportData(row, list.get(i));
            }
            OutputStream fileOut = new FileOutputStream(path);
            wb.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(wb!=null){
                    wb.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public List<T> importExcel(String path) {
        List<T> list = new LinkedList<T>();
        Workbook wb = null;
        try {
            if (path == null || path.equals("")) {
                System.out.println("所需内容不完整");
                return null;
            }
            /**
             * 判断上传文件是否为excel文件
             */
            int a = isExcel(new FileInputStream(path));
            if (a == 0) {
                System.out.println("文件类型不符");
                return null;
            }
            wb = WorkbookFactory.create(new FileInputStream(path));
            //确定要返回对象的类型
            Sheet sheet = wb.getSheetAt(0);//第一个
            int last = sheet.getLastRowNum();
            for (int i = 1; i <= last; i++) {
                Row row = sheet.getRow(i);//获取行
                if (importData(row) != null) {
                    list.add(importData(row));           //添加至列表
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // 关闭流
            try {
                if(wb!=null) {
                    wb.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public int isExcel(InputStream inputStream) {
        try {
            InputStream is = FileMagic.prepareToCheckMagic(inputStream);
            FileMagic magic = FileMagic.valueOf(is);
            //判断文件为excel文件
            if (Objects.equals(magic, FileMagic.OLE2) || Objects.equals(magic, FileMagic.OOXML)) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
