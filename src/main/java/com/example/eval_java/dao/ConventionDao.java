package com.example.eval_java.dao;

import com.example.eval_java.model.Convention;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConventionDao extends JpaRepository<Convention, Integer> {

}
