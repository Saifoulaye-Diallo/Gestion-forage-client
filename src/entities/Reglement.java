package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Reglement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.DATE)
    private Date datereglement;
    @OneToOne
    @JoinColumn(name = "fact_id", referencedColumnName = "id")
    Facture facture= new Facture();

    public Reglement() {
    }

    public Reglement(int id, Date datereglement, Facture facture) {
        this.id = id;
        this.datereglement = datereglement;
        this.facture = facture;
    }

    public int getId() {
        return (int) id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatereglement() {
        return datereglement;
    }

    public void setDatereglement(Date datereglement) {
        this.datereglement = datereglement;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }
}
