/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.categorie;
import entities.produit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.CategorieCrud;
import services.ProduitCrud;

/**
 * FXML Controller class
 *
 * @author rouja
 */
public class AjouterproduitController implements Initializable {

    @FXML
    private Button home;
    @FXML
    private TextField catid;
    @FXML
    private TextField nom;
    @FXML
    private Button ajouterprod;
    @FXML
    private TextField matricule_asseu;
    @FXML
    private TextField prix;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void home(MouseEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }

    @FXML
    private void ajouterprod(MouseEvent event) {
        String categorie_id = catid.getText();
        String Nom= nom.getText(); 
        String Matricule = matricule_asseu.getText();        
        String Prix = prix.getText(); 
 
        produit p =new produit(categorie_id,Nom,Matricule,Prix);
        ProduitCrud c = new ProduitCrud();
        
        c.ajouterProduit2(p);
    }
    
}
