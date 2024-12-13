package com.example.eval_java.model;

import com.example.eval_java.view.ConventionView;
import com.example.eval_java.view.SalarieView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
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
public class Convention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Cle primaire
    @JsonView({SalarieView.class, ConventionView.class})
    Integer id;

    @Column(nullable = false)
    @NotBlank(message= "Le nom de ne pas être vide")
    @JsonView({SalarieView.class, ConventionView.class})
    String nom;

    @PositiveOrZero(message = "Les subventions ne peuvent pas être négatifs")
    @JsonView({SalarieView.class, ConventionView.class})
    Float subvention;

    @Min(value = 1, message = "Le nombre minimum de salarie est de 1")
    @JsonView({SalarieView.class, ConventionView.class})
    Integer salarieMaximum;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    @JsonView(ConventionView.class)
    Entreprise entreprise;

    @OneToMany(mappedBy = "convention")
    @JsonView(ConventionView.class)
    List<Salarie> salaries = new ArrayList<>();




















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

    public @PositiveOrZero(message = "Les subventions ne peuvent pas être négatifs") Float getSubvention() {
        return subvention;
    }

    public void setSubvention(@PositiveOrZero(message = "Les subventions ne peuvent pas être négatifs") Float subvention) {
        this.subvention = subvention;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public @Min(value = 1, message = "Le nombre minimum de salarie est de 1") Integer getSalarieMaximum() {
        return salarieMaximum;
    }

    public void setSalarieMaximum(@Min(value = 1, message = "Le nombre minimum de salarie est de 1") Integer salarieMaximum) {
        this.salarieMaximum = salarieMaximum;
    }

    public List<Salarie> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salarie> salaries) {
        this.salaries = salaries;
    }
}
