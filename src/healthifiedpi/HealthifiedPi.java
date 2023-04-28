/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthifiedpi;

import Entity.Calendrier;
import Entity.RendezVous;
import Entity.Utilisateur;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import services.CalendrierCrud;
import services.RendezVousCrud;
import Utils.Connexion;
import java.time.LocalDate;
import java.time.Month;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import ressources.SoundPlayer;
import services.EmailReminderJob;
import services.UtilisateurCrud;

/**
 *
 * @author L390
 */
public class HealthifiedPi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Utilisateur a = new Utilisateur();
        Connexion mc = Connexion.getInstance();
        RendezVous rdv = new RendezVous();
        UtilisateurCrud mouheb = new UtilisateurCrud();
        RendezVousCrud rdc = new RendezVousCrud();
        CalendrierCrud cal = new CalendrierCrud();

  
        try{
              RendezVousCrud rdvc = new RendezVousCrud();
        // call the getNumAppointmentsPerMonthInYear method with some user ID and a year of your choice
        int[] appointmentsPerMonth = rdvc.getNumAppointmentsPerMonthInYear(6, LocalDate.now().getYear());
            System.out.println(LocalDate.now().getYear());
        // print the number of appointments for each month
        for (int i = 0; i < 12; i++) {
            System.out.printf("Appointments in %s: %d%n", Month.of(i + 1), appointmentsPerMonth[i]);
        }
    }catch (SQLException ex) {
        ex.printStackTrace();
    }
}

}
