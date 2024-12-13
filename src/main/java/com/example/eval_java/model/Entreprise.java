package com.example.eval_java.model;

import com.example.eval_java.view.ConventionView;
import com.example.eval_java.view.EntrepriseView;
import com.example.eval_java.view.UtilisateurView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Cle primaire
    @JsonView({UtilisateurView.class, EntrepriseView.class, ConventionView.class})
    Integer id;

    @Column(nullable = false)
    @NotBlank(message= "Le nom de ne pas être vide")
    @JsonView({UtilisateurView.class, EntrepriseView.class, ConventionView.class})
    String nom;


    @OneToOne(optional = false, mappedBy = "entreprise", cascade = CascadeType.PERSIST)
    Utilisateur utilisateur;

    @OneToMany(mappedBy = "entreprise")
    List<Convention> conventions = new ArrayList<>();




















    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "Le nom de ne pas être vide") String getNom() {
        return nom;
    }

    public void setNom(@NotBlank(message = "Le nom de ne pas être vide") String nom) {
        this.nom = nom;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Convention> getConventions() {
        return conventions;
    }

    public void setConventions(List<Convention> conventions) {
        this.conventions = conventions;
    }
}
