/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.DataBase;
import entities.Classe;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        DataBase data=new DataBase();
        //Ouvre la connexion
         data.openConnexion();
       /* String sql="INSERT INTO `classe` (`libelle`) VALUES (?)";
            //Initialisation du PS
             data.initPreparedStament(sql);
            //Injecter les valeurs dans la requete
              data.getPs().setString(1,"GLRS2C");
            //Executer la requete
              //Update ou Delete
             int nbreLigne=data.executeUpdate(sql);
             //Insert, LastInsertID
             ResultSet rs=data.getPs().getGeneratedKeys();
             if(rs.next()){
                 //ID Généré
              System.out.println( rs.getInt(1));  
             }
     */
          String sql="select * from classe";
          data.initPreparedStament(sql);
          ResultSet rs=data.executeSelect(sql);
          List<Classe> classes=new ArrayList();
          while(rs.next()){
              //Mapping Relation to Objet
              Classe cl=new Classe(
                      rs.getInt("id_classe"),
                      rs.getString("libelle")
              );
              classes.add(cl);
          }
            //Ferme la connexion
         data.closeConnexion();
         
         classes.forEach(System.out::println);
         
         /*
            for (Classe cl : classes){
              System.out.prinln(cl);
         }
         */
        
        
    }
    
}
