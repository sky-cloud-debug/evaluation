package com.evaluation.system.Service;

import com.evaluation.system.domain.*;
import com.evaluation.system.domain.ExtraEntity.AllqtAwards;
import com.evaluation.system.domain.ExtraEntity.AllxyAwards;

import java.util.List;

public interface ShowAwards {


    //public qtScholarship Showqtawards(String num);

    public basic ShowStudent(String num);

    public List<AllqtAwards> findAllqtAwards();

    public List<AllxyAwards> findAllyxAwards();

    public List<qtScholarship> findPersonAllByNumber(String number);

    public List<yxScholarship> findyxByNumber(String number);

    public List<AllqtAwards> findAllqtAwardsByClass(String classmajor);

    public List<AllxyAwards> findAllyxAwardsByClass(String classmajor);
}
