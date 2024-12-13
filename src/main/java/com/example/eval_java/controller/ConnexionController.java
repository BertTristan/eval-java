package com.example.eval_java.controller;

import com.example.eval_java.dao.EntrepriseDao;
import com.example.eval_java.dao.UtilisateurDao;
import com.example.eval_java.model.Entreprise;
import com.example.eval_java.model.Utilisateur;
import com.example.eval_java.security.AppUserDetails;
import com.example.eval_java.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class ConnexionController {

    @Autowired
    UtilisateurDao utilisateurDao;

    @Autowired
    EntrepriseDao entrepriseDao;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @PostMapping("/inscription")
    public ResponseEntity<Map<String, Object>> inscription(@RequestBody Utilisateur utilisateur) {
        if (utilisateur.getEntreprise() != null && utilisateur.getEntreprise().getId() != null) {
            Entreprise entreprise = entrepriseDao.findById(utilisateur.getEntreprise().getId()).orElse(null);
            utilisateur.setEntreprise(entreprise); // Si entreprise est null, elle reste null
        }

        utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));
        utilisateurDao.save(utilisateur);

        return ResponseEntity.ok(Map.of("message", "Enregistrement effectué"));
    }

    @PostMapping("/connexion")
    public ResponseEntity<String> connexion(@RequestBody Utilisateur utilisateur) {

        try {
            AppUserDetails appUserDetails = (AppUserDetails) authenticationProvider
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    utilisateur.getEmail(),
                                    utilisateur.getPassword()))
                    .getPrincipal();

            return ResponseEntity.ok(jwtUtils.generationToken(appUserDetails.getUsername()));

        } catch (AuthenticationException ex) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
