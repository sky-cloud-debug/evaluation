package com.evaluation.system.Service;

import com.evaluation.system.domain.*;

import java.util.List;

public interface ShowAwards {

    public yxScholarship Showyxawards(String num);

    public qtScholarship Showqtawards(String num);

    public basic ShowStudent(String num);

    public List<AllqtAwards> findAllqtAwards();

    public List<AllxyAwards> findAllyxAwards();

    public List<qtScholarship> findPersonAllByNumber(String number);

}
