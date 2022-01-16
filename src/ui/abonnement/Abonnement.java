package ui.abonnement;

import entities.Client;
import factory.Factory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import tools.Notification;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Abonnement implements Initializable {
    @FXML
    private TextArea description;
    @FXML
    private TextField date;
    @FXML
    private TextField numero;
    @FXML
    private ComboBox<Client> combo_client;
    @FXML
    private Button btn_annuler;
    @FXML
    private Button btn_enregistrer;
    List<entities.Abonnement> abonnements = new ArrayList<>();
    Factory factory = new Factory();
    public void allAction(ActionEvent event) throws RemoteException, ParseException {
        if (event.getSource() == btn_enregistrer){
            add();
        }else if (event.getSource() == btn_annuler){
            annule();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            combo_client();
            actualiser();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void actualiser() throws RemoteException {
        numero.setText(numeroAb());
        date.setText(date());
    }
    public void combo_client() throws RemoteException {
        ObservableList<Client> clients = FXCollections.observableArrayList(factory.iClient.getlistClient());
        combo_client.setItems(clients);
    }

    public void add() throws RemoteException, ParseException {
        entities.Abonnement ab = new entities.Abonnement();
        String numAb = numero.getText();
        String datecour = date.getText();
        String desc = description.getText();
        if (desc.equals("") || combo_client.getValue()==null){
            Notification.NotiError("ERREUR", "VEILLER REMPLIR TOUT LES CHAMPS !!!");
        }else {
            String aujourd = date.getText();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(aujourd);
            ab.setDateAbonnement(date);
            ab.setNumero(numeroAb());
            ab.setClient(combo_client.getValue());
            ab.setTextDescript(desc);
            ab.setCpt(false);
            factory.iAbonnement.addAbo(ab);
            Client cl = new Client();
            cl.setId(combo_client.getValue().getId());
            cl.setCpt(combo_client.getValue().getCpt());
            cl.setAdresse(combo_client.getValue().getAdresse());
            cl.setNom(combo_client.getValue().getNom());
            cl.setPrenom(combo_client.getValue().getPrenom());
            cl.setNumero(combo_client.getValue().getNumero());
            cl.setVillage(combo_client.getValue().getVillage());
            cl.setAbone(true);
            abonnements.add(ab);
            factory.iClient.updateClient(cl);
            Notification.NotifSucces("SUCCES","ABONNEMENT EFFECTUE AVEC SUCCES !!");
        }
    }
    public void annule(){
        description.setText("");
        combo_client.setValue(null);
    }
    public String date()
    {
        LocalDate datecourant = LocalDate.now();
        return datecourant.toString();
    }
    public String numeroAb(){
        Calendar now = Calendar.getInstance();
        return "A"+now.get(Calendar.DATE)+""+(now.get(Calendar.MONTH) + 1)+""
                +now.get(Calendar.YEAR)+""+now.get(Calendar.HOUR_OF_DAY)+""
                +now.get(Calendar.MINUTE)+""+now.get(Calendar.SECOND);
    }
}
