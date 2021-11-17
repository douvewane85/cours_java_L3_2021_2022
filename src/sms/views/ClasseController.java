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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import sms.dao.ClasseDao;
import sms.entities.Classe;
import sms.services.SmsService;

/**
 *
 * @author USER
 */
public class ClasseController  implements Initializable  {
 SmsService service =new SmsService();
 ObservableList<Classe> classes;
 private Classe classeSelected;
    @FXML
    private TextField txtLibelle;
    @FXML
    private Button btnADD;
    @FXML
    private TableView<Classe> tblvClasse;
    @FXML
    private TableColumn<Classe, String> tblcID;
    @FXML
    private TableColumn<Classe, String> tblcLibelle;
    @FXML
    private Button btnEDIT;
    @FXML
    private Button btnDEL;
    @FXML
    private Text lblError;
  
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
         service.setClasseDao(new ClasseDao());
          loadTableView();
    }

    private  void loadTableView(){
        classes=FXCollections.observableArrayList(service.listerClasse());
        tblcID.setCellValueFactory(new PropertyValueFactory<>("id"));
      tblcLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        tblvClasse.setItems(classes);
    }
    @FXML
    private void handleAddClasse(ActionEvent event) {
      
          if(txtLibelle.getText().trim().isEmpty()  ){
            lblError.setText("Libelle est Obligatoire");
            lblError.setVisible(true);
        }else{
              if(service.rechercherClasseParLibelle(txtLibelle.getText())!=null){
                   lblError.setText("Libelle existe déja"); 
                   lblError.setVisible(true);
              }else{
                  if(service.ajouterClasse(new Classe(txtLibelle.getText()))){
                      Alert alert =new Alert(Alert.AlertType.INFORMATION);
                      alert.setContentText("Classe Ajoutée avec succes");
                      alert.showAndWait();
                      loadTableView();
                  }
              }
          }
    }

    @FXML
    private void handleEditClasse(ActionEvent event) {
         if(txtLibelle.getText().trim().isEmpty()  ){
            lblError.setText("Libelle est Obligatoire");
            lblError.setVisible(true);
        }else{
              if(service.rechercherClasseParLibelle(txtLibelle.getText())!=null){
                   lblError.setText("Libelle existe déja"); 
                   lblError.setVisible(true);
              }else{
                  classeSelected.setLibellle(txtLibelle.getText());
                  if(service.modifierClasse(classeSelected)){
                      Alert alert =new Alert(Alert.AlertType.INFORMATION);
                      alert.setContentText("Classe Modifiée avec succes");
                      alert.showAndWait();
                      loadTableView();
                  }
              }
          }
    }

    @FXML
    private void handleDelClasse(ActionEvent event) {
        
    }

    @FXML
    private void handleSelectedRow(MouseEvent event) {
        classeSelected=tblvClasse.getSelectionModel().getSelectedItem();
        txtLibelle.setText(classeSelected.getLibelle());
        
    }
    
}
