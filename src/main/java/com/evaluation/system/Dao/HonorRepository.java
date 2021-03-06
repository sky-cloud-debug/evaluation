package com.evaluation.system.Dao;

import com.evaluation.system.domain.ExtraEntity.VerifyHonor;
import com.evaluation.system.domain.honor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HonorRepository extends JpaRepository<honor,String> {

    public List<honor> findByNumber(String number);
    @Modifying
    public int deleteByNumberAndStudentHonorAndYearAndState(String number,String honor,String year,int state);

    @Query(value = "select new com.evaluation.system.domain.ExtraEntity.VerifyHonor(a.number,b.name,b.classMajor,a.studentHonor,a.year,a.state,a.reason) from honor a,basic b where a.number=b.number and b.classMajor like %?1% and a.state=?2")
    public List<VerifyHonor> findByClssMajorAndYearAndState(String classmajor,int state);

    @Modifying
    @Query(value = "update honor a set a.state=?4,a.reason=?5 where a.number=?1 and a.studentHonor=?2 and a.year=?3")
    public int updateStateByNumAndHorAndYe(String number,String honor,String year,int state,String reason);

}
