package com.example.eval_java.controller;

import com.example.eval_java.dao.ConventionDao;
import com.example.eval_java.model.Convention;
import com.example.eval_java.security.IsEntreprise;
import com.example.eval_java.view.ConventionView;
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
public class ConventionController {
    @Autowired
    private ConventionDao conventionDao;

    @IsEntreprise
    @GetMapping("/convention")
    @JsonView(ConventionView.class)
    public List<Convention> getAll(){
        return conventionDao.findAll();
    }

    @IsEntreprise
    @GetMapping("/convention/{id}")
    @JsonView(ConventionView.class)
    public ResponseEntity<Convention> get(@PathVariable Integer id){
        Optional<Convention> optionalConvention = conventionDao.findById(id);
        if (optionalConvention.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalConvention.get(), HttpStatus.OK);
    }
}
