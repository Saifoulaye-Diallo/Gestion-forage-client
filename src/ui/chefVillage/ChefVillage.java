package ui.chefVillage;

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
import java.util.Enumeration;
import java.util.ResourceBundle;

public class ChefVillage implements Initializable {
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField telephone;
    @FXML
    private Button btn_annuler;
    @FXML
    private Button btn_enregistrer;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_supprimer;
    @FXML
    private TableView<entities.ChefVillage> tbl_cv;
    @FXML
    private TableColumn<entities.ChefVillage, String> cl_nom;
    @FXML
    private TableColumn<entities.ChefVillage, String> cl_prenom;
    @FXML
    private TableColumn<entities.ChefVillage, String> cl_adresse;
    @FXML
    private TableColumn<entities.ChefVillage, String> cl_telephone;

    Factory factory = new Factory();
    public void allAction(ActionEvent event) throws RemoteException {
        if (event.getSource()== btn_annuler){
            annuler();
        }else if (event.getSource() == btn_enregistrer){
            add();
        }else if (event.getSource() == btn_modifier){
            modifier();
        }else if (event.getSource() == btn_supprimer){
            supprimer();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            actualiser();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void add() throws RemoteException {
        entities.ChefVillage cv = new entities.ChefVillage();
        if (modifier() == null){
            String nomCv = nom.getText();
            String prenomCv = prenom.getText();
            String adresseCv = adresse.getText();
            String numeroCv = telephone.getText();
            cv.setNom(nomCv);
            cv.setPrenom(prenomCv);
            cv.setAdresse(adresseCv);
            cv.setNumero(numeroCv);
            if (nomCv.equals("")  || prenomCv.equals("") || adresseCv.equals("") || numeroCv.equals("")){
                Notification.NotiError("ERROR","VEILLER REMPLIR TOUT LES CHAMPS DU FORMULAIRE !!!");
            }else {
                factory.iChefVillage.addCV(cv);
                actualiser();
                annuler();
                Notification.NotifSucces("AJOUT","AJOUTER AVEC SUCCES!!!");
            }
        }else {
        Integer id = modifier().getId();
        String nomCv = nom.getText();
        String prenomCv = prenom.getText();
        String adresseCv = adresse.getText();
        String numeroCv = telephone.getText();
        cv.setId(id);
        cv.setNom(nomCv);
        cv.setPrenom(prenomCv);
        cv.setAdresse(adresseCv);
        cv.setNumero(numeroCv);
        if (nomCv.equals("")  || prenomCv.equals("") || adresseCv.equals("") || numeroCv.equals("")){
            Notification.NotiError("ERROR","VEILLER REMPLIR TOUT LES CHAMPS DU FORMULAIRE !!!");
        }else {
            factory.iChefVillage.updateCV(cv);
            actualiser();
            annuler();
            Notification.NotifSucces("MODIFICATION","MODIFIER AVEC SUCCES!!!");
            }
        }
    }
    public void actualiser() throws RemoteException {
        ObservableList<entities.ChefVillage> chefs = FXCollections.observableArrayList(factory.iChefVillage.getlistCVillage());
        cl_nom.setCellValueFactory(new PropertyValueFactory<entities.ChefVillage,String>("nom"));
        cl_prenom.setCellValueFactory(new PropertyValueFactory<entities.ChefVillage,String>("prenom"));
        cl_adresse.setCellValueFactory(new PropertyValueFactory<entities.ChefVillage,String>("adresse"));
        cl_telephone.setCellValueFactory(new PropertyValueFactory<entities.ChefVillage,String>("numero"));
        tbl_cv.setItems(chefs);
    }
    public void annuler(){
        nom.setText("");
        prenom.setText("");
        adresse.setText("");
        telephone.setText("");
    }
    public entities.ChefVillage modifier()
    {
        entities.ChefVillage cv = tbl_cv.getSelectionModel().getSelectedItem();;
        if (cv == null){
            Notification.NotiError("ERROR","VEILLER SELECTIONNER LA LIGNE A SUPPRIMER !!");
        }else {
            nom.setText(cv.getNom());
            prenom.setText(cv.getPrenom());
            adresse.setText(cv.getAdresse());
            telephone.setText(cv.getNumero());
            return cv;
        }
        return null;
    }
    public void supprimer() throws RemoteException {
        entities.ChefVillage cv = tbl_cv.getSelectionModel().getSelectedItem();;
        if (cv == null){
            Notification.NotiError("ERROR","VEILLER SELECTIONNER LA LIGNE A SUPPRIMER !!");
        }else {
            factory.iChefVillage.deleteCV(cv.getId());
            actualiser();
            Notification.NotifSucces("SUPPRESSION","SUPPRIMER AVEC SUCCES!!!");
        }
    }
}
