package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Facture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String numeroFacture;
    @Column
    private int montantTaxe;
    @Column
    private float montantTotal;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column
    private Boolean etat;
    @Temporal(TemporalType.DATE)
    protected Date datedebut;
    @Temporal(TemporalType.DATE)
    protected Date datefin;
    @Column(length = 255)
    protected float CumulConso;
    @ManyToOne
    private Compteur compteur = new Compteur();
    public Facture() {
    }

    public Facture(int id, String numeroFacture, int montantTaxe, float montantTotal, Date date, Boolean etat, Date datedebut, Date datefin, float cumulConso, Compteur compteur) {
        this.id = id;
        this.numeroFacture = numeroFacture;
        this.montantTaxe = montantTaxe;
        this.montantTotal = montantTotal;
        this.date = date;
        this.etat = etat;
        this.datedebut = datedebut;
        this.datefin = datefin;
        CumulConso = cumulConso;
        this.compteur = compteur;
    }

    //


    public String getNumeroFacture() {
        return numeroFacture;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public float getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(float montantTotal) {
        this.montantTotal = montantTotal;
    }

    public int getMontantTaxe() {
        return montantTaxe;
    }

    public void setMontantTaxe(int montantTaxe) {
        this.montantTaxe = montantTaxe;
    }

    public void setMontantTotal(int montantTotal) {
        this.montantTotal = montantTotal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Compteur getCompteur() {
        return compteur;
    }

    public void setCompteur(Compteur compteur) {
        this.compteur = compteur;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public float getCumulConso() {
        return CumulConso;
    }

    public void setCumulConso(float cumulConso) {
        CumulConso = cumulConso;
    }
}
