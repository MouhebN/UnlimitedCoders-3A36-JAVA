/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author L390
 */
public class Calendrier {
   private int id ; 
   private Timestamp heure_debut ;
   private Timestamp heure_fin ;
   private Utilisateur medecin ; 

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(Timestamp heure_debut) {
        this.heure_debut = heure_debut;
    }

    public Timestamp getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(Timestamp heure_fin) {
        this.heure_fin = heure_fin;
    }

    public Utilisateur getMedecin() {
        return medecin;
    }

    public void setMedecin(Utilisateur medecin) {
        this.medecin = medecin;
    }

    public Calendrier() {
    }

    public Calendrier(Timestamp heure_debut, Timestamp heure_fin, Utilisateur medecin) {
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.medecin = medecin;
    }
    

    public Calendrier(int id, Timestamp heure_debut, Timestamp heure_fin, Utilisateur medecin) {
        this.id = id;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.medecin = medecin;
    }
    public Calendrier(Utilisateur medecin, Timestamp heure_debut, Timestamp heure_fin) {
        
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.medecin = medecin;
    }

    
    @Override
    public String toString() {
        return "Calendrier{" + "id=" + id + ", heure_debut=" + heure_debut + ", heure_fin=" + heure_fin + ", medecin=" + medecin + '}';
    }
   
    
}
