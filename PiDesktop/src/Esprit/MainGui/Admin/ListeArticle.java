package Esprit.MainGui.Admin;

import Esprit.Dao.Classes.ArticleService;
import Esprit.Dao.Classes.CommentaireService;
import Esprit.Entities.Article;
import Esprit.Entities.Commentaire;
import Esprit.Entities.HboxArticle;
import com.jfoenix.controls.JFXButton;
import com.sun.deploy.nativesandbox.IntegrityProcess;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.geometry.VPos.CENTER;


public class ListeArticle implements Initializable {

    @FXML
    private VBox content;


    @FXML
    private TextField testReportSearchTv;

    @FXML
    private Label searchResultNumber;

    @FXML
    private ListView<HBox> testReportListView;
    int count = 0;
    private TableView<Commentaire> commentaireTable;

    private int articleId;
    private TableView<Article> articleTable;
    ArrayList<Article> ArticleArrayList;
    ArticleService produitDAO = new ArticleService() ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

       /* Article selectedArticle = articleTable.getSelectionModel().getSelectedItem();
        int articleId = selectedArticle.getId();
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CommentaireListe.fxml"));

            Parent root = loader.load();

        CommentaireListe secondController = loader.getController();
        secondController.setId(articleId);
        } catch (IOException e) {
    System.err.println("Erreur lors du chargement de la vue CommentaireListe.fxml : " + e.getMessage());
}
*/


        ArticleArrayList = new ArrayList<>();
        testReportListView.getItems().clear();

        try {
            ArticleArrayList = (ArrayList<Article>) new ArticleService().DisplayAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Article article : ArticleArrayList) {
            HBox hBox = createCard(article.getTitre(), article.getArticle_desc(),
                    article.getArticle_date().toString(), article.getNblike(), article.getNbdislike(),article.getNbcomment());
            hBox.setId(String.valueOf(article.getId()));
            System.out.println(hBox.getId());


            testReportListView.getItems().add(hBox);
        }
    }








    /*@FXML
    void onSearchBtnClick(ActionEvent event) {
        testReportSearchTv.setStyle("-fx-background-image: url('/resources/icons/ic_search.png');");
        testReportListView.getItems().clear();
        ArrayList<Article> recentPatientList = new Article().getTestReport(testReportSearchTv.getText());
        searchResultNumber.setText(recentPatientList.size() + " SEARCH RESULTS FOUND");
        for (int i = 0; i < recentPatientList.size(); i++){
            MedicalTestDetails medicalTestDetails = recentPatientList.get(i);
            HBox hBox = createCard(medicalTestDetails.getDoctorName(),medicalTestDetails.getPatientName(),
                    medicalTestDetails.getTestDate(), i);
            testReportListView.getItems().add(hBox);
        }
    }*/

    public HBox createCard(String titre, String articledesc, String date ,int like,int dis,int cmn){
        titre = titre.toUpperCase();
        articledesc = articledesc.toUpperCase();

        HBox hBox = new HBox();
        hBox.getStyleClass().add("card-background");
        hBox.setPadding(new Insets(10.0d, 20.0d, 10.0d, 20.0d));
        hBox.setSpacing(20);
        hBox.setPrefHeight(100);
        hBox.setPrefWidth(300);

CommentaireService commentaireService=new CommentaireService();
int index=count;

        ImageView icon = new ImageView("/Esprit/resources/icons/art.png");
        icon.setImage(new Image("/Esprit/resources/icons/art.png"));
        icon.setFitWidth(70);
        icon.setFitHeight(70);

        VBox vBox = new VBox();

        vBox.setSpacing(10);
        vBox.setPrefWidth(500);

        Label nameLabel = new Label(titre);
        nameLabel.getStyleClass().add("text-sub-heading");
        Label dateLabel = new Label(articledesc);
        JFXButton prescriptionBtn = new JFXButton();
        prescriptionBtn.setText("DELETE");
        prescriptionBtn.setPrefWidth(180);
        prescriptionBtn.getStyleClass().add("button-tertiary-small");

        prescriptionBtn.setOnAction(e -> {
            System.out.println("u clicked here ");
            commentaireService.delete(ArticleArrayList.get(index).getId());

            System.out.println("deellteed ");
            try {
                getArticles();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        dateLabel.getStyleClass().add("text-sub-heading-light");

        vBox.getChildren().addAll(nameLabel,dateLabel);


         VBox vBox1=new VBox();
        vBox1.getChildren().addAll(prescriptionBtn);
        vBox1.setSpacing(10);
        vBox1.setAlignment(Pos.CENTER_RIGHT);




        ImageView iconL = new ImageView("/Esprit/resources/icons/like.png");
        iconL.setImage(new Image("/Esprit/resources/icons/like.png"));
        iconL.setFitWidth(30);
        iconL.setFitHeight(30);

        ImageView iconD = new ImageView("/Esprit/resources/icons/dis.png");
        iconD.setImage(new Image("/Esprit/resources/icons/dis.png"));
        iconD.setFitWidth(30);
        iconD.setFitHeight(30);

        Label nameLike = new Label(String.valueOf(like));

        nameLabel.getStyleClass().add("text-sub-heading");
        Label nameDis = new Label(String.valueOf(dis));
        nameLabel.getStyleClass().add("text-sub-heading");


        Label namecmn = new Label(String.valueOf(cmn));
        nameLabel.getStyleClass().add("text-sub-heading");

        ImageView iconcmn = new ImageView("/Esprit/resources/icons/cmn.png");
        iconcmn.setImage(new Image("/Esprit/resources/icons/cmn.png"));
        iconcmn.setFitWidth(30);
        iconcmn.setFitHeight(30);
        // Ajouter un événement de clic sur l'image du commentaire
        iconcmn.setOnMouseClicked(event -> {

            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");



            HBox art =testReportListView.getSelectionModel().getSelectedItem();
            System.out.println(art.getId());
            int g= Integer.parseInt(art.getId());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("test.fxml"));
                Node node = loader.load();

                Test controller = loader.getController();

                controller.setId(g);

                content.getChildren().clear();
                content.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }







        });


        HBox hbox = new HBox();
        hbox.getChildren().addAll(iconL, nameLike, iconD,nameDis,iconcmn,namecmn);
        hbox.setSpacing(10); // espace entre les deux images

        vBox.getChildren().add(hbox);


        HBox hBox1 = new HBox();
        HBox.setHgrow(hBox1, Priority.ALWAYS);
        hBox1.setAlignment(Pos.CENTER_RIGHT);

        JFXButton viewMoreBtn = new JFXButton();
        viewMoreBtn.setPrefHeight(50);
        viewMoreBtn.setPrefWidth(50);
        viewMoreBtn.setPadding(new Insets(0, 20, 0, 0));






        //hBox1.getChildren().add(newLabel);
        Label newLabel = new Label(date);
        newLabel.getStyleClass().add("text-sub-heading");
        newLabel.setPrefWidth(170);
        newLabel.setPadding(new Insets(0,15,0,0));
        hBox1.getChildren().addAll(newLabel,viewMoreBtn);
        hBox.getChildren().addAll(icon, vBox, hBox1,vBox1);
        return hBox;
    }


    private void getArticles() throws SQLException {

        ArticleArrayList = new ArrayList<>();
        testReportListView.getItems().clear();

        try {
            ArticleArrayList = (ArrayList<Article>) new ArticleService().DisplayAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Article article : ArticleArrayList) {
            HBox hBox = createCard(article.getTitre(), article.getArticle_desc(),
                    article.getArticle_date().toString(), article.getNblike(), article.getNbdislike(),article.getNbcomment());
            hBox.setId(String.valueOf(article.getId()));
            System.out.println(hBox.getId());


            testReportListView.getItems().add(hBox);
        }
    }

    }



