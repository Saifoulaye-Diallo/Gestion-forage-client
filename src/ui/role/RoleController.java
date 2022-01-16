package ui.role;

import entities.Role;
import factory.Factory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tools.Notification;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class RoleController implements Initializable {
    //DECLARATION DES VARIABLES
    @FXML
    private TableView<Role> tabRole;
    @FXML
    private TableColumn<Role, Integer> id;
    @FXML
    private TableColumn<Role, String> nomRole;
    @FXML
    private TextField nom;
    @FXML
    private Button btn_ajout;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_actualiser;
    Factory factory = new Factory();

    public RoleController() throws RemoteException {
    }

    // FONCTION PRINCIPALE
    @FXML
    public void allAction(ActionEvent event) throws RemoteException, NotBoundException {
        if (event.getSource() == btn_ajout)
        {
            add();
        }else if (event.getSource() == btn_modifier)
        {
            update();
        }else if (event.getSource() == btn_actualiser)
        {
            actualiser();
        }else if (event.getSource() == btn_supprimer)
        {
            delete();
        }
    }

    //AJOUT DE LA FONCTION
    public void add() throws RemoteException, NotBoundException {
        Role rolemod = tabRole.getSelectionModel().getSelectedItem();
        int maj = 0;
        if(rolemod != null){
            maj = update();
        }
        if (maj != 0){
            Role role = new Role();
            role.setId(update());
            role.setNom(nom.getText());
            if (nom.getText().equals("")){
                Notification.NotiError("MODIFICATION","Veuiller saisir le nouveau nom du role !!");;
            }else {
                try{
                    factory.iRole.update(role);
                    nom.setText("");
                    actualiser();
                    maj = 0;
                    Notification.NotifSucces("MODIFICATION","MODIFIER AVEC SUCCES!!!");
                }catch (Exception ex){
                    Notification.NotifSucces("MODIFICATION","ERREUR DE MODIFICATION!!!");
                }
            }
           }else {
            String nomrole = nom.getText();
            Role roleadd = new Role();
            roleadd.setNom(nomrole);
            try{
                factory.iRole.add(roleadd);
                nom.setText("");
                actualiser();
                Notification.NotifSucces("AJOUT","AJOUTER AVEC SUCCES!!!");
            }catch (Exception ex){
                Notification.NotifSucces("AJOUT","ERREUR D'AJOUT!!!"+ex.getMessage());
            }
        }
    }

    //MODIFICATION D'UNE FONCTION
    public int update() throws RemoteException, NotBoundException {
        Role rolemod = tabRole.getSelectionModel().getSelectedItem();
        if (rolemod != null){
            return  rolemod.getId();
        }else {
            Notification.NotiError("MODIFICATION","VEILLEZ SLECTIONNER LALIGNE A MODIFIER!!!");;
        }
        return 0;
    }

    //SUPPRESSIOON D'UNE FONCTION
    public void delete() throws RemoteException, NotBoundException {
        Role role = tabRole.getSelectionModel().getSelectedItem();;
        if (role != null){
            try{
                factory.iRole.delete(role.getId());
                actualiser();
                Notification.NotifSucces("SUPPRESSION","SUPPRIMER AVEC SUCCES!!");
            }catch (Exception ex){
                Notification.NotifSucces("SUPPRESSION","ERREUR LORS DE LA SUPPRESSION!!");
            }
        }else {
            Notification.NotiError("SUPPRESSION","VEILLEZ SELECTION UN ROLE !!!");
        }
    }

    //INITIALISATION DU TABLEAU
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            actualiser();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //REMPLISSAGE DU TABLEAU
    public void actualiser() throws RemoteException {
        ObservableList<Role> Roles = FXCollections.observableArrayList(factory.iRole.getAll());
        id.setCellValueFactory(new PropertyValueFactory<Role,Integer>("id"));
        nomRole.setCellValueFactory(new PropertyValueFactory<Role,String>("nom"));
        tabRole.setItems(Roles);
    }
}
