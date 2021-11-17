/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Classe;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.Service;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ClasseController implements Initializable {

    private Service service=new Service();
    private ObservableList<Classe> obvClasses;
    private Classe classeSelected;
    
    @FXML
    private TextField txtfLibelle;
    @FXML
    private TableView<Classe> tblvClasse;
    @FXML
    private TableColumn<Classe, String> tblcID;
    @FXML
    private TableColumn<Classe, String> tblcLibelle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           loadTableView();
    } 
    
    private void loadTableView(){
        
        obvClasses=FXCollections
                            .observableArrayList(service.showAllClasse());
         //Construction des Colonnes
            tblcID.setCellValueFactory(new PropertyValueFactory<>("id"));
            tblcLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        //Abonnement
            tblvClasse.setItems(obvClasses);
    }

    @FXML
    private void handleAddClasse(ActionEvent event) {
       
        if(txtfLibelle.getText().trim().isEmpty()){
            Alert alert=new Alert(AlertType.ERROR);
            alert.setTitle("Fenetre Classe");
            alert.setContentText("Veuillez Saisir un Libelle");
            alert.show();
        }else{
            Classe classe=new Classe(txtfLibelle.getText());
            int id=service.addClasse(classe);
            classe.setId(id);
            
             Alert alert=new Alert(AlertType.INFORMATION);
            alert.setTitle("Fenetre Classe");
            alert.setContentText("Classe Enregistée avec Succes");
            alert.show();
            obvClasses.add(classe);
        }
         txtfLibelle.clear();
    }

    @FXML
    private void handleUpdateClasse(ActionEvent event) {
       
         if(txtfLibelle.getText().trim().isEmpty()){
            Alert alert=new Alert(AlertType.ERROR);
            alert.setTitle("Fenetre Classe");
            alert.setContentText("Veuillez Saisir un Libelle");
            alert.show();
        }else{
             classeSelected.setLibelle(txtfLibelle.getText());
            if(service.updateClasse(classeSelected)){
                 Alert alert=new Alert(AlertType.INFORMATION);
                 alert.setTitle("Fenetre Classe");
                 alert.setContentText("Classe Mis à jour avec Succes");
                  alert.show();
                  //Synchrone
                  // loadTableView(); 
                  obvClasses.set(searchClasse(classeSelected), classeSelected);
            } 
         }
          txtfLibelle.clear();
    }
    private int searchClasse(Classe classe) {
        int pos=-1;
        for(Classe cl:obvClasses){
            pos++;
            if(cl.getId()==classe.getId()) return pos;
        }
        return pos;
    }  
    
    
    @FXML
    private void handleDeleteClasse(ActionEvent event) {
        txtfLibelle.setDisable(true);
          Alert alert=new Alert(AlertType.CONFIRMATION);
                 alert.setTitle("Fenetre Classe");
                 alert.setContentText("Veuillez Confirmer la Suppression");
                 Optional<ButtonType> result= alert.showAndWait();
               if(result.get()==ButtonType.OK){
                      if(service.deleteClasse(classeSelected.getId())){
                         alert=new Alert(AlertType.INFORMATION);
                         alert.setTitle("Fenetre Classe");
                         alert.setContentText("Classe supprimée avec Succes");
                         alert.show();
                         //Synchrone
                         //loadTableView();  
                         obvClasses.remove(searchClasse(classeSelected));
                       }
                   
               }
              txtfLibelle.setDisable(false);
              txtfLibelle.clear();
    }

    @FXML
    private void handleSelectClasse(MouseEvent event) {
   
       classeSelected= tblvClasse 
                                .getSelectionModel().getSelectedItem();
       txtfLibelle.setText(classeSelected.getLibelle());
    }
    
}
