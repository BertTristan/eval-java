package com.example.eval_java.controller;

import com.example.eval_java.dao.EntrepriseDao;
import com.example.eval_java.dao.UtilisateurDao;
import com.example.eval_java.model.Entreprise;
import com.example.eval_java.model.Utilisateur;
import com.example.eval_java.security.IsAdmin;
import com.example.eval_java.view.UtilisateurView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class UtilisateurController {

    @Autowired
    private UtilisateurDao utilisateurDao;

    @Autowired
    private EntrepriseDao entrepriseDao;

    @Autowired
    BCryptPasswordEncoder encoder;

    @GetMapping("/utilisateur")
    @IsAdmin
    @JsonView(UtilisateurView.class)
    public List<Utilisateur> getAll(){
        return utilisateurDao.findAll();
    }

    @GetMapping("/utilisateur/{id}")
    @IsAdmin
    @JsonView(UtilisateurView.class)
    public ResponseEntity<Utilisateur> get(@PathVariable Integer id){
        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(id);
        if (optionalUtilisateur.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(optionalUtilisateur.get(), HttpStatus.OK);
    }

    @PostMapping("/utilisateur")
    @IsAdmin
    public ResponseEntity<Utilisateur> create(
            @RequestBody @Valid Utilisateur utilisateur){
        utilisateur.setId(null);
        utilisateur.setEmail(utilisateur.getEmail());
        utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));

        if ( (utilisateur.getEntreprise().getId()) > 0){
            entrepriseDao.findById(utilisateur.getEntreprise().getId()).ifPresent(utilisateur::setEntreprise);
        } else {
            utilisateur.setEntreprise(null);
        }

        utilisateurDao.save(utilisateur);
        return new ResponseEntity<>(utilisateur, HttpStatus.CREATED);
    }

    @PutMapping("/utilisateur/{id}")
    @IsAdmin
    public ResponseEntity<Utilisateur> update(
            @RequestBody @Valid Utilisateur utilisateurEnvoye, @PathVariable Integer id){
        utilisateurEnvoye.setId(id);
        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(id);
        if(optionalUtilisateur.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (utilisateurEnvoye.getEmail() != null){
            utilisateurEnvoye.setEmail(utilisateurEnvoye.getEmail());
        }
        if (utilisateurEnvoye.getPassword() != null){
            utilisateurEnvoye.setPassword(encoder.encode(utilisateurEnvoye.getPassword()));
        }
        if (utilisateurEnvoye.getEntreprise() != null){
            utilisateurEnvoye.setEntreprise(utilisateurEnvoye.getEntreprise());
        }
        utilisateurDao.save(utilisateurEnvoye);
        return new ResponseEntity<>(optionalUtilisateur.get(), HttpStatus.OK);
    }

    @DeleteMapping("/utilisateur/{id}")
    @IsAdmin
    public ResponseEntity<Utilisateur> delete(@PathVariable Integer id){
        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(id);
        if(optionalUtilisateur.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        utilisateurDao.deleteById(id);
        return new ResponseEntity<>(optionalUtilisateur.get(), HttpStatus.OK);
    }
}
