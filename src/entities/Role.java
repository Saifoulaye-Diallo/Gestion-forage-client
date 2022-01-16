package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nom;
    @OneToMany(mappedBy = "roles")
    List<User> users = new ArrayList<User>();

    public Role(int id, String nom, List<User> users) {
        this.id = id;
        this.nom = nom;
        this.users = users;
    }

    public Role() {
    }

    public int getId() {
        return id;
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

    public List<User> getRoles() {
        return users;
    }

    public void setRoles(List<User> roles) {
        this.users = roles;
    }

    @Override
    public String toString() {
        return nom;
    }
}
