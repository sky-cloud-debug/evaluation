package com.evaluation.system.util.ExcelUtils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;

public class ExcelUtils {

    // 后期更改为服务器excel存放地址
    public static String excelPath = "C:\\Users\\JYuXuAN\\Desktop\\";

    // 9*9乘法注入excel测试，后期删除
    public void test() {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheets = wb.createSheet("九九乘法表");
        for (int i = 1; i <= 9; i++) {
            XSSFRow row = sheets.createRow(i - 1); // 行
            for (int j = 1; j <= 9; j++) {
                XSSFCell cell = row.createCell(j - 1); // 列
                cell.setCellValue(i + "*" + j + "=" + i * j);
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("");
            try {
                wb.write(fileOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 注入班级成员
    public void setHead(String classMajor, String type, ArrayList<String> nameList) throws IOException {
        String filePath = excelPath + classMajor + type + ".xlsx";
        createExcel(filePath);
        XSSFWorkbook wb = returnWorkBookGivenFileHandle(filePath);
        XSSFSheet sheet = wb.createSheet("Sheet1");
        XSSFRow row = sheet.createRow(0);
        for (int i = 1; i <= nameList.size(); ++i) {
            XSSFCell cell = row.createCell(i);
            cell.setCellValue(nameList.get(i - 1));
        }
        saveExcel(wb, filePath);
    }

    // 注入测评小组成员
    public void setTest(String classMajor, String type, ArrayList<String> testList) {
        String filePath = excelPath + classMajor + type + ".xlsx";
        XSSFWorkbook wb = returnWorkBookGivenFileHandle(filePath);
        XSSFSheet sheet = wb.getSheet("Sheet1");
        for (int i = 0; i < testList.size(); ++i) {
            XSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue(testList.get(i));
        }
        saveExcel(wb, filePath);
    }

    // 测评小组打分 TODO！！！
    public void inputScore(String classMajor, String name, String type, ArrayList<Integer> scoreList) {

    }

    // 建立excel表格
    public void createExcel(String filePath) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        wb.write(fileOutputStream);
        fileOutputStream.close();
    }

    // 得到一个已有的Excel工作簿的POI对象
    private XSSFWorkbook returnWorkBookGivenFileHandle(String filePath) {
        XSSFWorkbook wb = null;
        FileInputStream fis = null;
        File f = new File(filePath);
        try {
            if (f != null) {
                fis = new FileInputStream(f);
                wb = new XSSFWorkbook(fis);
            }
        } catch (Exception e) {
            return null;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return wb;
    }

    // 保存Excel
    private void saveExcel(XSSFWorkbook wb, String filePath) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(filePath);
            wb.write(fileOutputStream);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
