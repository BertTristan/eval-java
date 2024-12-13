package com.example.eval_java.dao;

import com.example.eval_java.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseDao extends JpaRepository<Entreprise, Integer> {

}
