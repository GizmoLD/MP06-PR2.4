package com.project;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // por defecto es LAZY
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
            List<Llibre> librosOrdenados = new ArrayList<>(llibres);
            Collections.sort(librosOrdenados, Comparator.comparingLong(Llibre::getLlibreId));

            for (int i = 0; i < librosOrdenados.size(); i++) {
                Llibre llibre = librosOrdenados.get(i);
            
                stringBuilder.append(llibre.getLlibreId()).append(", ")
                        .append(llibre.getEditorial()).append(", ")
                        .append(llibre.getNom());
            
                if (i < librosOrdenados.size() - 1) {
                    stringBuilder.append(" | ");
                }
            }
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

}
