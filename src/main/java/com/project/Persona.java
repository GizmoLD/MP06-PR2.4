package com.project;

import javax.persistence.*;

import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Persona", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long personaId;

    @Column(name = "dni")
    private String dni;

    @Column(name = "nom")
    private String nom;

    @Column(name = "telefon")
    private String telefon;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(name = "persona_llibre", joinColumns = {
            @JoinColumn(referencedColumnName = "id") }, inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))

    private Set<Llibre> llibres;

    public Persona() {
    }

    public Persona(String dni, String nom, String telefon) {
        this.dni = dni;
        this.nom = nom;
        this.telefon = telefon;
    }

    public long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(long personaId) {
        this.personaId = personaId;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Set<Llibre> getLlibres() {
        return llibres;
    }

    public void setLlibres(Set<Llibre> llibres) {
        this.llibres = llibres;
    }

    /*
     * @Override
     * public String toString() {
     * return "{" +
     * " personaId='" + getPersonaId() + "'" +
     * ", dni='" + getDni() + "'" +
     * ", nom='" + getNom() + "'" +
     * ", telefon='" + getTelefon() + "'" +
     * //", llibres='" + getLlibres() + "'" +
     * "}";
     * }
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{ personaId='").append(getPersonaId()).append("',");
        stringBuilder.append(" dni='").append(getDni()).append("',");
        stringBuilder.append(" nom='").append(getNom()).append("',");
        stringBuilder.append(" telefon='").append(getTelefon()).append("',");
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
