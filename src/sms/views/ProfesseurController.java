/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms.views;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sms.dao.AffectationClasseDao;
import sms.dao.ClasseDao;
import sms.dao.ProfesseurDao;
import sms.dao.UserDao;
import sms.entities.Classe;
import sms.entities.Professeur;
import sms.services.SmsService;
import sms.utils.Validator;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ProfesseurController implements Initializable {
    SmsService service =new SmsService();
    ObservableList<Classe> classes;
    ObservableList<Professeur> professeurs;
    ObservableList<Classe> classesProf;
    Professeur profSelected=null;
    @FXML
    private TextField txtNCI;
    @FXML
    private TextField txtNomComplet;
    @FXML
    private ComboBox<Classe> cboClasse;
    @FXML
    private TableView<Classe> tblvClasse;
    @FXML
    private TableColumn<Classe, String> tblcID;
    @FXML
    private TableColumn<Classe, String> tblcLibelle;
    @FXML
    private FontAwesomeIcon IconPlus;
    @FXML
    private TableView<Professeur> tblvProfesseur;
    @FXML
    private TableColumn<Professeur, String> tblcNCI;
    @FXML
    private TableColumn<Professeur, String> tblcNomComplet;
   
    @FXML
    private TableColumn<Professeur, String> tblcGrade;
    @FXML
    private TableColumn<Professeur, String> tblcActions;
    @FXML
    private Label lblErrorNci;
    @FXML
    private Label lblErrorNomComplet;
  
    @FXML
    private TextField txtGrade;
    @FXML
    private Label lblErrorGrade;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtLogin;
    @FXML
    private Label lblErrorLogin;
    @FXML
    private FontAwesomeIcon IconSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtID.setVisible(false);
        service.setClasseDao(new ClasseDao());
       service.setAffDao(new AffectationClasseDao());
       service.setProfDao(new ProfesseurDao());
       classesProf=FXCollections.observableArrayList();
       classes=FXCollections.observableArrayList(service.listerClasse());
       cboClasse.setItems(classes);
       loadTableView();
    }    

    @FXML
    private void handleAffecterProfesseur(ActionEvent event) {
        if(Validator.validatorField(txtNCI,lblErrorNci,"Le Nci") &&
           Validator.validatorField(txtNomComplet,lblErrorNomComplet,"Le Nom Complet") &&
           Validator.validatorField(txtGrade,lblErrorGrade,"Le Grade ") &&
           Validator.validatorField(txtLogin,lblErrorLogin,"Le Login "  ) ){
            if(txtID.getText().trim().compareToIgnoreCase("0")==0){
                //Mode Ajout
                Professeur prof =new Professeur(
                        txtNCI.getText(),
                        txtGrade.getText(),
                        txtNomComplet.getText(),
                        txtLogin.getText(),
                        "0000"     
                );
               // System.err.println(HomeController.getCtrl().getAnneeScolaireEncours());
                service.affecter(prof, classesProf, HomeController.getCtrl().getAnneeScolaireEncours());
                loadTableView();
            }else{
                //Mode Mis a JourprofSelected
                service.affecter(profSelected, classesProf, HomeController.getCtrl().getAnneeScolaireEncours());

            }
            
        }
        
       
    }

    @FXML
    private void handleSearchProfesseur() {
          profSelected=null;
          if(Validator.validatorField(txtNCI,lblErrorNci,"Le Nci")){
                profSelected= service.rechercherProfesseurParNci(txtNCI.getText());
              }else{
                 profSelected=tblvProfesseur.getSelectionModel().getSelectedItem();
                 txtNCI.setText(profSelected.getNci());
                 
            }
             
            if(profSelected!=null){
                 txtNomComplet.setText(profSelected.getNom_complet());
                 txtGrade.setText(profSelected.getGrade());
                 txtLogin.setText(profSelected.getLogin());
                 txtID.setText(profSelected.getId()+"");
                 deactivedField(true);
                 classesProf.clear();
                 classesProf=FXCollections.observableArrayList(profSelected.getClasseEnseignees()
                                           .stream()
                                            .filter(a -> a.getAnnee().compareToIgnoreCase(HomeController.getCtrl().getAnneeScolaireEncours())==0)
                                            .map(a -> a.getClasse())
                                            .collect(Collectors.toList()));
                 tblcID.setCellValueFactory(new PropertyValueFactory<>("id"));
                 tblcLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
                tblvClasse.setItems(classesProf); 
            }
           
         
    }

 
    @FXML
    private void handleAddAffectation() {
         Classe classe=null;
          classe=cboClasse.getSelectionModel().getSelectedItem();
        if(classe!=null ){
            int id=classe.getId();
            if( !classesProf.stream().filter(cl->cl.getId()==id).findFirst().isPresent()){
                 classe.setAction("add");
                 classesProf.add(classe);
                // System.out.println(classes.size());
                 tblcID.setCellValueFactory(new PropertyValueFactory<>("id"));
                 tblcLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
                 tblvClasse.setItems(classesProf); 
             }
            
        }
        
    }
    
    
   
    private  void loadTableView(){
        professeurs=FXCollections.observableArrayList(service.listerProfesseur());
        tblcNCI.setCellValueFactory(new PropertyValueFactory<>("nci"));
        tblcNomComplet.setCellValueFactory(new PropertyValueFactory<>("nom_complet"));
        tblcGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        tblvProfesseur.setItems(professeurs);
    }

    
    
    private void deactivedField(boolean disabled){
         txtNCI.setDisable(disabled);
         txtGrade.setDisable(disabled);
         txtNomComplet.setDisable(disabled);
         txtLogin.setDisable(disabled);
    }
    @FXML
    public void clearField(){
        classesProf.clear();
        txtNCI.clear();
        txtGrade.clear();
        txtNomComplet.clear();
        txtLogin.clear();
        deactivedField(false);
    }
    
}
