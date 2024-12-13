package com.example.eval_java.controller;

import com.example.eval_java.dao.EntrepriseDao;
import com.example.eval_java.model.Entreprise;
import com.example.eval_java.security.IsAdmin;
import com.example.eval_java.security.IsEntreprise;
import com.example.eval_java.view.EntrepriseView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class EntrepriseController {

    @Autowired
    private EntrepriseDao entrepriseDao;

    @GetMapping("/entreprise")
    @JsonView(EntrepriseView.class)
    @IsAdmin
    public List<Entreprise> getAll(){
        return entrepriseDao.findAll();
    }


    @GetMapping("/entreprise/{id}")
    @JsonView(EntrepriseView.class)
    @IsAdmin
    public ResponseEntity<Entreprise> get(@PathVariable Integer id){
        System.out.println(entrepriseDao.findById(id));
        Optional<Entreprise> optionalEntreprise = entrepriseDao.findById(id);
        System.out.println(optionalEntreprise);
        if (optionalEntreprise.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalEntreprise.get(), HttpStatus.OK);
    }
}
