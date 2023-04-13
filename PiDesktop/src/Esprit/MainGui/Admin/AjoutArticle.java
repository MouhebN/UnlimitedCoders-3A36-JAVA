package Esprit.MainGui.Admin;

import Esprit.Dao.Classes.ArticleService;
import Esprit.Entities.Article;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class AjoutArticle implements Initializable {


    @FXML
    private TextArea titre;
    @FXML
    private TextArea descP;
    @FXML
    private Button btnIcon;
    @FXML
private DatePicker date;

    @FXML
    private   TextArea nblike;
    @FXML
    private   TextArea nbdislike;
    @FXML
    private   TextArea cmn;
    ArrayList<Article> ArticleArrayList;


    ArticleService articleDao = new ArticleService() ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void Ajouter() {



        if (!validateInput()) {
            return;
        }
        else {
            int ref=Integer.parseInt(nblike.getText());
            int ref1=Integer.parseInt(nbdislike.getText());
            int ref2=Integer.parseInt(cmn.getText());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = date.getValue();

            java.util.Date utilDate = java.sql.Date.valueOf(localDate);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            Article a = new Article(titre.getText(),descP.getText() ,sqlDate,ref,ref1,ref2) ;
            System.out.println("addeddd wereerrey");
            System.out.println(a);

            articleDao.insertArticle(a);
            System.out.println("addeddd wereerrey"); }
    }



    private  boolean validateInput() {
        if (nblike.getText().isEmpty() || Integer.parseInt(nblike.getText()) <= 0 ) {
            showAlert("Titre field Invalid.");
            return false;
        }
        if (nbdislike.getText().isEmpty() || Integer.parseInt(nbdislike.getText()) <= 0 ) {
            showAlert("nbdislike field Invalid.");
            return false;
        }
        if (cmn.getText().isEmpty() || Integer.parseInt(cmn.getText()) <= 0 ) {
            showAlert("nbLike field Invalid.");
            return false;
        }

        if (descP.getText().isEmpty()) {
            showAlert("Description field cannot be empty.");
            return false;
        }


        return true;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error check your inputs");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }







}
