package com.evaluation.system.Service;

import com.evaluation.system.domain.user;

public interface userService {

    public user findByNumber(String number);

    public String resetuser(String number);

    public boolean adduser(user us);

}
