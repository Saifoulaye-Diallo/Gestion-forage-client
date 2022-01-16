package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ChefVillage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column(length = 100)
    protected String nom,prenom;
    @Column(length = 50)
    protected String adresse;
    @Column(length = 50)
    protected String numero;

    public ChefVillage(int id, String nom, String prenom, String adresse, String numero) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numero = numero;
    }

    public ChefVillage() {
    }

    public int getId() {
        return (int) id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return nom+" "+prenom+" "+adresse+" "+numero;
    }
}
