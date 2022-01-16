package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "compteurs")
public class Compteur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50)
    private String numeroCompteur;
    @Column (length = 10)
    private String isValid;
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Abonnement abonnement = new Abonnement();
    @OneToMany(mappedBy = "compteur")
    List<Facture> listFactures = new ArrayList<Facture>();
    //
    public Compteur() {
    }
    //

    public Compteur(int id, String numeroCompteur, String isValid,Abonnement abonnement, List<Facture> listFactures) {
        this.id = id;
        this.numeroCompteur = numeroCompteur;
        this.isValid = isValid;
        this.abonnement = abonnement;
        this.listFactures = listFactures;
    }

    //
    public int getId() {
        return (int) id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroCompteur() {
        return numeroCompteur;
    }

    public void setNumeroCompteur(String numeroCompteur) {
        this.numeroCompteur = numeroCompteur;
    }


    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public Abonnement getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }

    public List<Facture> getListFactures() {
        return listFactures;
    }

    public void setListFactures(List<Facture> listFactures) {
        this.listFactures = listFactures;
    }
}
