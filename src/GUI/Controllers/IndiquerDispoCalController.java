/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import Entity.Calendrier;
import Entity.RendezVous;
import Entity.Utilisateur;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import com.calendarfx.view.CalendarView;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import services.CalendrierCrud;
import services.RendezVousCrud;

/**
 * FXML Controller class
 *
 * @author L390
 */
public class IndiquerDispoCalController implements Initializable {

    @FXML
    private Button btnID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void toCalendar(ActionEvent event) throws SQLException {
        Utilisateur medecin = new Utilisateur(6);
        displayDoctorDispo(medecin);
    }

    public void displayDoctorDispo(Utilisateur medecin) throws SQLException {

        CalendrierCrud cal = new CalendrierCrud();

        // Convert the appointments data to CalendarFX-compatible format
        List<CalendarSource> calendarData = new ArrayList<>();
        // Get the availability data for the doctor
        List<Calendrier> availabilityData = cal.getDispoByMedecinId(6);

        // Convert the availability data to CalendarFX-compatible format
        CalendarSource availabilitySource = new CalendarSource("Availability");
        availabilityData.stream().map((availability) -> {
            // Convert the availability start and end times to LocalDateTime
            LocalDateTime localStart = availability.getHeure_debut()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            LocalDateTime localEnd = availability.getHeure_fin()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            Interval interval = new Interval(localStart, localEnd);
            return interval;
        }).map((interval) -> {
            // Create a new Entry object for the availability
            String availabilityDetails = "Available";
            Entry<String> entry = new Entry<>(availabilityDetails, interval);
            // Associate the Calendrier object with the entry
            entry.setUserObject(availabilityDetails);
            return entry;
        }).forEach((entry) -> {
            // Add the entry to the availability source
            availabilitySource.getCalendars().add(new Calendar("Calendar"));
            availabilitySource.getCalendars().get(availabilitySource.getCalendars().size() - 1).addEntry(entry);
        });

        // Iterate over the entries in the availability source and add the event listener to each one
        /* availabilitySource.getCalendars().forEach(calendar -> {
            calendar.getCalendarEntries().forEach(entry -> {
                // Add the event listener to the entry
                entry.setOnCalendarEventChanged(event -> {
                    // Get the new start and end times of the entry
                    LocalDateTime newStart = event.getNewInterval().getStart();
                    LocalDateTime newEnd = event.getNewInterval().getEnd();

                    // Update the corresponding Calendrier object in the database
                    CalendrierCrud calendrierCrud = new CalendrierCrud();
                    Calendrier calendrier = (Calendrier) entry.getUserObject();
                    calendrier.setHeure_debut(Timestamp.valueOf(newStart));
                    calendrier.setHeure_fin(Timestamp.valueOf(newEnd));
                    // Assume heureDebut and heureFin are Timestamp objects representing the modified start and end times

                    try {
                        int id = cal.getDispoByDateTimeRange(Timestamp.valueOf(newStart), Timestamp.valueOf(newEnd));
                        calendrierCrud.modifierDispo(id, Timestamp.valueOf(newStart), Timestamp.valueOf(newEnd));
                    } catch (SQLException ex) {
                        Logger.getLogger(IndiquerDispoCalController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            });
        });*/
        
        
        calendarData.add(availabilitySource);
        CalendarView calendarView = new CalendarView();
        calendarView.getCalendarSources().setAll(calendarData);
        calendarView.setRequestedTime(LocalTime.now());
        

        Scene scene = new Scene(calendarView);
       
        Stage stage = (Stage) btnID.getScene().getWindow();

        stage.setScene(scene);

    }

}
