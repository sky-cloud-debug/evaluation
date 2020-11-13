package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.AwardRepository;
import com.evaluation.system.Dao.AwardTempRepository;
import com.evaluation.system.Service.ExamineService;
import com.evaluation.system.Service.QuailtyService;
import com.evaluation.system.Service.fileService;
import com.evaluation.system.domain.AwardTemp;
import com.evaluation.system.domain.quality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * author:江宇轩
 */
@Service
public class ExamineServiceImpl implements ExamineService {

    @Autowired
    AwardRepository awardRepository;

    @Autowired
    AwardTempRepository awardTempRepository;

    @Autowired
    QuailtyService qualityService;

    @Autowired
    fileService fileService;
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
     * 加分项审核表导入数据库，保存照片
     *
     * @param awardTemp
     * @return
     */
    public String insertAwardTemp(AwardTemp awardTemp, MultipartFile file, HttpServletRequest request) throws IOException {
        String result=null;
        String filename=null;
//        if(file.getSize()>1000){
//            result="图片大小不能超过1M";
//            return result;
//        }
        filename=awardTemp.getClassMajor() + "\\" + awardTemp.getNumber()+ UUID.randomUUID().toString().substring(0,8) + ".jpg";
        String route="../supports/"+filename;
        awardTemp.setRoute(route);
        Resource resource=new ClassPathResource("");
        String projectPath=resource.getFile().getAbsolutePath()+"\\static\\supports";
        System.out.println("上传路径："+projectPath);
        if(upload(projectPath,file,filename)){
            awardTempRepository.save(awardTemp);
            result="上传成功";
        }else {
            result="上传失败";
        }
        return result;
    }
    private boolean upload(String realpath,MultipartFile file,String filename){
        String path=realpath+"\\"+filename;
        System.out.println(path);
        File dest=new File(path);
        if(!dest.getParentFile().exists()){
            boolean b=dest.getParentFile().mkdirs();
            System.out.println(b);
            if(!b){
                return b;
            }
        }
        try {
            file.transferTo(dest);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
        boolean result=false;
        awardTempRepository.save(awardTemp); // 更新中间库
        if (flag == 2) {
            quality quality=qualityService.findbynumber(number);
            System.out.println("原来的分数："+quality);
            String type=awardTemp.getType();
            if(type.equals("道德素质")){
                result=qualityService.updateMoralbyNumber((quality.getMoral()+awardTemp.getScore()),number);
            }else if(type.equals("科技人文素质")){
                result=qualityService.updateTechnologybyNumber((quality.getMoral()+awardTemp.getScore()),number);
            }else {
                result=qualityService.updateHeartbyNumber((quality.getMoral()+awardTemp.getScore()),number);
            }
        } else {
//            String s=fileService.deletephoto(awardTemp.getRoute());
//            return s;
        }
        if(result){
            return "管理员审核通过";
        }else {
            return "管理员审核不通过";
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
    public String judgeMaterials_monitor(String number, String awardName, int flag, String reason,HttpServletRequest request) {
        AwardTemp awardTemp;
        awardTemp = awardTempRepository.findByNumberAndAwardName(number, awardName);
        awardTemp.setFlag(flag);
        System.out.println("审核后的信息为：----"+awardTemp);
        if (flag == 1) {
            awardTemp.setReason("");
        } else {
            awardTemp.setReason(reason);
        }
        awardTempRepository.save(awardTemp); // 更新中间库
//        if (flag != 1) {
//            String s=fileService.deletephoto(awardTemp.getRoute());
//            return s;
//        }
        return "班长审核通过";
    }

    /**
     * 返回审核结果
     *
     * @param number
     * @return
     * flag=1代表班上审核通过
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
