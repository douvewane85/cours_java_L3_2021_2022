/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class Professeur extends User {
    private String nci;
    private String grade;
    private List<AffectationClasse> classeEnseignees=new ArrayList<>();

    public List<AffectationClasse> getClasseEnseignees() {
        return classeEnseignees;
    }

    public void setClasseEnseignees(List<AffectationClasse> classeEnseignees) {
        this.classeEnseignees = classeEnseignees;
    }

    public Professeur() {
         this.role="ROLE_PROFESSEUR";
    }

    public Professeur(String nci,String grade, String nom_complet, String login, String password) {
        super(nom_complet, login, password);
        this.grade = grade;
        this.nci = nci;
        this.role="ROLE_PROFESSEUR";
    }

    public Professeur(String nci, String grade, int id, String nom_complet, String login, String password) {
        super(id, nom_complet, login, password);
        this.nci = nci;
        this.grade = grade;
         this.role="ROLE_PROFESSEUR";
    }

    public Professeur(String grade, int id, String nom_complet, String login, String password) {
        super(id, nom_complet, login, password);
        this.grade = grade;
        this.role="ROLE_PROFESSEUR";
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getNci() {
        return nci;
    }

    public void setNci(String nci) {
        this.nci = nci;
    }
    
    
}
