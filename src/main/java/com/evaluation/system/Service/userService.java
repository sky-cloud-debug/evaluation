package com.evaluation.system.Service;

import com.evaluation.system.domain.user;

public interface userService {

    public user findByNumber(String number);

    public String updatapassword(user user);

    public String resetuser(user user);
}
