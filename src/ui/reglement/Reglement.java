package ui.reglement;

import entities.Facture;
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
import tools.Outils;


import java.net.URL;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class Reglement implements Initializable {
    @FXML
    private TableView<Facture> tbl_factures;
    @FXML
    private TableColumn<Facture,String> numero;
    @FXML
    private TableColumn<Facture, Integer> taxe;
    @FXML
    private TableColumn<Facture, Date> date_debut;
    @FXML
    private TableColumn<Facture, Date> date_fin;
    @FXML
    private TableColumn<Facture, Float> montant;
    @FXML
    private TextField date;
    @FXML
    private TextField rechercher;
    @FXML
    private Button btn_valider;
    @FXML
    private Button btn_rechercher;
    Factory factory = new Factory();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            factures();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void allAction(ActionEvent event) throws RemoteException, ParseException {
        if (event.getSource()==btn_rechercher){
            factures();
        }else if (event.getSource()==btn_valider){
            add();
        }
    }

    public void factures() throws RemoteException {
        date.setText(date());
        String numeroo = rechercher.getText();
        ObservableList<Facture> factures = FXCollections.observableArrayList();
        if (numeroo.equals("")){
            for (Facture facture : factory.iFacture.getlistFacture()) {
                if ( facture.getEtat() == false) {
                    factures.add(facture);
                }
            }
            numero.setCellValueFactory(new PropertyValueFactory<Facture, String>("numeroFacture"));
            taxe.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("montantTaxe"));
            date_debut.setCellValueFactory(new PropertyValueFactory<Facture, Date>("datedebut"));
            date_fin.setCellValueFactory(new PropertyValueFactory<Facture, Date>("datefin"));
            montant.setCellValueFactory(new PropertyValueFactory<Facture, Float>("montantTotal"));
            tbl_factures.setItems(factures);
        }else {
            for (Facture facture : factory.iFacture.getlistFacture()) {
                if (facture.getNumeroFacture().equals(numeroo) && facture.getEtat() == false){
                    factures.add(facture);
                }
            }
            numero.setCellValueFactory(new PropertyValueFactory<Facture, String>("numeroFacture"));
            taxe.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("montantTaxe"));
            date_debut.setCellValueFactory(new PropertyValueFactory<Facture, Date>("datedebut"));
            date_fin.setCellValueFactory(new PropertyValueFactory<Facture, Date>("datefin"));
            montant.setCellValueFactory(new PropertyValueFactory<Facture, Float>("montantTotal"));
            tbl_factures.setItems(factures);
        }
    }
    public String date()
    {
        LocalDate datecourant = LocalDate.now();
        return datecourant.toString();
    }

    public void add() throws RemoteException, ParseException {
        if (tbl_factures.getSelectionModel().getSelectedItem() == null){
            Outils.showErrorMessage("ERREUR","VEILLER SELECTION LE COMPTEUR");
        }else {
            Facture facture = tbl_factures.getSelectionModel().getSelectedItem();
            Facture fact = new Facture();
            fact.setId(facture.getId());
            fact.setEtat(true);
            fact.setMontantTotal(facture.getMontantTotal());
            fact.setMontantTaxe(facture.getMontantTaxe());
            fact.setCumulConso(facture.getCumulConso());
            fact.setDate(facture.getDate());
            fact.setDatedebut(facture.getDatedebut());
            fact.setDatefin(facture.getDatefin());
            fact.setNumeroFacture(facture.getNumeroFacture());
            factory.iFacture.updateFacture(fact);
            entities.Reglement reglement = new entities.Reglement();
            reglement.setDatereglement(new SimpleDateFormat("yyyy-MM-dd").parse(date.getText()));
            reglement.setFacture(facture);
            factory.iReglement.addReglement(reglement);
            factures();
            Notification.NotifSucces("SUCCES","AJOUTER AVEC SUCCES !!!");
        }
    }
}
