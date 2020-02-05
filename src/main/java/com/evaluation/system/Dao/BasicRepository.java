package com.evaluation.system.Dao;

        import com.evaluation.system.domain.basic;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicRepository extends JpaRepository<basic,String> {

        public basic findByClassMajor(Integer number);
}

//push在哪来着