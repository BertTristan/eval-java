package com.example.eval_java.controller;

import com.example.eval_java.dao.SalarieDao;
import com.example.eval_java.model.Salarie;
import com.example.eval_java.security.IsEntreprise;
import com.example.eval_java.view.SalarieView;
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
public class SalarieController {
    @Autowired
    private SalarieDao salarieDao;

    @IsEntreprise
    @GetMapping("/salarie")
    @JsonView(SalarieView.class)
    public List<Salarie> getAll(){
        return salarieDao.findAll();
    }

    @IsEntreprise
    @GetMapping("/salarie/{id}")
    @JsonView(SalarieView.class)
    public ResponseEntity<Salarie> get(@PathVariable Integer id){
        Optional<Salarie> optionalSalarie = salarieDao.findById(id);
        if (optionalSalarie.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalSalarie.get(), HttpStatus.OK);
    }
}
