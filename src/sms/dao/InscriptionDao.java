/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms.dao;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sms.entities.Classe;
import sms.entities.Inscription;

/**
 *
 * @author USER
 */
public class InscriptionDao implements IDao<Inscription> {
     DaoMysql sgbd=new DaoMysql();
     private final String SQL_INSERT="INSERT INTO `inscription` (`etu_id`, `classe_id`, `annee_scolaire`,date) VALUES (?, ?, ?,?)";
    @Override
    public int insert(Inscription insc) {
        int result=0;
      sgbd.ouvrirConnexionBD();
    
      sgbd.preparerRequete(SQL_INSERT);
        try {
            sgbd.getPs().setInt(1,insc.getEtu().getId());
            sgbd.getPs().setInt(2,insc.getClasse().getId());
            sgbd.getPs().setString(3,insc.getAnnee());
            sgbd.getPs().setString(4,insc.getDateInscription().format(DateTimeFormatter.ISO_DATE));
            result=  sgbd.executeMisAJour();
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
      sgbd.closeConnexion();
      return result;
    }

    @Override
    public int update(Inscription obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Inscription findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Inscription> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public List<Inscription> findAll(String Annee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public List<Inscription> findAll(int idClasse,String Annee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
