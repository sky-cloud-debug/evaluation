package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.AwardRepository;
import com.evaluation.system.Dao.AwardTempRepository;
import com.evaluation.system.Service.ExamineService;
import com.evaluation.system.domain.Award;
import com.evaluation.system.domain.AwardTemp;
import com.evaluation.system.util.RouterUtils;
import com.evaluation.system.util.oss.OSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * author:江宇轩
 */
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
    public ArrayList<AwardTemp> getAwardTempInfo(String classMajor, int flag) {
        ArrayList<AwardTemp> awardTemps = new ArrayList<AwardTemp>();
        awardTemps = awardTempRepository.findByClassMajorAndFlag(classMajor, flag);
        return awardTemps;
    }
    /**
     * 导出所有审核表中的班级
     *
     * @return
     */
    public List<String> getAllClassMajor() {
        List<String> classMajors;
        classMajors = awardTempRepository.getClassMajorGroupByClassMajor();
        return classMajors;
    }

    /**
     * 加分项审核表导入数据库，保存照片
     * 修改：需要判断是否新建文件夹
     *
     * @param awardTemp
     * @return
     */
    public String insertAwardTemp(AwardTemp awardTemp, MultipartFile file) {
        OSSUtil ossClient = new OSSUtil();
        String fileName = awardTemp.getNumber() + "-" + RouterUtils.generateRandomString() + ".jpg";
        try {
            if (!(file).isEmpty()) {
                //将文件上传
                String name = ossClient.uploadImg2Oss(file, fileName);
                //获取文件的URl地址  以便前台  显示
                String router = ossClient.getImgUrl(name);
                awardTemp.setRouter(router);
                awardTempRepository.save(awardTemp);
                return "上传成功";
            } else {
                return "上传失败";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败"; // 上传失败后所有提交的数据清除，不进入数据库！
        }
    }


    /**
     * 管理员审核加分项
     *
     * @param number
     * @param awardName
     * @param flag
     * @param reason
     * @return
     */
    public String judgeMaterials_admin(String number, String awardName, int flag, String reason) {
        AwardTemp awardTemp;
        awardTemp = awardTempRepository.findByNumberAndAwardName(number, awardName);
        awardTemp.setFlag(flag);
        if (flag == 2) {
            awardTemp.setReason("");
        } else {
            awardTemp.setReason(reason);
        }
        awardTempRepository.save(awardTemp); // 更新中间库
        if (flag == 2) {
            Award award = new Award();
            award.setNumber(awardTemp.getNumber());
            award.setName(awardTemp.getName());
            award.setType(awardTemp.getType());
            award.setLevel(awardTemp.getLevel());
            award.setAwardName(awardTemp.getAwardName());
            award.setScore(awardTemp.getScore());
            award.setClassMajor(awardTemp.getClassMajor());
            award.setRouter(awardTemp.getRouter());
            award.setUpdateTime(awardTemp.getUpdateTime());
            awardRepository.save(award); // 导入最终库
            return "管理员审核通过";
        } else {
            String router = awardTemp.getRouter();
            String[] ss = router.split("/");
            int length = ss.length;
            OSSUtil ossUtil = new OSSUtil();
            ossUtil.deleteImg(ss[length - 1]);
            return "管理员已拒绝：" + reason;
        }
    }

    /**
     * 班长审核加分项
     *
     * @param number
     * @param awardName
     * @param flag
     * @param reason
     * @return
     */
    public String judgeMaterials_monitor(String number, String awardName, int flag, String reason) {
        AwardTemp awardTemp;
        awardTemp = awardTempRepository.findByNumberAndAwardName(number, awardName);
        awardTemp.setFlag(flag);
        if (flag == 1) {
            awardTemp.setReason("");
        } else {
            awardTemp.setReason(reason);
        }
        awardTempRepository.save(awardTemp); // 更新中间库
        if (flag == -1) {
            String router = awardTemp.getRouter();
            String[] ss = router.split("/");
            int length = ss.length;
            OSSUtil ossUtil = new OSSUtil();
            ossUtil.deleteImg(ss[length - 1]);
            return "班长已拒绝：" + reason;
        } else {
            return "班长审核通过";
        }
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

    /**
     * 通过某个条件查询未审批的奖状
     *
     * @param classMajor
     * @param type
     * @param level
     * @return
     */
    public ArrayList<AwardTemp> findAwardTemp(String classMajor, String type, String level) {
        ArrayList<AwardTemp> awardTemps = new ArrayList<>();
        if (classMajor != null) {
            awardTemps = awardTempRepository.findByClassMajor(classMajor);
            return awardTemps;
        } else if (type != null) {
            awardTemps = awardTempRepository.findByType(type);
            return awardTemps;
        } else if (level != null) {
            awardTemps = awardTempRepository.findByLevel(level);
            return awardTemps;
        } else
            return null;
    }

    /**
     * 通过某个条件查询未审批的奖状
     *
     * @param classMajor
     * @param type
     * @param level
     * @param flag
     * @return
     */
    public ArrayList<AwardTemp> findAwardTemp(String classMajor, String type, String level, int flag) {
        ArrayList<AwardTemp> awardTemps = new ArrayList<>();
        if (classMajor != null) {
            if (flag == -1) { // 不需要按照审批状态查询
                awardTemps = awardTempRepository.findByClassMajor(classMajor);
            } else { // 需要按照审批状态查询
                awardTemps = awardTempRepository.findByClassMajorAndFlag(classMajor, flag);
            }
            return awardTemps;
        } else if (type != null) {
            if (flag == -1) { // 不需要按照审批状态查询
                awardTemps = awardTempRepository.findByType(type);
            } else { // 需要按照审批状态查询
                awardTemps = awardTempRepository.findByTypeAndFlag(type, flag);
            }
            return awardTemps;
        } else if (level != null) {
            if (flag == -1) { // 不需要按照审批状态查询
                awardTemps = awardTempRepository.findByLevel(level);
            } else { // 需要按照审批状态查询
                awardTemps = awardTempRepository.findByLevelAndFlag(level, flag);
            }
            return awardTemps;
        } else
            return null;
    }
}
