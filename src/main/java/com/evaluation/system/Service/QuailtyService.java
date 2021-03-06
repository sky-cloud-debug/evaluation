package com.evaluation.system.Service;

import com.evaluation.system.domain.ExtraEntity.exportquality;
import com.evaluation.system.domain.quality;

import java.util.List;

public interface QuailtyService {

    public String updateQuality(quality a);

    public quality findbynumber(String number);

    public boolean updateMoralbyNumber(Double moral, String number);

    public boolean updateHeartbyNumber(Double heart, String number);

    public boolean updateTechnologybyNumber(Double technology, String number);

    public List<exportquality> exportquality();
}
