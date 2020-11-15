package com.evaluation.system.Dao;

import com.evaluation.system.domain.ExtraEntity.exportquality;
import com.evaluation.system.domain.quality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface QualityRepository extends JpaRepository<quality,String> {
    public quality findBywisdom(double asd);
    public quality findByClassMajor(String major);
    public quality findByNumber(String number);

    @Modifying
    @Query(value = "UPDATE quality SET moral=?1 WHERE number=?2",nativeQuery = true)
    public int updateMoralbyNumber(Double moral,String number);

    @Modifying
    @Query(value = "UPDATE quality SET heart=?1 WHERE number=?2",nativeQuery = true)
    public int updateHeartbyNumber(Double heart,String number);

    @Modifying
    @Query(value = "UPDATE quality SET technology=?1 WHERE number=?2",nativeQuery = true)
    public int updateTechnologybyNumber(Double technology,String number);

    @Modifying
    @Query(value = "select new com.evaluation.system.domain.ExtraEntity.exportquality(a.number,b.name,a.classMajor,a.rank,a.moral,a.wisdom,a.heart,a.technology,a.totalCount) from quality a,basic b where a.number=b.number order by a.number")
    public List<exportquality> exportquality();
}