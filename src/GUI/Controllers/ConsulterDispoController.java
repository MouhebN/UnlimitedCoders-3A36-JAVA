/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;
import Entity.Calendrier;
import Entity.RendezVous;
import Entity.Utilisateur;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import services.CalendrierCrud;
import services.RendezVousCrud;
import services.UtilisateurCrud;

/**
 * FXML Controller class
 *
 * @author L390
 */
public class ConsulterDispoController implements Initializable {

    @FXML
    private TableColumn<Calendrier, LocalDateTime> endID;
    @FXML
    private Button backID;
    @FXML
    private TableColumn<Calendrier, Integer> dispoID;
    @FXML
    private TableColumn<Calendrier, LocalDateTime> startdadteID;
    @FXML
    private TableView<Calendrier> tableID;
    @FXML
    private TableColumn<Calendrier, Void> deleteID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            dispoID.setCellValueFactory(new PropertyValueFactory<>("id"));
            startdadteID.setCellValueFactory(new PropertyValueFactory<>("heure_debut"));
            endID.setCellValueFactory(new PropertyValueFactory<>("heure_fin"));
            // Populate table with data
            UtilisateurCrud utilisateurCrud = new UtilisateurCrud();
            Utilisateur medecin = utilisateurCrud.getMedecinById(6);
            CalendrierCrud calendrierCrud = new CalendrierCrud();
            List<Calendrier> calendrierList = calendrierCrud.getDispoByMedecinId(medecin.getId());
            ObservableList<Calendrier> observableList = FXCollections.observableList(calendrierList);
            tableID.setItems(observableList);

            // Set up column with delete button for each row
            Callback<TableColumn<Calendrier, Void>, TableCell<Calendrier, Void>> cellFactory = new Callback<TableColumn<Calendrier, Void>, TableCell<Calendrier, Void>>() {
                @Override
                public TableCell<Calendrier, Void> call(final TableColumn<Calendrier, Void> param) {
                    final TableCell<Calendrier, Void> cell = new TableCell<Calendrier, Void>() {

                        private final Button deleteButton = new Button("Supprimer");

                        {
                            deleteButton.setOnAction((ActionEvent event) -> {
                                Calendrier calendrier = getTableView().getItems().get(getIndex());
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation");
                                alert.setHeaderText("Supprimer une disponibilité");
                                alert.setContentText("Êtes-vous sûr(e) de vouloir supprimer cette disponibilité ?");

                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.isPresent() && result.get() == ButtonType.OK) {
                                    try {
                                        CalendrierCrud calendrierCrud = new CalendrierCrud();
                                        calendrierCrud.supprimerDispo(calendrier.getId());
                                        tableID.getItems().remove(calendrier);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(ConsulterDispoController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });

                        }
                        //// updating the graphical representation of the cell.
                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(deleteButton);
                            }
                        }
                    };
                    return cell;
                }
            };
            deleteID.setCellFactory(cellFactory);
            tableID.setItems(observableList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    private void backToMainPage(ActionEvent event) throws IOException {
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("/GUI/mainPageMedecin.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }

}
