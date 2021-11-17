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
import sms.entities.Professeur;

/**
 *
 * @author USER
 */
public class ProfesseurDao implements IDao<Professeur>{
    private final String SQL_INSERT="INSERT INTO `user` (`nom_complet`, `login`, `password`, `role`,`nci`, `grade`) VALUES (?,?,?,?,?, ?)";
    private final String SQL_SELECT_ALL="select * from user where role like 'ROLE_PROFESSEUR' ";
    private final String SQL_SELECT_BY_ID="select * from user where role like 'ROLE_PROFESSEUR' and id_user=? ";
    private final String SQL_SELECT_BY_NCI="select * from user where role like 'ROLE_PROFESSEUR' and nci like ? ";
    private DaoMysql sgbd=new DaoMysql();
    AffectationClasseDao affDao =new AffectationClasseDao();
    @Override
    public int insert(Professeur prof) {
        int result=0;
      sgbd.ouvrirConnexionBD();
    
      sgbd.preparerRequete(SQL_INSERT);
        try {
            sgbd.getPs().setString(1,prof.getNom_complet());
            sgbd.getPs().setString(2,prof.getLogin());
            sgbd.getPs().setString(3,prof.getPassword());
            sgbd.getPs().setString(4,prof.getRole());  
            sgbd.getPs().setString(5,prof.getNci());
            sgbd.getPs().setString(6,prof.getGrade());
            result=  sgbd.executeMisAJour();
            ResultSet rs=sgbd.getPs().getGeneratedKeys();
            if(rs.next()){
               result=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
      sgbd.closeConnexion();
      return result; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Professeur obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Professeur findById(int id) {
       Professeur prof=null;
        sgbd.ouvrirConnexionBD();
        sgbd.preparerRequete(SQL_SELECT_BY_ID);
        try {
            sgbd.getPs().setInt(1, id);
            ResultSet rs=sgbd.executeSelect();
            while(rs.next()){
                //String nci, String grade, int id, String nom_complet, String login, String password
                prof =new Professeur(rs.getString("nci"), 
                                              rs.getString("grade"), 
                                              rs.getInt("id_user"), 
                                              rs.getString("nom_complet"), 
                                              rs.getString("login"), 
                                              rs.getString("password"));
               prof.setClasseEnseignees(affDao.findByProfesseur( rs.getInt("id_user")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       sgbd.closeConnexion();
       return prof; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Professeur> findAll() {
        List<Professeur> profs=new ArrayList<>();
        sgbd.ouvrirConnexionBD();
        sgbd.preparerRequete(SQL_SELECT_ALL);
        
        ResultSet rs=sgbd.executeSelect();
        try {
            while(rs.next()){
                //String nci, String grade, int id, String nom_complet, String login, String password
                Professeur prof =new Professeur(rs.getString("nci"), 
                                              rs.getString("grade"), 
                                              rs.getInt("id_user"), 
                                              rs.getString("nom_complet"), 
                                              rs.getString("login"), 
                                              rs.getString("password"));
                 // prof.setClasseEnseignees(affDao.findByProfesseur( rs.getInt("id_user")));
              profs.add(prof);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       sgbd.closeConnexion();
       return profs;
    }
    
     public Professeur findByNci(String nci) {
        Professeur prof=null;
        sgbd.ouvrirConnexionBD();
        sgbd.preparerRequete(SQL_SELECT_BY_NCI);
      
        try {
              sgbd.getPs().setString(1, nci);
              ResultSet rs=sgbd.executeSelect();
            while(rs.next()){
                //String nci, String grade, int id, String nom_complet, String login, String password
                 prof =new Professeur(rs.getString("nci"), 
                                              rs.getString("grade"), 
                                              rs.getInt("id_user"), 
                                              rs.getString("nom_complet"), 
                                              rs.getString("login"), 
                                              rs.getString("password"));
                  prof.setClasseEnseignees(affDao.findByProfesseur( rs.getInt("id_user")));
                 // System.out.println(prof.getClasseEnseignees().size());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       sgbd.closeConnexion();
       return prof;
    }
    
}
