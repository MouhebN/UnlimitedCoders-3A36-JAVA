/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
/**
 *
 * @author MSI
 */
public class fristwindow extends Application {
    
    @Override
    public void start(Stage stage) {
        Parent root;
        try{
            root = FXMLLoader.load(getClass().getResource("Facteur.fxml"));
            Scene scene = new Scene(root);
            
            stage.setTitle("Facteur");
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
        
       
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
