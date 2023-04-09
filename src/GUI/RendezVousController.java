/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.RendezVous;
import entities.Utilisateur;
import java.io.IOException;
import static java.lang.Math.E;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.RendezVousCrud;
import services.UtilisateurCrud;

/**
 * FXML Controller class
 *
 * @author L390
 */
public class RendezVousController implements Initializable {

    @FXML
    private ComboBox<Utilisateur> medecinID;
    @FXML
    private TextField etatID;
    @FXML
    private TextField DescriptionID;
    @FXML
    private DatePicker DateID;
    @FXML
    private Button btnID;
    @FXML
    private Button backID;
    @FXML
    private ComboBox<LocalTime> timeID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            UtilisateurCrud utilisateurCrud = new UtilisateurCrud();
            ObservableList<Utilisateur> medecins = utilisateurCrud.getAllMedecins();
            medecinID.getItems().addAll(medecins);
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
        timeID.getItems().addAll(
                LocalTime.of(8, 0),
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                LocalTime.of(12, 0),
                LocalTime.of(13, 0),
                LocalTime.of(14, 0),
                LocalTime.of(15, 0),
                LocalTime.of(16, 0),
                LocalTime.of(17, 0)
        );
    }

    @FXML
    private void save(ActionEvent event) throws SQLException {
        Utilisateur medecin = (Utilisateur) medecinID.getValue();
        String etat = etatID.getText();
        String description = DescriptionID.getText();
        LocalDate localDate = DateID.getValue();
        LocalTime localTime = (LocalTime) timeID.getValue();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        Instant instant = Instant.from(localDateTime.atZone(ZoneId.systemDefault()));
        Timestamp date = Timestamp.from(instant);
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime selectedDateTime = date.toLocalDateTime();
        UtilisateurCrud utilisateurCrud = new UtilisateurCrud();
        Utilisateur patient = utilisateurCrud.getPatientById(8);
        RendezVousCrud rdc = new RendezVousCrud();

        if (selectedDateTime.isBefore(currentDateTime)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Selected date is in the past!");
            alert.showAndWait();

        } else if (etat.isEmpty() || description.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Etat and description fields cannot be empty!");
            alert.showAndWait();
            
        } else {
            if (rdc.isRdvAlreadyReserved(medecin.getId(), date)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("This date and time are already reserved for the selected doctor!");
                alert.showAndWait(); 
                
            }else{          
            RendezVous rdv = new RendezVous(date, medecin, patient, description, etat);
            rdc.ajouterRdv2(rdv);
            }
        }
    }

    @FXML
    private void backToMainPage(ActionEvent event) throws IOException {
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        //Get the current window and set the scene to the main page scene
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }
}
