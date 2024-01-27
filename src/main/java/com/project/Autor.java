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
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch=FetchType.EAGER) //por defecto es LAZY
    //@JoinColumn(name = "autor_id") //autor_id ??
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
    }

    /*
     * @Override
     * public String toString() {
     * return "{" +
     * " autorId='" + getAutorId() + "'" +
     * ", nom='" + getNom() + "'" +
     * //", llibres='" + getLlibres() + "'" +
     * "}";
     * }
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{ autorId='").append(getAutorId()).append("',");
        stringBuilder.append(" nom='").append(getNom()).append("',");
        stringBuilder.append(" llibres=[");

        if (llibres != null) {
            for (Llibre llibre : llibres) {
                stringBuilder.append(llibre.toString()).append(", ");
            }
        }

        stringBuilder.append("]}");
        return stringBuilder.toString();
    }

}
