package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column(length = 100)
    protected String nom,prenom;
    @Column(length = 50)
    protected String adresse;
    @Column(length = 50)
    protected String numero;
    @Column(length = 10)
    protected Boolean isCpt;
    @Column(length = 10)
    protected Boolean isAbone;
    @ManyToOne
    Village village = new Village();
    @OneToMany(mappedBy = "client")
    List<Abonnement> listAbonnement = new ArrayList<Abonnement>();

    public Client() {
    }
    //

    public Client(int id, String nom, String prenom, String adresse, String numero, Boolean isCpt, Boolean isAbone, Village village, List<Abonnement> listAbonnement) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numero = numero;
        this.isCpt = isCpt;
        this.isAbone = isAbone;
        this.village = village;
        this.listAbonnement = listAbonnement;
    }


    //


    public Boolean getAbone() {
        return isAbone;
    }

    public void setAbone(Boolean abone) {
        isAbone = abone;
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

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    public List<Abonnement> getListAbonnement() {
        return listAbonnement;
    }

    public void setListAbonnement(List<Abonnement> listAbonnement) {
        this.listAbonnement = listAbonnement;
    }


    public Boolean getCpt() {
        return isCpt;
    }

    public void setCpt(Boolean cpt) {
        isCpt = cpt;
    }

    @Override
    public String toString() {
        return nom +" "+prenom+" "+numero+" "+numero+" "+village;
    }
}
