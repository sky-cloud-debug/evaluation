package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.AwardRepository;
import com.evaluation.system.Dao.AwardTempRepository;
import com.evaluation.system.Service.ExamineService;
import com.evaluation.system.domain.Award;
import com.evaluation.system.domain.AwardTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class ExamineServiceImpl implements ExamineService {

    @Value("${supportPath}")
    public String supportPath;

    @Autowired
    AwardRepository awardRepository;

    @Autowired
    AwardTempRepository awardTempRepository;

    /**
     * 传输加分项审核表
     *
     * @param classMajor
     * @return
     */
    public ArrayList<AwardTemp> getAwardTempInfo(String classMajor, String judge) {
        ArrayList<AwardTemp> awardTemps = new ArrayList<AwardTemp>();
        awardTemps = awardTempRepository.findByClassMajorAndJudge(classMajor, judge);
        return awardTemps;
    }

    /**
     * 返回现存的驳回加分项
     *
     * @param judge
     * @return
     */
    public ArrayList<AwardTemp> getAwardTempRejectInfo(String judge) {
        ArrayList<AwardTemp> awardTemps = new ArrayList<AwardTemp>();
        awardTemps = awardTempRepository.findByJudge(judge);
        return awardTemps;
    }

    /**
     * 加分项审核表导入数据库，保存照片
     *
     * @param awardTemp
     * @return
     */
    public String insertAwardTemp(AwardTemp awardTemp, MultipartFile file) {
        String filePath = supportPath + awardTemp.getClassMajor() + "/" + awardTemp.getAwardName() + "-" + awardTemp.getName() + ".jpg";
        try {
            file.transferTo(Paths.get(filePath));
            awardTempRepository.save(awardTemp);
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败"; // 上传失败后所有提交的数据清除，不进入数据库！
        }
    }

    /**
     * 管理员审核加分项
     *
     * @param awardTemp
     * @return
     */
    public String judgeMaterials(AwardTemp awardTemp) {
        awardTempRepository.save(awardTemp); // 更新中间库
        if (awardTemp.getJudge().equals("已通过")) {
            Award award = new Award();
            award.setNumber(awardTemp.getNumber());
            award.setName(awardTemp.getName());
            award.setType(awardTemp.getType());
            award.setAwardName(awardTemp.getAwardName());
            award.setScore(awardTemp.getScore());
            award.setClassMajor(awardTemp.getClassMajor());
            awardRepository.save(award); // 导入最终库
        } else {
            String filePath = supportPath + awardTemp.getClassMajor() + "/" + awardTemp.getAwardName() + "-" + awardTemp.getName() + ".jpg";
            File file = new File(filePath);
            try {
                boolean flag = file.delete(); // 如果驳回，删除照片
                if (flag) {
                    return "删除成功";
                } else {
                    return "删除失败！";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "图片不存在或其他异常！";
            }
        }
        return "审核通过";
    }

    /**
     * 返回审核结果
     *
     * @param number
     * @return
     */
    public ArrayList<AwardTemp> getJudgeResult(String number) {
        ArrayList<AwardTemp> awardTemps = new ArrayList<AwardTemp>();
        awardTemps = awardTempRepository.findByNumber(number);
        return awardTemps;
    }


}
