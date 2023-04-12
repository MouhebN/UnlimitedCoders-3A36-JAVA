/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui;
import Entity.Consultation;
import Services.ServiceConsultation;
import Services.ServicePDF;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 *
 * @author bytesudoer
 */
public class HomeConsultationController implements Initializable{

    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnRetour;
    @FXML
    private Button bntPdf;

    @FXML
    private TextField matriculeMedecinText;
    @FXML
    private TextField idPatientText;
    @FXML
    private DatePicker dateConsultationText;
    @FXML
    private TextField montantText;

    @FXML
    private TableView<Consultation> consultationTable;
    
    @FXML
    private TableColumn<?,?> idColonne;
    @FXML
    private TableColumn<?,?> matriculeColonne;
    @FXML
    private TableColumn<?,?> idPatientColonne;
    @FXML
    private TableColumn<?,?> dateConsultationColonne;
    @FXML
    private TableColumn<?,?> montantColonne;


    public HomeConsultationController()
    {
    }

    public void notifier(String operation)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Consultation");
        alert.setHeaderText(null);
        alert.setContentText("Operation "+operation+" Effectue avec success");
        alert.show();
    }
    public void notifierError(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Operation Refusee");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }

    @FXML
    public void btnAjouter(ActionEvent evt)
    {

        System.out.println("Ajout Consultation Click");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        ServiceConsultation sc = new ServiceConsultation();
        String matricule = matriculeMedecinText.getText();
        String idPatient = idPatientText.getText();
        Float montant = Float.parseFloat(montantText.getText());
        LocalDate dateConsultation = dateConsultationText.getValue();

        if(this.validerMatriculeMedecin(matricule) && this.validerIdPatient(idPatient) && this.validierMontant(montant))
        {
            Consultation c = new Consultation();
            c.setMatriculeMedecin(matricule);
            c.setIdPatient(idPatient);
            c.setMontant(montant);
            c.setDateConsultation(java.sql.Date.valueOf(dateConsultation));
            sc.ajouterConsultation(c);
            this.notifier("Ajout");
            afficherListeConsultation();
        }
    }
    @FXML
    public void btnSupprimer(ActionEvent evt)
    {
        System.out.println("Supprimer Consultation click");
        ServiceConsultation sv = new ServiceConsultation();
        
        Consultation c = (Consultation) consultationTable.getSelectionModel().getSelectedItem();
        sv.supprimerConsultation(c.getId());
        this.notifier("Suppression");
        afficherListeConsultation();
    }
    @FXML
    public void btnModifier(ActionEvent evt)
    {
        System.out.println("Modifier Consultation Click");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifierConsultation.fxml"));
        try{
            loader.load();
            Consultation c = (Consultation)consultationTable.getSelectionModel().getSelectedItem();
            ModifierConsultationController mc = loader.getController();
            mc.setTextFields(c);
            Parent root = loader.getRoot();
            Scene scene = new Scene(root);

            Stage stage = (Stage)btnRetour.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void btnRetour(ActionEvent evt)
    {
        System.out.println("Retour Click");
        Stage stage = (Stage) btnRetour.getScene().getWindow();
        stage.close();
        MenuController mc = new MenuController();
        mc.show();
    }
    @FXML
    public void btnPdf(ActionEvent evt) throws FileNotFoundException, DocumentException
    {
        System.out.println("Boutton PDF Click");
        Consultation c = (Consultation) consultationTable.getSelectionModel().getSelectedItem();
        ServiceConsultation sv = new ServiceConsultation();
        List<Consultation>listeConsultations = sv.afficherConsultation();
        ServicePDF pdf = new ServicePDF();
        pdf.genererPdfConsultation("Consultation", listeConsultations);
        this.notifier("Generation PDF");
        
    }

    public ObservableList<Consultation> getConsultations()
    {
        ObservableList<Consultation> observableListConsultation = FXCollections.observableArrayList();
        ServiceConsultation sv = new ServiceConsultation();
        List<Consultation> listeConsultation = sv.afficherConsultation();

        for (Consultation consultation : listeConsultation) {
            observableListConsultation.add(consultation);
        }
        return observableListConsultation;
    }

    public void afficherListeConsultation()
    {
        ObservableList<Consultation> list = getConsultations();
        //System.out.println(list);
        idColonne.setCellValueFactory(new PropertyValueFactory<>("id"));
        matriculeColonne.setCellValueFactory(new PropertyValueFactory<>("matriculeMedecin"));
        idPatientColonne.setCellValueFactory(new PropertyValueFactory<>("idPatient"));
        dateConsultationColonne.setCellValueFactory(new PropertyValueFactory<>("dateConsultation"));
        montantColonne.setCellValueFactory(new PropertyValueFactory<>("montant"));

        consultationTable.setItems(list);
    }
    public void show()
    {
        try
        {
            System.out.println("Boutton Retour click");
            Parent loader = FXMLLoader.load(getClass().getResource("HomeConsultation.fxml"));
            Scene scene = new Scene(loader);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    // Fonctions Controle Saisie
    public boolean validerMatriculeMedecin(String matricule)
    {
        if(matricule.length() < 5)
        {
            this.notifierError("Le champ 'Matricule Medecin' doit contenir au moins 5 caracteres");
            return false;
        }
        return true;
    }
    public boolean validerIdPatient(String idPatient)
    {
        if(idPatient.length() < 5)
        {
            this.notifierError("Le champ 'Identifiant Patient' doit contenir au moins 5 caracteres");
            return false;
        }
        return true;
    }
    public boolean validierMontant(float montant)
    {
        if(montant< 0)
        {
            this.notifierError("Le champ 'Montant' doit etre un entier positif");
            return false;
        }
        return true;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherListeConsultation();
    }

    
}
