package ui.facture;

import entities.Compteur;
import factory.Factory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tools.Notification;
import tools.Outils;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class Facture implements Initializable {
    @FXML
    private TextField num_facture;
    @FXML
    private TextField date_facture;
    @FXML
    private TextField compteur;
    @FXML
    private DatePicker date_debut;
    @FXML
    private DatePicker date_fin;
    @FXML
    private TextField consommation;
    @FXML
    private TextField pu;
    @FXML
    private TextField taxe;
    @FXML
    private TextField total;
    @FXML
    private Button btn_enregistrer;
    Factory factory = new Factory();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initial();
    }
    public void allAction(ActionEvent event) throws ParseException, RemoteException {
        if (event.getSource() == btn_enregistrer){
            add();
        }
    }

    public Compteur compteurs() throws RemoteException {
        if (compteur.getText().equals(""))
        {
            return null;
        }else {
            for (Compteur c: factory.iCompteur.getlistCompteur()) {
                if (c.getNumeroCompteur().equals(compteur.getText())){
                    return c ;
                }
            }
        }
        return null;
    }
    public String numeroFacture(){
        Calendar now = Calendar.getInstance();
        return "FA"+now.get(Calendar.DATE)+""+(now.get(Calendar.MONTH) + 1)+""
                +now.get(Calendar.YEAR)+""+now.get(Calendar.HOUR_OF_DAY)+""
                +now.get(Calendar.MINUTE)+""+now.get(Calendar.SECOND);
    }
    public void initial(){
        LocalDate date = LocalDate.now();
        num_facture.setText(numeroFacture());
        date_facture.setText(String.valueOf(date));
        pu.setText("150/L");
        taxe.setText("0");
    }
    public void add() throws ParseException, RemoteException {
        if (compteurs() == null)
        {
            Outils.showErrorMessage("ERREUR","VEILLER SAISIR LE NUMERO DU COMPTEUR VALIDE ET VALIDER !!!");
        }else {
            if (date_debut.getValue() == null || date_fin.getValue() == null || consommation.getText().equals("") || taxe.getText().equals("")){
                Notification.NotiError("ERREUR","VEILLER REMPLIR TOUT LES CHAMPS !!!");
            }else {
                float Conso = Float.parseFloat(consommation.getText());
                int Taxe = Integer.parseInt(taxe.getText());
                if (date_debut.getValue().isAfter(date_fin.getValue())){
                    Notification.NotiError("ERREUR","VEILLER UNE INTERVALLE DE DATE NORMAL !!!!!");
                }else if (Conso <= 0 || Taxe< 0){
                    Notification.NotiError("ERREUR","VEILLER SAISIR DES MONTANT POSITIFS !!!!!");
                }else {
                    Date datedebut = new SimpleDateFormat("yyyy-MM-dd").parse(date_debut.getValue().toString());
                    Date datefin = new  SimpleDateFormat("yyyy-MM-dd").parse(date_fin.getValue().toString());
                    float Montant = (150 * Conso) + Taxe;
                    total.setText(String.valueOf(Montant));
                    entities.Facture facture = new entities.Facture();
                    facture.setCompteur(compteurs());
                    facture.setCumulConso(Conso);
                    Date datefacture = new SimpleDateFormat("yyyy-MM-dd").parse(date_facture.getText());
                    facture.setDate(datefacture);
                    facture.setDatedebut(datedebut);
                    facture.setDatefin(datefin);
                    facture.setEtat(false);
                    facture.setMontantTaxe(Taxe);
                    facture.setMontantTotal( Montant);
                    facture.setNumeroFacture(num_facture.getText());
                    try{
                        factory.iFacture.addFacture(facture);
                        Notification.NotifSucces("SUCCES","FACTURE AJOUTER AVEC SUCCES  !!!");
                    }catch (Exception e){
                        Notification.NotiError("ERREUR","ERREUR L\'ORS DE L'AJOUT !!!"+e);
                    }
                }
            }
        }
    }

}
