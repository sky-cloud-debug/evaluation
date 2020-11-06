package com.evaluation.system.Service;

import com.evaluation.system.domain.role;

import java.util.List;

public interface RoleService {

    public List<role> findRoleByNumber(String number);
}
