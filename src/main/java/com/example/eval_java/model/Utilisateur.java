package com.example.eval_java.model;

import com.example.eval_java.view.EntrepriseView;
import com.example.eval_java.view.UtilisateurView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Cle primaire
    @JsonView({UtilisateurView.class})
    Integer id;

    @Column(unique = true, nullable = false)
    @NotBlank(message= "L'email de ne pas être vide")
    @Email(message = "L'adresse email est invalide")
    @JsonView({UtilisateurView.class})
    String email;

    @Column(nullable = false)
    @NotBlank(message= "Le mot de passe de ne pas être vide")
    @JsonView(UtilisateurView.class)
    String password;

    @OneToOne(optional = true, cascade = CascadeType.PERSIST)
    @JsonView(UtilisateurView.class)
    Entreprise entreprise;













    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "L'email de ne pas être vide") @Email(message = "L'adresse email est invalide") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "L'email de ne pas être vide") @Email(message = "L'adresse email est invalide") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Le mot de passe de ne pas être vide") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Le mot de passe de ne pas être vide") String password) {
        this.password = password;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }
}
