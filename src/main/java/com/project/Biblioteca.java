package com.project;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "Biblioteca", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class Biblioteca implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bibliotecaId;

    @Column(name = "nom")
    private String nom;

    @Column(name = "ciutat")
    private String ciutat;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(name = "Llibre_Biblioteca", joinColumns = {
            @JoinColumn(referencedColumnName = "id") }, inverseJoinColumns = {
                    @JoinColumn(referencedColumnName = "id") })
    private Set<Llibre> llibres;

    public Biblioteca() {
    }

    public Biblioteca(String nom, String ciutat) {
        this.nom = nom;
        this.ciutat = ciutat;
    }

    public long getBibliotecaId() {
        return bibliotecaId;
    }

    public void setBibliotecaId(long bibliotecaId) {
        this.bibliotecaId = bibliotecaId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public Set<Llibre> getLlibres() {
        return llibres;
    }

    public void setLlibres(Set<Llibre> llibres) {
        this.llibres = llibres;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getBibliotecaId() + ": ");
        stringBuilder.append(getNom() + ", ");
        stringBuilder.append(getCiutat() + ", ");
        stringBuilder.append("Llibres: [");
        
        if (llibres != null) {
            List<Llibre> librosOrdenados = new ArrayList<>(llibres);
            Collections.sort(librosOrdenados, Comparator.comparingLong(Llibre::getLlibreId));

            for (int i = 0; i < librosOrdenados.size(); i++) {
                Llibre llibre = librosOrdenados.get(i);
            
                stringBuilder.append(llibre.getLlibreId()).append(", ")
                        .append(llibre.getEditorial()).append(", ")
                        .append(llibre.getNom());
                        //.append(llibre.getAutor());
            
                if (i < librosOrdenados.size() - 1) {
                    stringBuilder.append(" | ");
                }
            }
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

}
