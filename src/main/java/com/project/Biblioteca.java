package com.project;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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
