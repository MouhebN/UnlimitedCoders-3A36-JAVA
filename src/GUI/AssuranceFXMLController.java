/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Assurance;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.AssuranceCRUD;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AssuranceFXMLController implements Initializable {

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfPlafond;

    @FXML
    private TextField tfAdresse;

    @FXML
    private TableView<Assurance> tabAssurance;

    @FXML
    private TableColumn<Assurance, String> colId;

    @FXML
    private TableColumn<Assurance, String> colNom;

    @FXML
    private TableColumn<Assurance, String> colPlafond;

    @FXML
    private TableColumn<Assurance, String> colAssurance;

    @FXML
    private TableColumn<Assurance, Void> modifierCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        final AssuranceCRUD ac = new AssuranceCRUD();
        ObservableList<Assurance> assurances = FXCollections.observableArrayList(ac.afficher());

        tabAssurance.setItems(assurances);
        colId.setCellValueFactory(new PropertyValueFactory<Assurance, String>("idAssurance"));
        colNom.setCellValueFactory(new PropertyValueFactory<Assurance, String>("nomAssurance"));
        colPlafond.setCellValueFactory(new PropertyValueFactory<Assurance, String>("plafond"));
        colAssurance.setCellValueFactory(new PropertyValueFactory<Assurance, String>("adresseAssurance"));

        modifierCol.setCellFactory(new Callback<TableColumn<Assurance, Void>, TableCell<Assurance, Void>>() {

            @Override
            public TableCell<Assurance, Void> call(final TableColumn<Assurance, Void> param) {
                final TableCell<Assurance, Void> cell = new TableCell<Assurance, Void>() {

                    private final Button btn = new Button("Modifier");

                    {
                        btn.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                // Récupérer la convention correspondante
                                Assurance assurance = getTableView().getItems().get(getIndex());

                                // Ouvrir la fenêtre de modification pour cette convention
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierAssuranceFXML.fxml"));
                                Parent root = null;
                                try {
                                    root = loader.load();
                                } catch (IOException ex) {
                                    Logger.getLogger(AssuranceFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                ModifierAssuranceFXMLController controller = loader.getController();
                                controller.setAssurance(assurance);
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.showAndWait();

                                // Rafraîchir la table des conventions
                                tabAssurance.setItems(FXCollections.observableArrayList(ac.afficher()));
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
    private void ajouter(ActionEvent event) {
        AssuranceCRUD sc = new AssuranceCRUD();
        Assurance a = null;
        String nom = tfNom.getText().trim();
        String adresse = tfAdresse.getText().trim();
        String plafondText = tfPlafond.getText().trim();

        if (nom.isEmpty() || adresse.isEmpty() || plafondText.isEmpty()) {
            Alert alertError = new Alert(AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setHeaderText("Échec de l'ajout de l'assurance");
            alertError.setContentText("Veuillez remplir tous les champs.");
            alertError.showAndWait();
            return;
        }

        try {
            Float plafond = Float.parseFloat(plafondText);
            if (plafond < 0) {
                Alert alertError = new Alert(AlertType.ERROR);
                alertError.setTitle("Error");
                alertError.setHeaderText("Échec de l'ajout de l'assurance");
                alertError.setContentText("La valeur du plafond ne peut pas être négative.");
                alertError.showAndWait();
                return;
            }
            a = new Assurance(nom, adresse, plafond);
            sc.ajouter(a);
            Alert alertSuccess = new Alert(AlertType.INFORMATION);
            alertSuccess.setTitle("Success");
            alertSuccess.setHeaderText("Ajout de l'assurance réussi");
            alertSuccess.setContentText("L'assurance a été ajoutée à la base de données.");
            alertSuccess.showAndWait();
        } catch (NumberFormatException e) {
            Alert alertError = new Alert(AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setHeaderText("Échec de l'ajout de l'assurance");
            alertError.setContentText("Veuillez saisir une valeur numérique valide pour le plafond.");
            alertError.showAndWait();
        }
    }

    @FXML
    private void afficher(ActionEvent event) {

        AssuranceCRUD ac = new AssuranceCRUD();
        ObservableList<Assurance> assurances = FXCollections.observableArrayList(ac.afficher());
        tabAssurance.setItems(assurances);
        colId.setCellValueFactory(new PropertyValueFactory<Assurance, String>("idAssurance"));
        colNom.setCellValueFactory(new PropertyValueFactory<Assurance, String>("nomAssurance"));
        colPlafond.setCellValueFactory(new PropertyValueFactory<Assurance, String>("plafond"));
        colAssurance.setCellValueFactory(new PropertyValueFactory<Assurance, String>("adresseAssurance"));
    }

    @FXML
    private void supprimer(ActionEvent event) {

        Assurance selectedAssurance = tabAssurance.getSelectionModel().getSelectedItem();
        if (selectedAssurance != null) {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Supprimer une assurance");
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cette assurance ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                AssuranceCRUD sc = new AssuranceCRUD();
                boolean deleted = sc.supprimer(selectedAssurance);
                if (deleted) {

                    tabAssurance.getItems().remove(selectedAssurance);

                } else {
                    Alert errorAlert = new Alert(AlertType.ERROR);
                    errorAlert.setTitle("Error Dialog");
                    errorAlert.setHeaderText("Suppression échouée");
                    errorAlert.setContentText("Une erreur est survenue lors de la suppression de l'assurance.");
                    errorAlert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Aucune assurance sélectionnée");
            alert.setContentText("Veuillez sélectionner une assurance à supprimer.");
            alert.showAndWait();
        }

    }

}
