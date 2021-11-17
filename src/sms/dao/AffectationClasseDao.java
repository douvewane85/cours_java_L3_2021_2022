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
import sms.entities.AffectationClasse;
import sms.entities.Classe;

/**
 *
 * @author USER
 */
public class AffectationClasseDao implements IDao<AffectationClasse>{
   private final String SQL_INSERT="INSERT INTO `classe_prof` (`classe_id`, `prof_id`, `anne_scolaire`) VALUES (?, ?,?)";
   private final String SQL_SELECT_BY_PROFESSEUR="SELECT * FROM `classe_prof` where prof_id=?";
     DaoMysql sgbd=new DaoMysql();
     ClasseDao classeDao=new ClasseDao();
//     ProfesseurDao profDao=new ProfesseurDao();
    @Override
    public int insert(AffectationClasse affClasse) {
      int result=0;
      sgbd.ouvrirConnexionBD();
      sgbd.preparerRequete(SQL_INSERT);
        try {
            sgbd.getPs().setInt(1,affClasse.getClasse().getId());
            sgbd.getPs().setInt(2,affClasse.getProf().getId());
            sgbd.getPs().setString(3,affClasse.getAnnee());
           // System.err.println(affClasse.getClasse().getId()+" "+affClasse.getProf().getId()+" "+affClasse.getAnnee());
            result=  sgbd.executeMisAJour();
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
      sgbd.closeConnexion();
      return result; 
    }

    @Override
    public int update(AffectationClasse obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AffectationClasse findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AffectationClasse> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public List<AffectationClasse> findByProfesseur(int id_prof) {
        List<AffectationClasse> datas=new ArrayList<>();
        sgbd.ouvrirConnexionBD();
           sgbd.preparerRequete(SQL_SELECT_BY_PROFESSEUR);
         try {
             sgbd.getPs().setInt(1, id_prof);
              ResultSet rs=sgbd.executeSelect();
       
            while(rs.next()){
               AffectationClasse data =new AffectationClasse(
                        rs.getInt("id_classe_prof"),
                         classeDao.findById( rs.getInt("classe_id")),
                         null,
                         rs.getString("anne_scolaire")
                );
               
               datas.add(data);
            
            }
        }catch (SQLException ex) {
             Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
         } 
        sgbd.closeConnexion();
        return datas;
    }
    
}
