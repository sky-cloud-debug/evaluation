package com.evaluation.system.Service;

import com.evaluation.system.domain.qtScholarship;
import com.evaluation.system.domain.yxScholarship;

public interface ScholarshipService {
    public String add_yx(String level,String card);

    public String add_qt(String bonusName,String card);

    public String updata_qt(qtScholarship qt);

    public String delete_qt(String number,String Bonus_name);

    public qtScholarship findBynumberAndbonus_name(String number,String Bonus_name);
}
