package com.evaluation.system.util.ExcelUtils.Excellmpl;
import com.evaluation.system.domain.basic;
import com.evaluation.system.util.ExcelUtils.EntityExcelUtil;
import org.apache.poi.ss.usermodel.Row;


public class basicExceImpl extends EntityExcelUtil<basic> {

    public basicExceImpl() {
        String title[]={"学号","姓名","性别","政治面貌","职务","专业班级"};
        super.setTitle(title);
    }

    @Override
    public void exportData(Row row, basic ob) {
        row.createCell(0).setCellValue(ob.getNumber());
        row.createCell(1).setCellValue(ob.getName());
        row.createCell(2).setCellValue(ob.getSex());
        row.createCell(3).setCellValue(ob.getPolitical());
        row.createCell(4).setCellValue(ob.getDuty());
        row.createCell(5).setCellValue(ob.getClassMajor());

    }

    @Override
    public basic importData(Row row) {
        if(row==null) {
            return null;
        }
        return new basic(row.getCell(0).toString(),row.getCell(1).toString(),row.getCell(2).toString(),row.getCell(3).toString(),row.getCell(4).toString(),row.getCell(5).toString());
    }

}
