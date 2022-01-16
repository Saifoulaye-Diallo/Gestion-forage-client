package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Abonnement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.DATE)
    private Date dateAbonnement;
    @Column(length = 50)
    private String numero;
    @Column(length = 500)
    private String textDescript;
    @Column
    private Boolean isCpt;
    @ManyToOne
    Client client = new Client();

    public Abonnement() {
    }

    public Abonnement(long id, Date dateAbonnement, String numero, String textDescript, Boolean isCpt, Client client) {
        this.id = id;
        this.dateAbonnement = dateAbonnement;
        this.numero = numero;
        this.textDescript = textDescript;
        this.isCpt = isCpt;
        this.client = client;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getCpt() {
        return isCpt;
    }

    public void setCpt(Boolean cpt) {
        isCpt = cpt;
    }

    //
    public int getId() {
        return (int) id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateAbonnement() {
        return dateAbonnement;
    }

    public void setDateAbonnement(Date dateAbonnement) {
        this.dateAbonnement = dateAbonnement;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTextDescript() {
        return textDescript;
    }

    public void setTextDescript(String textDescript) {
        this.textDescript = textDescript;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return numero;
    }
}
