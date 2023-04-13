package Esprit.MainGui.mainback;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class dashboard implements Initializable {

    @FXML
    private StackPane myStackPane;

    @FXML
    private AnchorPane frameLayout;

    @FXML
    private JFXButton navSupportBtn;

    @FXML
    private JFXButton navAppointmentsBtn;

    @FXML
    private JFXButton navViewAllTestReportBtn;

    @FXML
    private JFXButton navSettingsBtn;

    @FXML
    private JFXButton navReportBug;

    @FXML
    private JFXButton navAddPatientBtn;

    @FXML
    private JFXButton navViewPatientBtn;

    @FXML
    private StackPane stackPaneRoot;

    @FXML
    private JFXButton navTestReportsBtn;

    @FXML
    private Label receptionNameTv;

    @FXML
    private JFXButton navLogOutBtn;

    private JFXButton guiButtonCurrent;
    private JFXButton guiButtonPrevious;
    private Timeline timeline;
    private JFXDialog dialog;
    URL url;
    ResourceBundle rs;
    private ActionEvent actionEvent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        guiButtonCurrent = navAppointmentsBtn;
        guiButtonPrevious = navAppointmentsBtn;
        receptionNameTv.setText("Admin");
        // initially load appointments UI
        try {
            frameLayout.getChildren().clear();
            VBox root = FXMLLoader.load(getClass().getResource("/Esprit/MainGui/Admin/ListeArticle.fxml"));
            root = (VBox) makeResponsive(root, "vbox");
            // get the controller for the second view


            frameLayout.getChildren().add(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    private Parent makeResponsive(Parent root, String node) {
        switch (node) {
            case "vbox":
                VBox vBox = (VBox) root;
                // to make the contents of the frame responsive
                vBox.setPrefWidth(930);
                vBox.setPrefHeight(847);
                AnchorPane.setTopAnchor(vBox, 0.0d);
                AnchorPane.setBottomAnchor(vBox, 0.0d);
                AnchorPane.setLeftAnchor(vBox, 0.0d);
                AnchorPane.setRightAnchor(vBox, 0.0d);
                return vBox;
            case "ScrollPane":
                ScrollPane scrollPane = (ScrollPane) root;
                // to make the contents of the frame responsive
                scrollPane.setPrefWidth(930);
                scrollPane.setPrefHeight(847);
                AnchorPane.setTopAnchor(scrollPane, 0.0d);
                AnchorPane.setBottomAnchor(scrollPane, 0.0d);
                AnchorPane.setLeftAnchor(scrollPane, 0.0d);
                AnchorPane.setRightAnchor(scrollPane, 0.0d);
                return scrollPane;
            case "BorderPane":
                BorderPane borderPane = (BorderPane) root;
                // to make the contents of the frame responsive
                borderPane.setPrefWidth(930);
                borderPane.setPrefHeight(847);
                AnchorPane.setTopAnchor(borderPane, 0.0d);
                AnchorPane.setBottomAnchor(borderPane, 0.0d);
                AnchorPane.setLeftAnchor(borderPane, 0.0d);
                AnchorPane.setRightAnchor(borderPane, 0.0d);
                return borderPane;
        }
        return null;
    }

    @FXML
    void onAppointmentsClick(ActionEvent event) {
        if (!guiButtonCurrent.equals(navAppointmentsBtn)) {
            guiButtonCurrent = navAppointmentsBtn;
            guiChangeButtonStyle();
            guiButtonPrevious = navAppointmentsBtn;
            try {
                frameLayout.getChildren().clear();
                VBox root = FXMLLoader.load(getClass().getResource("/Esprit/MainGui/Admin/ListeArticle.fxml"));
                root = (VBox) makeResponsive(root, "vbox");
                frameLayout.getChildren().add(root);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    @FXML
    void navViewAllTestReportBtnClick(ActionEvent event) {
        if (!guiButtonCurrent.equals(navViewAllTestReportBtn)) {
            guiButtonCurrent = navViewAllTestReportBtn;
            guiChangeButtonStyle();
            guiButtonPrevious = navViewAllTestReportBtn;
            try {
                frameLayout.getChildren().clear();
                VBox root = FXMLLoader.load(getClass().getResource("test_report_history/view_test_report_history.fxml"));
                root = (VBox) makeResponsive(root, "vbox");
                frameLayout.getChildren().add(root);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }






    @FXML
    void onSettingsClick(ActionEvent event) {

    }

    @FXML
    void onReportBugClick(ActionEvent event) {

    }

    @FXML
    void onSupportClick(ActionEvent event) {

    }

    @FXML
    void onLogOutClick(ActionEvent event) {
        Stage stage = (Stage) guiButtonCurrent.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Esprit/MainGui/Admin/ListeArticle.fxml"));
            Parent parent = loader.load();
            Scene loginScene = new Scene(parent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void guiChangeButtonStyle() {
        guiButtonCurrent.getStyleClass().clear();
        guiButtonCurrent.getStyleClass().add("nav-button-selected");

        guiButtonPrevious.getStyleClass().clear();
        guiButtonPrevious.getStyleClass().add("nav-button-unselected");
    }

    public void onLogOutClick(javafx.event.ActionEvent actionEvent) {
        Stage stage = (Stage) guiButtonCurrent.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Esprit/MainGui/mainback/main.fxml"));
            Parent parent = loader.load();
            Scene loginScene = new Scene(parent);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onLogOutClick1(javafx.event.ActionEvent actionEvent) {
        Stage stage = (Stage) guiButtonCurrent.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Esprit/MainGui/Admin/Ajout.fxml"));
            Parent parent = loader.load();
            Scene loginScene = new Scene(parent);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onAppointmentsClick(javafx.event.ActionEvent actionEvent) {

        if (!guiButtonCurrent.equals(navAppointmentsBtn)) {
            guiButtonCurrent = navAppointmentsBtn;
            guiChangeButtonStyle();
            guiButtonPrevious = navAppointmentsBtn;
            try {
                frameLayout.getChildren().clear();
                VBox root = FXMLLoader.load(getClass().getResource("/Esprit/MainGui/Admin/ListeArticle.fxml"));
                root = (VBox) makeResponsive(root, "vbox");
                frameLayout.getChildren().add(root);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}

