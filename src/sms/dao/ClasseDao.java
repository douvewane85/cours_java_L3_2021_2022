/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sms.entities.Classe;

/**
 *
 * @author USER
 */
public class ClasseDao implements IDao<Classe> {
     private final String SQL_SELECT_ALL="SELECT * FROM `classe`";
     private final String SQL_SELECT_BY_LIBELLE="SELECT * FROM `classe` where libelle like ?";
     private final String SQL_SELECT_BY_ID="SELECT * FROM `classe` where id_classe=?";
     private final String SQL_INSERT="INSERT INTO `classe` (`libelle`) VALUES (?)";
     private final String SQL_UPDATE="UPDATE `classe` SET `libelle` = ? WHERE `classe`.`id_classe` = ?;";
    DaoMysql sgbd=new DaoMysql();

    @Override
    public int insert(Classe classe) {
      int result=0;
      sgbd.ouvrirConnexionBD();
    
      sgbd.preparerRequete(SQL_INSERT);
        try {
            sgbd.getPs().setString(1,classe.getLibelle());
            result=  sgbd.executeMisAJour();
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
      sgbd.closeConnexion();
      return result;
    }

    @Override
    public int update(Classe classe) {
        int result=0;
      sgbd.ouvrirConnexionBD();
      sgbd.preparerRequete(SQL_UPDATE);
        try {
            sgbd.getPs().setString(1,classe.getLibelle());
            sgbd.getPs().setInt(2,classe.getId());
            result=  sgbd.executeMisAJour();
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
      sgbd.closeConnexion();
      return result;
    }

    @Override
    public Classe findById(int id) {
        Classe classe=null;
        sgbd.ouvrirConnexionBD();
        sgbd.preparerRequete(SQL_SELECT_BY_ID);
         try {
             sgbd.getPs().setInt(1, id);
              ResultSet rs=sgbd.executeSelect();
       
            if(rs.next()){
                classe =new Classe(
                        rs.getInt("id_classe"),
                        rs.getString("libelle")
                );
            
            }
        }catch (SQLException ex) {
             Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
         } 
     
       sgbd.closeConnexion();
       return classe;
    }

    @Override
    public List<Classe> findAll() {
       List<Classe> classes=new ArrayList<>();
        sgbd.ouvrirConnexionBD();
        sgbd.preparerRequete(SQL_SELECT_ALL);
        ResultSet rs=sgbd.executeSelect();
        try {
            while(rs.next()){
                Classe cl =new Classe(
                        rs.getInt("id_classe"),
                         rs.getString("libelle")
                );
              classes.add(cl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       sgbd.closeConnexion();
       return classes;
    }
    public Classe findByLibelle(String libelle) {
       Classe classe=null;
        sgbd.ouvrirConnexionBD();
        sgbd.preparerRequete(SQL_SELECT_BY_LIBELLE);
         try {
             sgbd.getPs().setString(1, libelle);
              ResultSet rs=sgbd.executeSelect();
       
            if(rs.next()){
                classe =new Classe(
                        rs.getInt("id_classe"),
                        rs.getString("libelle")
                );
            
            }
        }catch (SQLException ex) {
             Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
         } 
     
       sgbd.closeConnexion();
       return classe;
    }
    
}
