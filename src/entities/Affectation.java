/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author USER
 */
public class Affectation {
    private int id;

   
    private String annee;
    //Attributs Navigationnel
    private Classe classe;
    private Professeur professeur;

    public Affectation() {
    }
    
     public Affectation(String annee, Classe classe, Professeur professeur) {
        this.annee = annee;
        this.classe = classe;
        this.professeur = professeur;
    }

    public Affectation(int id, String annee, Classe classe, Professeur professeur) {
        this.id = id;
        this.annee = annee;
        this.classe = classe;
        this.professeur = professeur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Professeur getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }
     
     
    
    
}
