package com.example.eval_java.security;

import com.example.eval_java.dao.UtilisateurDao;
import com.example.eval_java.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    UtilisateurDao utilisateurDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findByEmail(email);

        if (optionalUtilisateur.isEmpty()) {
            throw new UsernameNotFoundException("Email introuvable");
        }

        return new AppUserDetails(optionalUtilisateur.get());
    }
}
