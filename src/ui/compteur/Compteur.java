package ui.compteur;

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
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

public class Compteur implements Initializable {
    @FXML
    private Button btn_search;
    @FXML
    private Button btn_activer;
    @FXML
    private Button btn_desactiver;
    @FXML
    private TextField search;
    @FXML
    private TableView<entities.Compteur> tbl_compteur;
    @FXML
    private TableColumn<entities.Compteur,String> cl_numero;
    @FXML
    private TableColumn<entities.Compteur,String> cl_etat;
    Factory factory = new Factory();
    public void allAction(ActionEvent event) throws RemoteException {
        if (event.getSource() == btn_search){
            compteurs();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            compteurs();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void  compteurs() throws RemoteException {
        ObservableList<entities.Compteur> compteur = FXCollections.observableArrayList();
        if (search.getText().equals("")){
           compteur =  FXCollections.observableArrayList(factory.iCompteur.getlistCompteur());
        }else {
            for (entities.Compteur cpt:  factory.iCompteur.getlistCompteur()) {
                if (cpt.getNumeroCompteur().equals(search.getText())){
                    compteur.add(cpt);
                }
            }
        }
        cl_numero.setCellValueFactory(new PropertyValueFactory<entities.Compteur,String>("numeroCompteur"));
        cl_etat.setCellValueFactory(new PropertyValueFactory<entities.Compteur,String>("isValid"));
        tbl_compteur.setItems(compteur);
    }

    public void activer(){
        entities.Compteur cpt = tbl_compteur.getSelectionModel().getSelectedItem();
        if (cpt ==null){
            Notification.NotiError("ERREUR","VEILLEZ SELECTIONNER LE COMPTEUR !!");
        }else if (cpt.getIsValid().equals("DESACTIVER")){
            entities.Compteur compteur = new entities.Compteur();
            compteur.setNumeroCompteur(cpt.getNumeroCompteur());
            compteur.setIsValid("ACTIVER");
            compteur.setAbonnement(cpt.getAbonnement());
            compteur.setId(cpt.getId());
            try {
                factory.iCompteur.updateCompteur(compteur);
                Notification.NotifSucces("SUCCES","COMPTEUR DESACTIVER!!!");
            }catch (Exception e){
                Notification.NotiError("ERREUR","ERREUR LORS DE LA DESACTIVATION !!!"+e.getMessage());
            }
        }else {
            Notification.NotiError("ERREUR","CE COMPTEUR EST ACTIF");
        }
    }
    public void desactiver(){
        entities.Compteur cpt = tbl_compteur.getSelectionModel().getSelectedItem();
        if (cpt ==null){
            Notification.NotiError("ERREUR","VEILLEZ SELECTIONNER LE COMPTEUR !!");
        }else if (cpt.getIsValid().equals("ACTIF")){
            entities.Compteur compteur = new entities.Compteur();
            compteur.setNumeroCompteur(cpt.getNumeroCompteur());
            compteur.setIsValid("DESACTIVER");
            compteur.setAbonnement(cpt.getAbonnement());
            compteur.setId(cpt.getId());
            try {
                factory.iCompteur.updateCompteur(compteur);
                Notification.NotifSucces("SUCCES","COMPTEUR DESACTIVER!!!");
            }catch (Exception e){
                Notification.NotiError("ERREUR","ERREUR LORS DE LA DESACTIVATION !!!"+e.getMessage());
            }
        }else {
            Notification.NotiError("ERREUR","CE COMPTEUR EST DESACTIVER!!!!");
        }
    }
}
