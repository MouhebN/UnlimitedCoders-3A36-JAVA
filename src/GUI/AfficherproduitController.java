/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.produit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ProduitCrud;



/**
 * FXML Controller class
 *
 * @author rouja
 */
public class AfficherproduitController implements Initializable {

    @FXML
    private TableView<produit> tabProduit;
    @FXML
    private TableColumn<produit, String> colCategorie;

    @FXML
    private TableColumn<produit, String> colMatricule;

    @FXML
    private TableColumn<produit, String> colNom;

    @FXML
    private TableColumn<produit, String> colPrix;


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
    private void afficherprod(MouseEvent event) {
        ProduitCrud pc = new ProduitCrud();
        ObservableList<produit> produits = FXCollections.observableArrayList(pc.afficherProduit());
        tabProduit.setItems(produits);
        colCategorie.setCellValueFactory(new PropertyValueFactory<produit, String>("categorie_id"));
        colMatricule.setCellValueFactory(new PropertyValueFactory<produit, String>("matricule_asseu"));
        colNom.setCellValueFactory(new PropertyValueFactory<produit, String>("nom"));
        colPrix.setCellValueFactory(new PropertyValueFactory<produit, String>("prix"));
        
    }
}
