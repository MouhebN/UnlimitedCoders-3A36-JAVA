/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.RendezVous;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.RendezVousCrud;
import services.UtilisateurCrud;

/**
 * FXML Controller class
 *
 * @author L390
 */
public class ConsulterRdvController implements Initializable {

    @FXML
    private TableColumn<RendezVous, LocalDate> dateID;
    @FXML
    private TableColumn<RendezVous, String> medecinID;
    @FXML
    private TableColumn<RendezVous, String> etatID;
    @FXML
    private TableColumn<RendezVous, String> descriptionID;
    @FXML
    private Button backID;
    @FXML
    private TableView<RendezVous> tableView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Set up table columns
            dateID.setCellValueFactory(new PropertyValueFactory<>("date"));
            medecinID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMedecin().getNom() + " " + cellData.getValue().getMedecin().getPrenom()));
            etatID.setCellValueFactory(new PropertyValueFactory<>("etat"));
            descriptionID.setCellValueFactory(new PropertyValueFactory<>("description"));

            // Populate table with data
            UtilisateurCrud utilisateurCrud = new UtilisateurCrud();
            Utilisateur patient = utilisateurCrud.getPatientById(8);
            RendezVousCrud rendezVousCrud = new RendezVousCrud();
            List<RendezVous> rendezVousList = rendezVousCrud.getRendezVousByUtilisateurId(patient.getId());
            ObservableList<RendezVous> observableList = FXCollections.observableList(rendezVousList);
            tableView.setItems(observableList);
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterRdvController.class.getName()).log(Level.SEVERE, null, ex);
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
