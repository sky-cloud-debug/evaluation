package com.evaluation.system.util.ExcelUtils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

public class ExcelReadUtils {


    public static ArrayList<ArrayList<Double>> getTestMemberScores(String excelPath, String classMajor, String name) {
        String path1 = excelPath + classMajor + "Moral.xlsx";
        String path2 = excelPath + classMajor + "Heart.xlsx";
        String path3 = excelPath + classMajor + "Technology.xlsx";
        ArrayList<Double> list1 = getTestMemberScore(path1, name);
        ArrayList<Double> list2 = getTestMemberScore(path2, name);
        ArrayList<Double> list3 = getTestMemberScore(path3, name);
        ArrayList<ArrayList<Double>> scoreLists = new ArrayList<>();
        scoreLists.add(list1);
        scoreLists.add(list2);
        scoreLists.add(list3);
        return scoreLists;
    }

    public static ArrayList<Double> getTestMemberScore(String filePath, String name) {
        System.out.println(filePath);
        ArrayList<Double> scoreList = new ArrayList<>();
        XSSFWorkbook wb = ExcelUtils.returnWorkBookGivenFileHandle(filePath);
        XSSFSheet sheet = wb.getSheet("Sheet1");
        for (int i = 1; i <= sheet.getLastRowNum(); ++i) {
            XSSFRow row = sheet.getRow(i);
            XSSFCell cell = row.getCell(0);
            if (cell.getStringCellValue().equals(name)) {
                for (int j = 1; j < row.getPhysicalNumberOfCells(); ++j) {
                    XSSFCell xc = row.getCell(j);
                    xc.setCellType(CellType.STRING);
                    scoreList.add(Double.parseDouble(xc.getStringCellValue()));
                }
            }
        }
        return scoreList;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Double>> scoreLists = new ArrayList<>();
        scoreLists = getTestMemberScores("D:/Work/evaluation/score/", "计算机18-4", "江宇轩");
    }

}
