package cn.ac.iie.zuul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
@NoRepositoryBean
public interface CommonRepository<E, ID extends Serializable> extends JpaRepository<E, ID> {
}
