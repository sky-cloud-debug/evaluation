package com.evaluation.system.util.ExcelUtils.Excellmpl;
import com.evaluation.system.domain.ExtraEntity.exportquality;
import com.evaluation.system.domain.quality;
import com.evaluation.system.util.ExcelUtils.EntityExcelUtil;
import org.apache.poi.ss.usermodel.Row;


public class qualityExceImpl extends EntityExcelUtil<exportquality> {

    public qualityExceImpl() {
        String title[]={"学号","姓名","专业班级","排名","思想道德成绩","智育成绩","身心成绩","科技人文成绩","总分"};
        super.setTitle(title);
    }

    @Override
    public void exportData(Row row, exportquality ob) {
        row.createCell(0).setCellValue(ob.getNumber());
        row.createCell(1).setCellValue(ob.getName());
        row.createCell(2).setCellValue(ob.getClassMajor());
        row.createCell(3).setCellValue(ob.getRank());
        row.createCell(4).setCellValue(ob.getMoral());
        row.createCell(5).setCellValue(ob.getWisdom());
        row.createCell(6).setCellValue(ob.getHeart());
        row.createCell(7).setCellValue(ob.getTechnology());
        row.createCell(8).setCellValue(ob.getTotalCount());
    }

    @Override
    public exportquality importData(Row row) {
        if(row==null) {
            return null;
        }
        return new exportquality(row.getCell(0).toString(),row.getCell(1).toString(),
                row.getCell(2).toString(),stringToInt(row.getCell(3).toString()),stringToDouble(row.getCell(4).toString()),
                stringToDouble(row.getCell(5).toString()),stringToDouble(row.getCell(6).toString()),
                stringToDouble(row.getCell(7).toString()),stringToDouble(row.getCell(8).toString()));
    }

}
