/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthifiedpi;

import entities.Calendrier;
import entities.RendezVous;
import entities.Utilisateur;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import services.CalendrierCrud;
import services.RendezVousCrud;
import util.Connexion;
import java.time.Month;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import ressources.SoundPlayer;
import services.UtilisateurCrud;

/**
 *
 * @author L390
 */
public class HealthifiedPi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Connexion mc = Connexion.getInstance();
        // Connexion mc2 = Connexion.getInstance();
        //: System.out.println(mc.hashCode() +"--"+mc2.hashCode());

        RendezVousCrud rdc = new RendezVousCrud();
        CalendrierCrud cal = new CalendrierCrud();

        // Date date = new Date(1636712200030L);
        //RendezVous r = new RendezVous(date,6,8,"urgent", "java222");
        //   Utilisateur Medecin = new Utilisateur(6);
        //Utilisateur Patient = new Utilisateur(8);
        //   rdc.modifierRdv(159, new Date (1636712200030L), Medecin, Patient, "Urgent", "olaaa ");
        //  System.out.println(rdc.afficherRendezvouses());
        ///               Ajouter Dispo      /////
        /*   Utilisateur medecin = new Utilisateur(6);
            LocalDateTime heureDebut = LocalDateTime.of(2023, 3, 11, 8, 30);
            LocalDateTime heureFin = LocalDateTime.of(2023, 3, 11, 10, 0);
            Calendrier calendrier = new Calendrier( heureDebut, heureFin,medecin);
            cal.ajouterDispo(calendrier);*/
        ////////////////////////////////////////
        // call the afficherDispo(id) function
        /* List<Calendrier> disponibilites = cal.afficherDispo(6);
            for (Calendrier disponibilite : disponibilites) {
            System.out.println(disponibilite);
            }*/
        //////////////////////////////
        //////         Supprimer dispo         /////
        // cal.supprimerDispo(71);
        //////////////////////////////
        ////         Modifier Dispo by id           ////
        // cal.modifierDispo(72, LocalDateTime.of(2023, Month.APRIL, 4, 9, 0), LocalDateTime.of(2023, Month.APRIL, 4, 11, 0));
        
    }
}
