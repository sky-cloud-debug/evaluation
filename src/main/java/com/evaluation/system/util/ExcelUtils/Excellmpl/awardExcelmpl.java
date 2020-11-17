package com.evaluation.system.util.ExcelUtils.Excellmpl;

import com.evaluation.system.domain.Award;
import com.evaluation.system.util.ExcelUtils.EntityExcelUtil;
import org.apache.poi.ss.usermodel.Row;

public class awardExcelmpl extends EntityExcelUtil<Award> {

    public awardExcelmpl() {
        String title[]={"学号","姓名","专业班级","奖项类型","加分类型","奖项名称","加分数","时间"};
        super.setTitle(title);
    }

    @Override
    public void exportData(Row row, Award ob) {
        row.createCell(0).setCellValue(ob.getNumber());
        row.createCell(1).setCellValue(ob.getName());
        row.createCell(2).setCellValue(ob.getClassMajor());
        row.createCell(3).setCellValue(ob.getType());
        row.createCell(4).setCellValue(ob.getLevel());
        row.createCell(5).setCellValue(ob.getAwardName());
        row.createCell(6).setCellValue(ob.getScore());
        row.createCell(7).setCellValue(ob.getUpdateTime());
    }
    @Override
    public Award importData(Row row) {
        return null;
    }
}
