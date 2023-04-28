/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author L390
 */
public class RendezVous {

    private int id;
    private Timestamp date;
    private Utilisateur medecin;
    private Utilisateur patient;
    private String description;
    private String etat;

    public RendezVous(int id) {
        this.id = id;
    }

    public RendezVous() {
    }

    public RendezVous(Timestamp date, Utilisateur patient, String description, String etat) {
        this.date = date;
        this.patient = patient;
        this.description = description;
        this.etat = etat;
    }

    public RendezVous(int id, Timestamp date, Utilisateur medecin, Utilisateur patient, String description, String etat) {
        this.id = id;
        this.date = date;
        this.medecin = medecin;
        this.patient = patient;
        this.description = description;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Utilisateur getMedecin() {
        return medecin;
    }

    public void setMedecin(Utilisateur medecin) {
        this.medecin = medecin;
    }

    public Utilisateur getPatient() {
        return patient;
    }

    public void setPatient(Utilisateur patient) {
        this.patient = patient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public RendezVous(Timestamp date, Utilisateur medecin, Utilisateur patient, String description, String etat) {
        this.date = date;
        this.medecin = medecin;
        this.patient = patient;
        this.description = description;
        this.etat = etat;
    }

    public RendezVous(Timestamp date, String description, String etat) {
        this.date = date;
        this.description = description;
        this.etat = etat;

    }

    public RendezVous(int id, Timestamp date, Utilisateur medecin, Utilisateur patient) {
        this.id = id;
        this.date = date;
        this.medecin = medecin;
        this.patient = patient;
    }

    @Override
public String toString() {
    return "Patient: " + patient.getNom() + " " + patient.getPrenom() + ", " +
           "Medecin: " + medecin.getNom() + " " + medecin.getPrenom() + ", " +
           "Date: " + date.toString() + ", " +
           "Etat: " + etat;
}


} 
