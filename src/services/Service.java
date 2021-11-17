/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.AffectationDao;
import dao.ClasseDao;
import dao.EtudiantDao;
import dao.InscriptionDao;
import dao.ProfesseurDao;
import dao.UserDao;
import entities.Affectation;
import entities.Classe;
import entities.Etudiant;
import entities.Inscription;
import entities.Professeur;
import entities.User;
import java.util.List;

/**
 *
 * @author USER
 */
public   class Service implements IService{

    //Dependences avec la Couche Dao
       AffectationDao daoAff=new AffectationDao();
       ClasseDao daoClasse=new ClasseDao();
       EtudiantDao daoEtu=new EtudiantDao();
       ProfesseurDao daoProf=new ProfesseurDao();
       InscriptionDao daoIns=new InscriptionDao();
       UserDao  daoUser=new UserDao();
       
    @Override
    public int addClasse(Classe classe) {
       return daoClasse.insert(classe);
    }

    @Override
    public boolean updateClasse(Classe classe) {
      return daoClasse.update(classe)!=0;
    }

    @Override
    public boolean deleteClasse(int id) {
        return daoClasse.delete(id)!=0; //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public List<Classe> showAllClasse() {
        return daoClasse.findAll(); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public Classe searchClasseById(int id) {
        return daoClasse.findById(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Professeur searchProfesseurByNci(String nci) {
       return daoProf.findByNci(nci); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAffectation(Professeur prof, Classe classe, String annee) {
        //Nouveau
        if(prof.getId()==0){
            int id=daoProf.insert(prof);
            prof.setId(id);
        }
        Affectation affect=new Affectation(annee, classe, prof);
        return  daoAff.insert(affect)!=0;
    }

    @Override
    public List<Classe> searchClasseByProfesseur(String nci, String anne) {
        return daoClasse.findClasseByProfesseur(nci, anne); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Professeur> showAllProfesseur() {
         return daoProf.findAll();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Etudiant searchEtudiantByMatricule(String matricule) {
        return daoEtu.findByMatricule(matricule); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addInscription(Etudiant etu, Classe classe, String annee) {
      if(etu.getId()==0){
         int id= daoEtu.insert(etu);
          etu.setId(id);
      }
         Inscription ins =new Inscription(annee, classe, etu);
         return daoIns.insert(ins)!=0;
    }

    @Override
    public Etudiant searchInscriptionByEtudiantAndYear(int id, String annee) {
        return daoEtu.findByIdAndAnnee(id, annee); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Etudiant> searchIncription(String annee) {
        return daoEtu.findAll(annee); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Etudiant> searchIncription(String annee, Classe classe) {
       return daoEtu.findAll(annee,classe);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User seConnecter(String login, String password) {
           return daoUser.findUserLoginAndPassword(login, password);
    }
    
}
