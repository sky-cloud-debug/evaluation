package com.evaluation.system.Dao;

import com.evaluation.system.domain.role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<role,String> {

    @Query(value = "SELECT r.id,r.name FROM user u LEFT JOIN role_user ru ON u.id=ru.id LEFT JOIN role r ON ru.id=r.id WHERE u.number=?1",nativeQuery = true)
    public List<role> findRoleByNumber(String number);
}
