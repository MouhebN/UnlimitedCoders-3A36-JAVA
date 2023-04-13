/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rouja
 */
public class HomeController implements Initializable {

    @FXML
    private Button ajouterproduit;
    @FXML
    private Button ajoutercat;
    @FXML
    private Button ajoutercomm;
    @FXML
    private Button afficherproduit;
    @FXML
    private Button afficherCommande;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterproduit(MouseEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("ajouterproduit.fxml"));
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }

    @FXML
    private void ajoutercat(MouseEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("ola.fxml"));
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }

    @FXML
    private void ajoutercomm(MouseEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("ajouterComm.fxml"));
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }
    
    @FXML
    private void afficherproduit(MouseEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("afficherproduit.fxml"));
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }
    @FXML
    private void affichercommande(MouseEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("AfficherCommande.fxml"));
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }
    
    
}
