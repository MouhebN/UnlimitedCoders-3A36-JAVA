/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDateTime;

/**
 *
 * @author L390
 */
public class Calendrier {
   private int id ; 
   private LocalDateTime heure_debut ;
   private LocalDateTime heure_fin ;
   private Utilisateur medecin ; 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(LocalDateTime heure_debut) {
        this.heure_debut = heure_debut;
    }

    public LocalDateTime getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(LocalDateTime heure_fin) {
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

    public Calendrier(LocalDateTime heure_debut, LocalDateTime heure_fin, Utilisateur medecin) {
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.medecin = medecin;
    }
    

    public Calendrier(int id, LocalDateTime heure_debut, LocalDateTime heure_fin, Utilisateur medecin) {
        this.id = id;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.medecin = medecin;
    }

    @Override
    public String toString() {
        return "Calendrier{" + "id=" + id + ", heure_debut=" + heure_debut + ", heure_fin=" + heure_fin + ", medecin=" + medecin + '}';
    }
   
    
}
