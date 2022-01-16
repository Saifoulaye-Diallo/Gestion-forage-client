package ui.village;

import entities.ChefVillage;
import factory.Factory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tools.Notification;
import tools.Outils;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class Village implements Initializable {
    @FXML
    private TextField nom;
    @FXML
    private Button btn_annuler;
    @FXML
    private Button btn_enregistrer;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_cv;
    @FXML
    private TableView<entities.Village> tbl_village;
    @FXML
    private TableColumn<entities.Village,String> cl_nom;
    @FXML
    private TableColumn<entities.Village,String> cl_cv;
    @FXML
    private ComboBox<ChefVillage> choice_cv;

    Factory factory= new Factory();
    public void allAction(ActionEvent event) throws IOException {
        if (event.getSource()== btn_annuler){
            annuler();
        }else if (event.getSource() == btn_enregistrer){
            add();
        }else if (event.getSource() == btn_modifier){
            modifier();
        }else if (event.getSource() == btn_supprimer){
            supprimer();
        }else if (event.getSource() == btn_cv){
            Outils.loadSub(event,"NOUVEAU CHEF DE VILLAGE","../ui/chefVillage/ChefVillage.fxml");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            actualiser();
            combo_cv();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void combo_cv() throws RemoteException {
        ObservableList<ChefVillage> cv = FXCollections.observableArrayList(factory.iChefVillage.getlistCVillage());
        choice_cv.setItems(cv);
    }

    public void add() throws RemoteException {
        entities.Village v = new entities.Village();
        String nomV = nom.getText();
        ChefVillage cv = choice_cv.getValue();
        if (nomV.equals("") || cv ==null){
            Notification.NotiError("ERREUR","VEILLER REMPLIR LE CHAMP ET CHOISIR LE CHEF DE VILLAGE !!!");
        }else{
            v.setNom(nomV);
            v.setChefV(cv);
            factory.iVillage.addVillage(v);
            actualiser();
            annuler();
            Notification.NotifSucces("AJOUT","AJOUTER AVEC SUCCES !!!");
        }
    }
    public void actualiser() throws RemoteException {
        ObservableList<entities.Village> villages = FXCollections.observableArrayList(factory.iVillage.getlistVillage());
        cl_nom.setCellValueFactory(new PropertyValueFactory<entities.Village,String>("nom"));
        cl_cv.setCellValueFactory(new PropertyValueFactory<entities.Village,String>("chefV"));
        tbl_village.setItems(villages);
    }
    public void annuler(){
        nom.setText("");
        choice_cv.setValue(null);
    }

    public entities.Village modifier()
    {
        entities.Village cv = tbl_village.getSelectionModel().getSelectedItem();;
        if (cv == null){
            Notification.NotiError("ERROR","VEILLER SELECTIONNER LA LIGNE A SUPPRIMER !!");
        }else {
            nom.setText(cv.getNom());
            nom.setText(cv.getNom());
            choice_cv.setValue(cv.getChefV());
            return cv;
        }
        return null;
    }
    public void supprimer() throws RemoteException {
        entities.Village v = tbl_village.getSelectionModel().getSelectedItem();;
        if (v == null){
            Notification.NotiError("ERROR","VEILLER SELECTIONNER LA LIGNE A SUPPRIMER !!");
        }else {
            factory.iVillage.deleteVillage(v.getId());
            actualiser();
            Notification.NotifSucces("SUPPRESSION","SUPPRIMER AVEC SUCCES!!!");
        }
    }
}
