/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms.services;

import java.time.LocalDate;
import java.util.List;
import sms.dao.AffectationClasseDao;
import sms.dao.AnneeDao;
import sms.dao.ClasseDao;
import sms.dao.EtudiantDao;
import sms.dao.InscriptionDao;
import sms.dao.ProfesseurDao;
import sms.dao.UserDao;
import sms.entities.AffectationClasse;
import sms.entities.Classe;
import sms.entities.Etudiant;
import sms.entities.Inscription;
import sms.entities.Professeur;
import sms.entities.User;

/**
 *
 * @author USER
 */
public class SmsService implements SmsInterface{
    private   AffectationClasseDao affDao;
    private ClasseDao classeDao;
    private InscriptionDao insDao;
    private UserDao userDao;
    private AnneeDao anneeDao;
    private ProfesseurDao profDao;
    private EtudiantDao etuDao;

    public EtudiantDao getEtuDao() {
        return etuDao;
    }

    public void setEtuDao(EtudiantDao etuDao) {
        this.etuDao = etuDao;
    }

    public ProfesseurDao getProfDao() {
        return profDao;
    }

    public void setProfDao(ProfesseurDao profDao) {
        this.profDao = profDao;
    }

    public void setAnneeDao(AnneeDao anneeDao) {
        this.anneeDao = anneeDao;
    }
    
    
    public void setAffDao(AffectationClasseDao affDao) {
        this.affDao = affDao;
    }

    public void setClasseDao(ClasseDao classeDao) {
        this.classeDao = classeDao;
    }

    public void setInsDao(InscriptionDao insDao) {
        this.insDao = insDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

         
    public SmsService() {
          
    }
    

    @Override
    public boolean inscription(Etudiant etu, Classe classe, String annee) {
      Inscription ins =new Inscription();
      //Premiere Inscription
      if(etu.getId()==0){
         int id= etuDao.insert(etu);
         etu.setId(id);
      }
        ins.setClasse(classe);
        ins.setEtu(etu);
        ins.setDateInscription(LocalDate.now());
        ins.setAnnee(annee);
      return insDao.insert(ins)!=0;
    }

    @Override
    public boolean ajouterClasse(Classe cl) {
       return classeDao.insert(cl)!=0;
    }

    @Override
    public List<Classe> listerClasse() {
        return classeDao.findAll();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modifierClasse(Classe cl) {
       return classeDao.update(cl)!=0;
    }

    @Override
    public List<Etudiant> ListerInscristUneClasse(int idClasse, String annee) {
       return etuDao.findAll(annee, idClasse);
    }

    @Override
    public boolean ajouterProfesseur(Professeur prof) {
      return profDao.insert(prof)!=0;
    }

    @Override
    public List<Professeur> listerProfesseur() {
       return profDao.findAll();
    }

    @Override
    public void affecter(Professeur prof, List<Classe> classes, String annee) {
      //Premiere Inscription
      if(prof.getId()==0){
         int id= profDao.insert(prof);
         prof.setId(id);
      }
        System.out.println( classes.size());
      classes.stream()
              .filter(cl->cl.getAction().compareToIgnoreCase("add")==0)
              .forEach(cl->{
               AffectationClasse ins =new AffectationClasse();
               ins.setClasse(cl);
              ins.setProf(prof);
              ins.setAnnee(annee);
              affDao.insert(ins);
      });
      
    }

    @Override
    public List<Classe> listerClasseUnProfesseur(int idProf, String anne) {
         return classeDao.findAll();
    }

    @Override
    public User connexion(String login, String password) {
       return userDao.findLoginAndPassword(login, password);
    }

    @Override
    public List<String> listerAnneeScolaire() {
        return anneeDao.findAll();
    }

    @Override
    public Classe rechercherClasseParLibelle(String libelle) {
        return classeDao.findByLibelle(libelle); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Professeur rechercherProfesseurParNci(String nci) {
      return profDao.findByNci(nci);
    }

    @Override
    public List<Etudiant> listerEtudiant() {
       return etuDao.findAll();
    }

    @Override
    public List<Etudiant> ListerInscristAnne(String annee) {
       return etuDao.findAll(annee);
    }

    @Override
    public Etudiant rechercherEtudiantParMatricule(String matricule) {
       return etuDao.findByMatricule(matricule);
    }
    
}
