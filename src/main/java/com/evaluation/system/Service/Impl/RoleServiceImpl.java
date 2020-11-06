package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.RoleRepository;
import com.evaluation.system.Service.RoleService;
import com.evaluation.system.domain.role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<role> findRoleByNumber(String number) {
        return roleRepository.findRoleByNumber(number);
    }
}
