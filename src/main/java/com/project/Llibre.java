package com.project;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Llibre", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class Llibre implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long llibreId;

    @Column(name = "nom")
    private String nom;

    @Column(name = "editorial")
    private String editorial;

    @ManyToOne
    @JoinColumn(name = "autor_id") //autor_id
    private Autor autor;

    //@ManyToMany(mappedBy = "employees")
    //@JoinTable(name = "biblioteca_llibre", joinColumns = @JoinColumn(name = "llibre_id"), inverseJoinColumns = @JoinColumn(name = "biblioteca_id"))
    @ManyToMany(mappedBy = "llibres")
    private Set<Biblioteca> biblioteques;

    @ManyToMany(mappedBy = "llibres")
    private Set<Persona> persones;

    public Llibre() {
    }

    public Llibre(String nom, String editorial) {
        this.nom = nom;
        this.editorial = editorial;
    }

    public long getLlibreId() {
        return llibreId;
    }

    public void setLlibreId(long llibreId) {
        this.llibreId = llibreId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Set<Biblioteca> getBiblioteques() {
        return biblioteques;
    }

    public void setBiblioteques(Set<Biblioteca> biblioteques) {
        this.biblioteques = biblioteques;
    }

    public Set<Persona> getPersones() {
        return persones;
    }

    public void setPersones(Set<Persona> persones) {
        this.persones = persones;
    }


    @Override
    public String toString() {
        return "{" +
            " llibreId='" + getLlibreId() + "'" +
            //", nom='" + getNom() + "'" +
            ", editorial='" + getEditorial() + "'" +
            //", autor='" + getAutor() + "'" +
            //", biblioteques='" + getBiblioteques() + "'" +
            //", persones='" + getPersones() + "'" +
            "}";
    }

}
