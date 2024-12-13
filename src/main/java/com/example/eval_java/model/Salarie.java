package com.example.eval_java.model;

import com.example.eval_java.view.ConventionView;
import com.example.eval_java.view.SalarieView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Salarie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Cle primaire
    @JsonView({SalarieView.class, ConventionView.class})
    Integer id;

    @Column(length = 10)
    @Size(min = 3, max = 10, message = "Le matricule est trop court")
    @JsonView({SalarieView.class, ConventionView.class})
    String matricule;

    @Column(nullable = false)
    @NotBlank(message= "Le code barre de ne pas être vide")
    @JsonView({SalarieView.class, ConventionView.class})
    String code_barre;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    @JsonView(SalarieView.class)
    Convention convention;























    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @Size(min = 3, max = 10, message = "Le matricule est trop court") String getMatricule() {
        return matricule;
    }

    public void setMatricule(@Size(min = 3, max = 10, message = "Le matricule est trop court") String matricule) {
        this.matricule = matricule;
    }

    public @NotBlank(message = "Le code barre de ne pas être vide") String getCode_barre() {
        return code_barre;
    }

    public void setCode_barre(@NotBlank(message = "Le code barre de ne pas être vide") String code_barre) {
        this.code_barre = code_barre;
    }

    public Convention getConvention() {
        return convention;
    }

    public void setConvention(Convention convention) {
        this.convention = convention;
    }
}
