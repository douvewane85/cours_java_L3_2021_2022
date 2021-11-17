/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms.entities;

import java.time.LocalDate;

/**
 *
 * @author USER
 */
public class Inscription {
    private int id;
    private LocalDate dateInscription;
    private String annee;
    private Classe classe;
    private Etudiant etu;

    public Inscription(String annee, Classe classe, Etudiant etu) {
        this.annee = annee;
        this.classe = classe;
        this.etu = etu;
    }

    public int getId() {
        return id;
    }

    public Inscription() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
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

    public Etudiant getEtu() {
        return etu;
    }

    public void setEtu(Etudiant etu) {
        this.etu = etu;
    }
    
    
}
