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
    @JoinColumn(name = "autor_id") 
    private Autor autor;

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

    @Override
    public String toString() {
        
        return 
            getLlibreId() + ": " +
            getNom() + ", " +
            getEditorial();
    }

}
