package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Village implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100)
    private String nom;
    @OneToMany(mappedBy = "village")
    List<Client> Listclients = new ArrayList<Client>();
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    ChefVillage chefV = new ChefVillage();

    public Village() {
    }

    public Village(int id, String nom, List<Client> listclients, ChefVillage chefV) {
        this.id = id;
        this.nom = nom;
        Listclients = listclients;
        this.chefV = chefV;
    }
    //

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

    public List<Client> getListclients() {
        return Listclients;
    }

    public void setListclients(List<Client> listclients) {
        Listclients = listclients;
    }

    public ChefVillage getChefV() {
        return chefV;
    }

    public void setChefV(ChefVillage chefV) {
        this.chefV = chefV;
    }

    @Override
    public String toString() {
        return nom;
    }
}
