package com.project;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Autor", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class Autor implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long autorId;

    @Column(name = "nom")
    private String nom;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Llibre> llibres;

    public Autor() {
    }

    public Autor(String nom) {
        this.nom = nom;
    }

    public long getAutorId() {
        return autorId;
    }

    public void setAutorId(long autorId) {
        this.autorId = autorId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Llibre> getLlibres() {
        return llibres;
    }

    public void setLlibres(Set<Llibre> llibres) {
        this.llibres = llibres;
    }


    @Override
    public String toString() {
        return "{" +
            " autorId='" + getAutorId() + "'" +
            ", nom='" + getNom() + "'" +
            //", llibres='" + getLlibres() + "'" +
            "}";
    }

}
