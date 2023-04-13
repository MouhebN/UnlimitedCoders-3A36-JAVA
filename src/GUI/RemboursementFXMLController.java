/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Assurance;
import entities.Remboursement;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.AssuranceCRUD;
import services.RemboursementCRUD;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class RemboursementFXMLController implements Initializable {

    @FXML
    private DatePicker dpDate;

    @FXML
    private TextField tfReponse;

    @FXML
    private TextField tfMontant;

    @FXML
    private TextField tfDepot;

    @FXML
    private TableView<Remboursement> tabRemboursement;

    @FXML
    private TableColumn<Remboursement, String> colId;

    @FXML
    private TableColumn<Remboursement, String> colDate;

    @FXML
    private TableColumn<Remboursement, String> colReponse;

    @FXML
    private TableColumn<Remboursement, String> colMontant;

    @FXML
    private TableColumn<Remboursement, String> colDepot;

    @FXML
    private TableColumn<Remboursement, Void> modifierCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        final RemboursementCRUD rc = new RemboursementCRUD();
        ObservableList<Remboursement> remboursements = FXCollections.observableArrayList(rc.afficher());
        tabRemboursement.setItems(remboursements);
        colId.setCellValueFactory(new PropertyValueFactory<Remboursement, String>("idRemboursement"));
        colDate.setCellValueFactory(new PropertyValueFactory<Remboursement, String>("dateRemboursement"));
        colReponse.setCellValueFactory(new PropertyValueFactory<Remboursement, String>("reponse"));
        colMontant.setCellValueFactory(new PropertyValueFactory<Remboursement, String>("montantRembourse"));
        colDepot.setCellValueFactory(new PropertyValueFactory<Remboursement, String>("idDepot"));
       modifierCol.setCellFactory(new Callback<TableColumn<Remboursement, Void>, TableCell<Remboursement, Void>>() {

            @Override
            public TableCell<Remboursement, Void> call(final TableColumn<Remboursement, Void> param) {
                final TableCell<Remboursement, Void> cell = new TableCell<Remboursement, Void>() {

                    private final Button btn = new Button("Modifier");

                    {
                        btn.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                // Récupérer la convention correspondante
                                Remboursement remboursement = getTableView().getItems().get(getIndex());

                                // Ouvrir la fenêtre de modification pour cette convention
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierRemboursementFXML.fxml"));
                                Parent root = null;
                                try {
                                    root = loader.load();
                                } catch (IOException ex) {
                                    Logger.getLogger(AssuranceFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                ModifierRemboursementFXMLController controller = loader.getController();
                                controller.setRemboursement(remboursement);
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.showAndWait();

                                // Rafraîchir la table des conventions
                                tabRemboursement.setItems(FXCollections.observableArrayList(rc.afficher()));
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        });
    }

    @FXML
    void ajouter(ActionEvent event) {
        RemboursementCRUD rc = new RemboursementCRUD();
        Remboursement r = null;
        LocalDate date = dpDate.getValue();
        String reponse = tfReponse.getText().trim();
        String montantText = tfMontant.getText().trim();
        String depotText = tfDepot.getText().trim();

        if (date == null || reponse.isEmpty() || montantText.isEmpty() || depotText.isEmpty()) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setHeaderText("Échec de l'ajout du remboursement");
            alertError.setContentText("Veuillez remplir tous les champs.");
            alertError.showAndWait();
            return;
        }

        try {
            Float montant = Float.parseFloat(montantText);
            int depot = Integer.parseInt(depotText);
            if (montant < 0) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Error");
                alertError.setHeaderText("Échec de l'ajout du remboursement");
                alertError.setContentText("La valeur du montant ne peut pas être négative.");
                alertError.showAndWait();
                return;
            }
            r = new Remboursement(date, reponse, montant, depot);
            rc.ajouter(r);
            Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
            alertSuccess.setTitle("Success");
            alertSuccess.setHeaderText("Ajout du remboursement réussi");
            alertSuccess.setContentText("Le remboursement a été ajouté à la base de données.");
            alertSuccess.showAndWait();
        } catch (NumberFormatException e) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setHeaderText("Échec de l'ajout du remboursement");
            alertError.setContentText("Veuillez saisir une valeur numérique valide pour le montant et le dépôt.");
            alertError.showAndWait();
        }

    }

    @FXML
    void afficher(ActionEvent event) {
        RemboursementCRUD rc = new RemboursementCRUD();
        ObservableList<Remboursement> remboursements = FXCollections.observableArrayList(rc.afficher());
        tabRemboursement.setItems(remboursements);
        colId.setCellValueFactory(new PropertyValueFactory<Remboursement, String>("idRemboursement"));
        colDate.setCellValueFactory(new PropertyValueFactory<Remboursement, String>("dateRemboursement"));
        colReponse.setCellValueFactory(new PropertyValueFactory<Remboursement, String>("reponse"));
        colMontant.setCellValueFactory(new PropertyValueFactory<Remboursement, String>("montantRembourse"));
        colDepot.setCellValueFactory(new PropertyValueFactory<Remboursement, String>("idDepot"));
    }
    
    @FXML
    private void supprimer(ActionEvent event) {

        Remboursement selectedRemboursement = tabRemboursement.getSelectionModel().getSelectedItem();
        if (selectedRemboursement != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Supprimer un remboursement");
            alert.setContentText("Êtes-vous sûr de vouloir supprimer ce remboursement ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                RemboursementCRUD sc = new RemboursementCRUD();
                boolean deleted = sc.supprimer(selectedRemboursement);
                if (deleted) {

                    tabRemboursement.getItems().remove(selectedRemboursement);

                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error Dialog");
                    errorAlert.setHeaderText("Suppression échouée");
                    errorAlert.setContentText("Une erreur est survenue lors de la suppression du remboursement.");
                    errorAlert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Aucun remboursement sélectionné");
            alert.setContentText("Veuillez sélectionner un remboursement à supprimer.");
            alert.showAndWait();
        }

    }
    
}
