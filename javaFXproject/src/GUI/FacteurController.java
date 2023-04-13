/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import entities.Facteur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.FacteurGrud;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class FacteurController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfidpatient;
    @FXML
    private TextField tfidmedicament;
    @FXML
    private TextField tfnommed;
    @FXML
    private TextField tfdosage;
    @FXML
    private TextField tfcin;
    @FXML
    private TextField tfprix;
    @FXML
    private Button tfsave;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveFacteur(ActionEvent event) {
        String cin =tfcin.getText();
        String nom =tfnom.getText();
        String prenom =tfprenom.getText();
        int id_patient =Integer.parseInt(tfidpatient.getText()) ;
        
        int id_medicament =Integer.parseInt(tfidmedicament.getText());
        String nom_med =tfnommed.getText();
        String dosage =tfdosage.getText();
        int prix  =Integer.parseInt (tfprix.getText());
        
        Facteur c =new Facteur(cin,nom,prenom,id_patient,id_medicament,nom_med,dosage,prix);
        FacteurGrud pc = new FacteurGrud();
        pc.ajouterfacteur(c);
    }
    
}
