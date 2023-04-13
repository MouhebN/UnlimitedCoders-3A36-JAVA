/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Remboursement;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.RemboursementCRUD;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ModifierRemboursementFXMLController implements Initializable {

    @FXML
    private DatePicker dpDate;

    @FXML
    private TextField tfReponse;

    @FXML
    private TextField tfMontant;

    @FXML
    private Button enregistrer;
    
    private Remboursement remboursement;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
private void validerButton(ActionEvent event) {
    Remboursement r = remboursement;
    r.setDateRemboursement(dpDate.getValue());
    r.setReponse(tfReponse.getText());
    r.setMontantRembourse(Float.parseFloat(tfMontant.getText()));

    RemboursementCRUD rc = new RemboursementCRUD();
    boolean resultat = rc.modifier(r);
    if (resultat) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText("Remboursement modifié avec succès");
        alert.showAndWait();
    }
    Stage stage = (Stage) enregistrer.getScene().getWindow();
    stage.close();
}

public void setRemboursement(Remboursement remboursement) {
    this.remboursement = remboursement;
    dpDate.setValue(remboursement.getDateRemboursement());
    tfReponse.setText(remboursement.getReponse());
    tfMontant.setText(Float.toString(remboursement.getMontantRembourse()));
}

}
