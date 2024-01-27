package com.project;

import javax.persistence.*;

import org.hibernate.Hibernate;

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

    // @JoinColumn(name = "autor_id") /// cascade = CascadeType.ALL
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // por defecto es LAZY
    // @JoinColumn(name = "llibre_id") //autor_id ??
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
        Hibernate.initialize(llibres);
        return llibres;
    }

    public void setLlibres(Set<Llibre> llibres) {
        this.llibres = llibres;
        for (Llibre llibre : llibres) {
            llibre.setAutor(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getAutorId() + ": ");
        stringBuilder.append(getNom() + ", ");
        stringBuilder.append("Items: [");
        if (llibres != null) {
            int size = llibres.size();
            int count = 0;

            for (Llibre llibre : llibres) {
                count++;

                stringBuilder.append(llibre.getLlibreId()).append(", ")
                        .append(llibre.getEditorial()).append(", ")
                        .append(llibre.getNom());

                if (count < size) {
                    // Si no es el último elemento, agrega el separador "|"
                    stringBuilder.append(" | ");
                }
            }
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

}
