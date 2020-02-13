package com.evaluation.system.Dao;

        import com.evaluation.system.domain.basic;
        import org.springframework.data.jpa.repository.JpaRepository;

        import java.util.ArrayList;

public interface BasicRepository extends JpaRepository<basic,String> {

        public basic findByClassMajor(Integer number);

        public ArrayList<basic> findByClassMajor(String classMajor);
}

//push在哪来着

//在上边