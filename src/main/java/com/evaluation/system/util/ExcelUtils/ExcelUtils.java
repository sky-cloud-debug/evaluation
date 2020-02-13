package com.evaluation.system.util.ExcelUtils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelUtils {

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
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\JYuXuAN\\Desktop\\计算机18-4Moral.xlsx");
            try {
                wb.write(fileOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setHead(String classMajor, String type, ArrayList<String> nameList) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheets = wb.createSheet("Sheet1");
        XSSFRow row = sheets.createRow(0);
        for (int i = 1; i <= nameList.size(); ++i) {
            XSSFCell cell = row.createCell(i);
            cell.setCellValue(nameList.get(i - 1));
        }
        try { // 文件通过classMajor、type找到，此处测试~
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\JYuXuAN\\Desktop\\计算机18-4Moral.xlsx");
            try {
                wb.write(fileOutputStream);
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setTest(String classMajor,ArrayList<String> teamList) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheets = wb.createSheet("Sheet1");
        for (int i = 1; i <= teamList.size(); ++i) {
            XSSFRow row = sheets.createRow(i);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue(teamList.get(i - 1));
        }
        try { // 文件通过classMajor、type找到，此处测试~
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\JYuXuAN\\Desktop\\计算机18-4Moral.xlsx");
            try {
                wb.write(fileOutputStream);
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void inputScore(String classMajor, String name, String type, ArrayList<Integer> scoreList) {

    }

}
