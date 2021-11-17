/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms.views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sms.dao.AffectationClasseDao;
import sms.dao.ClasseDao;
import sms.dao.EtudiantDao;
import sms.dao.InscriptionDao;
import sms.dao.ProfesseurDao;
import sms.entities.Classe;
import sms.entities.Etudiant;
import sms.services.SmsService;
import sms.utils.Validator;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class InscriptionController implements Initializable {

     SmsService service =new SmsService();
     ObservableList<Classe> obClasses;
     ObservableList<Etudiant> obEtudiants;
     Classe classeSelected=null;
     Etudiant etudiantSlected=null;
     
    @FXML
    private TextField txtNomComplet;
    @FXML
    private TextField txtMatricule;
    @FXML
    private TextField txtTuteur;
   
    @FXML
    private TableView<Etudiant> tblInscription;
    @FXML
    private TableColumn<Etudiant, String> tblcMatricule;
    @FXML
    private TableColumn<Etudiant, String> tblcNomPrenom;
    @FXML
    private TableColumn<Etudiant, String> tblcClasse;
    @FXML
    private ComboBox<Classe> cboClasse;
    @FXML
    private Label lblErrorMatricule;
    @FXML
    private Label lblErrorNomPrenom;
    @FXML
    private Label lblErrorTuteur;
    @FXML
    private TextField txtID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service.setClasseDao(new ClasseDao());
        service.setInsDao(new InscriptionDao());
        service.setEtuDao(new EtudiantDao());
        obClasses=FXCollections.observableArrayList(service.listerClasse());
        cboClasse.setItems(obClasses);
        loadTableView();
        //Cacher les Erreurs Message
        lblErrorMatricule.setVisible(false);
        lblErrorNomPrenom.setVisible(false);
        lblErrorTuteur.setVisible(false);
        txtID.setVisible(false);
    }    

    @FXML
    private void addInscription(ActionEvent event) {
          if(Validator.validatorField(txtMatricule,lblErrorMatricule,"Le Matricule") &&
           Validator.validatorField(txtNomComplet,lblErrorNomPrenom,"Le Nom Complet") &&
           Validator.validatorField(txtTuteur,lblErrorTuteur,"Le Tuteur ")){
            if(txtID.getText().trim().compareToIgnoreCase("0")==0){
                classeSelected=cboClasse.getSelectionModel().getSelectedItem();
                   Etudiant etu=new Etudiant(
                           txtMatricule.getText(), txtTuteur.getText(), txtNomComplet.getText(), txtMatricule.getText(), "0000"
                   );
                   service.inscription(etu, classeSelected, HomeController.getCtrl().getAnneeScolaireEncours());
                   loadTableView();
            }else{
                   service.inscription(etudiantSlected, classeSelected, HomeController.getCtrl().getAnneeScolaireEncours());
                   loadTableView();
            }
            }
    }

    @FXML
    private void clearFields(ActionEvent event) {
        txtMatricule.clear();
        txtTuteur.clear();
        txtNomComplet.clear();
        txtID.setText("0");
        
    }

    @FXML
    private void handleSearchEtudiant() {
         etudiantSlected=null;
          if(Validator.validatorField(txtMatricule,lblErrorMatricule,"Le Matricule")){
                etudiantSlected= service.rechercherEtudiantParMatricule(txtMatricule.getText());
              }else{
                 etudiantSlected=tblInscription.getSelectionModel().getSelectedItem();
                 txtMatricule.setText(etudiantSlected.getMatricule()); 
            }
          if( etudiantSlected!=null){
              txtID.setText(etudiantSlected.getId()+"");
              txtMatricule.setText(etudiantSlected.getMatricule());
              txtNomComplet.setText(etudiantSlected.getNom_complet());
              txtTuteur.setText(etudiantSlected.getTuteur());
          }
          
    }
    
 private  void loadTableView(){
        
        obEtudiants=FXCollections.observableArrayList(service.ListerInscristAnne(HomeController.getCtrl().getAnneeScolaireEncours()));
        tblcMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        tblcNomPrenom.setCellValueFactory(new PropertyValueFactory<>("nom_complet"));
        tblcClasse.setCellValueFactory(new PropertyValueFactory<>("classe"));
        tblInscription.setItems(obEtudiants);
       
    }

private void deactivedField(boolean disabled){
         txtMatricule.setDisable(disabled);
         txtTuteur.setDisable(disabled);
         txtNomComplet.setDisable(disabled);
    }

   

    @FXML
    private void handleShowEtudiantByClasse(ActionEvent event) {
        obEtudiants.clear();
        classeSelected=cboClasse.getSelectionModel().getSelectedItem();
        obEtudiants=FXCollections.observableArrayList(service.ListerInscristUneClasse(classeSelected.getId(),HomeController.getCtrl().getAnneeScolaireEncours()));
        tblcMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        tblcNomPrenom.setCellValueFactory(new PropertyValueFactory<>("nom_complet"));
        tblcClasse.setCellValueFactory(new PropertyValueFactory<>("classe"));
        tblInscription.setItems(obEtudiants);
    }
    
}