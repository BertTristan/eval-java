package com.example.eval_java.dao;

import com.example.eval_java.model.Salarie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalarieDao extends JpaRepository<Salarie, Integer> {

}
