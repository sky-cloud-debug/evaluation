package com.evaluation.system.util.ExcelUtils.Excellmpl;

import com.evaluation.system.domain.Award;
import com.evaluation.system.domain.ExtraEntity.exportquality;
import com.evaluation.system.domain.honor;
import com.evaluation.system.util.ExcelUtils.EntityExcelUtil;
import org.apache.poi.ss.usermodel.Row;

public class honorExcelmpl extends EntityExcelUtil<honor> {

    public honorExcelmpl() {
        String title[]={"学号","荣誉","学年","审核状态","原因"};
        super.setTitle(title);
    }

    @Override
    public void exportData(Row row, honor ob) {
        row.createCell(0).setCellValue(ob.getNumber());
        row.createCell(1).setCellValue(ob.getStudentHonor());
        row.createCell(2).setCellValue(ob.getYear());
        row.createCell(3).setCellValue(ob.getState());
        row.createCell(4).setCellValue(ob.getReason());
    }
    @Override
    public honor importData(Row row) {
        if(row==null) {
            return null;
        }
        //String number, String studentHonor, String year, int state, String reason
        return new honor(row.getCell(0).toString(),row.getCell(1).toString(),
                row.getCell(2).toString(),2,"条件符合");
    }
}
