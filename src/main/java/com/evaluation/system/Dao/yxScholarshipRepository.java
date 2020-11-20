package com.evaluation.system.Dao;

import com.evaluation.system.domain.ExtraEntity.AllqtAwards;
import com.evaluation.system.domain.ExtraEntity.AllxyAwards;
import com.evaluation.system.domain.ExtraEntity.VerifyQtScholarship;
import com.evaluation.system.domain.ExtraEntity.VerifyYxScholarship;
import com.evaluation.system.domain.qtScholarship;
import com.evaluation.system.domain.yxScholarship;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface yxScholarshipRepository extends JpaRepository<yxScholarship,String> {

//    @Query(value = "select new com.evaluation.system.domain.yxScholarship(a.number,a.scholarshipLevel,a.cardNumber,a.state,a.reason) from yxScholarship a where a.number=?1")
//    public List<yxScholarship> findyxByNumber(String number);

    public List<yxScholarship> findByNumber(String number);

    @Query(value = "select new com.evaluation.system.domain.ExtraEntity.AllxyAwards(a.number,b.name,a.scholarshipLevel) from yxScholarship a,basic b where a.number=b.number and a.year=?1 and b.classMajor=?2 and a.state=2")
    public List<AllxyAwards> findByYearAndClassMajor(String year, String classmajor);

    @Modifying
    public int deleteByNumberAndScholarshipLevelAndYear(String number,int level,String year);

    @Query(value = "select new com.evaluation.system.domain.ExtraEntity.VerifyYxScholarship(a.number,b.name,b.classMajor,a.scholarshipLevel,a.cardNumber,a.year,a.reason) from yxScholarship a,basic b where a.number=b.number and a.state=?2 and b.classMajor=?1")
    public List<VerifyYxScholarship> verifyYxscholarshipByClass(String classmajor, int state);

    @Modifying
    @Query(value = "update yxScholarship a set a.state=?1,a.reason=?4 where a.number=?2 and a.year=?3")
    public int updateYxScholarshipState(int state,String number,String year,String reason);
}
