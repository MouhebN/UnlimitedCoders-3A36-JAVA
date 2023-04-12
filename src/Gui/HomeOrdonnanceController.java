/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui;
import Entity.Consultation;
import Entity.Medicament;
import Entity.Ordonnance;
import Services.ServiceConsultation;
import Services.ServiceMedicament;
import Services.ServiceOrdonnance;
import Services.ServicePDF;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/**
 *
 * @author bytesudoer
 */
public class HomeOrdonnanceController implements Initializable{

    @FXML 
    private Button btnRetour;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnPdf;

    //interface Afficher
    @FXML
    private TableView<Ordonnance> ordonnanceTable;
    @FXML
    private TableColumn<?,?> idColonne;
    @FXML
    private TableColumn<?,?> consultationColonne;
    @FXML
    private TableColumn<?,?> validiteColonne;
    @FXML
    private TableColumn<?,?> medicamentColonne;

    //interface Ajouter
    @FXML
    private ChoiceBox idConsultationText;
    @FXML
    private TextField validiteText;
    @FXML
    private ChoiceBox medicamentText;


    public HomeOrdonnanceController()
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
        System.out.println("Ajout Ordonnance Click");
        ServiceOrdonnance sv = new ServiceOrdonnance();
        int consultationId = (int) idConsultationText.getSelectionModel().getSelectedItem();
        int validite = Integer.parseInt(validiteText.getText());
        String nomMedicament = (String)medicamentText.getSelectionModel().getSelectedItem();
        if(this.validerIdConsultation(consultationId) && this.validerValidier(validite) && this.validerNomMedicament(nomMedicament))
        {
            Ordonnance o = new Ordonnance();
            o.setConsultation_id(consultationId);
            o.setValidite(validite);
            sv.ajouterOrdonnance(o);
            this.notifier("Ajout");
            afficherOrdonnance();
            
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
    public void btnSupprimer(ActionEvent evt)
    {
        System.out.println("Supprimer Ordonnance Click");
        ServiceOrdonnance sv = new ServiceOrdonnance();
        
        Ordonnance o = (Ordonnance) ordonnanceTable.getSelectionModel().getSelectedItem();
        sv.supprimerOrdonnance(o.getId());
        this.notifier("Suppression");
        afficherOrdonnance();
        
        
    }
    @FXML
    public void btnModifier(ActionEvent evt)
    {
        System.out.println("Modifer Ordonnance Click");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifierOrdonnance.fxml"));
        try{
            loader.load();
            Ordonnance o = (Ordonnance) ordonnanceTable.getSelectionModel().getSelectedItem();
            ModifierOrdonnanceController mc = loader.getController();
            mc.setTextFields(o);
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
    private void btnPdf(ActionEvent evt) throws FileNotFoundException, DocumentException
    {
        System.out.println("Boutton PDF Click");
        ServiceOrdonnance sv = new ServiceOrdonnance();
        ServicePDF pdf = new ServicePDF();
        List<Ordonnance> listeOrdonnances = sv.afficherOrdonnance();
        pdf.genererPdfOrdonnance("Ordonnance", listeOrdonnances);
    }
    public void show()
    {
        try{
            System.out.println("Boutton Retour Click");
            Parent loader = FXMLLoader.load(getClass().getResource("HomeOrdonnance.fxml"));
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

    public ObservableList<Ordonnance> getOrdonnances()
    {
        ObservableList<Ordonnance> observableListOrdonnance = FXCollections.observableArrayList();
        ServiceOrdonnance sv = new ServiceOrdonnance();
        List<Ordonnance> listeOrdonnances = sv.afficherOrdonnance();
        for(Ordonnance ordonnance: listeOrdonnances)
        {
            //System.out.println("ordonnance Affichage : "+ordonnance);
            observableListOrdonnance.add(ordonnance);
        }
        return observableListOrdonnance;
    }

    public void afficherOrdonnance()
    {
        ObservableList<Ordonnance> list = getOrdonnances();
        idColonne.setCellValueFactory(new PropertyValueFactory<>("id"));
        consultationColonne.setCellValueFactory(new PropertyValueFactory<>("consultation_id"));
        validiteColonne.setCellValueFactory(new PropertyValueFactory<>("validite"));
        medicamentColonne.setCellValueFactory(new PropertyValueFactory<>("nomMedicaments"));
        
        ordonnanceTable.setItems(list);
    }
    public void ajouterIdConsultationChoiceBox()
    {
        ObservableList<Integer> observableListIdConsultation = FXCollections.observableArrayList();
        ServiceConsultation sv = new ServiceConsultation();
        List<Consultation> listeConsultation = sv.afficherConsultation();
        for(Consultation consultation:listeConsultation)
        {
            //System.out.println("Consultation : "+consultation);
            observableListIdConsultation.add(consultation.getId());
        }
        idConsultationText.setItems(observableListIdConsultation);
    }
    public void ajouterNomMedicamentsChoiceBox()
    {
        ObservableList<String> observableListNomMedicaments = FXCollections.observableArrayList();
        ServiceMedicament sv = new ServiceMedicament();
        List<Medicament> listeMedicaments = sv.afficherMedicament();
        for(Medicament medicament: listeMedicaments)
        {
            observableListNomMedicaments.add(medicament.getNom());
        }
        medicamentText.setItems(observableListNomMedicaments);
    }
    public boolean validerIdConsultation(int id)
    {
        if(id==0)
        {
            this.notifierError("Le champ 'Identifiant Consultation' est un champ obligatoire");
            return false;
        }
        return true;
    }
    public boolean validerValidier(int validite)
    {
        if(validite < 0 )
        {
            this.notifierError("Le champ 'Validite' doit etre un entier positif");
            return false;
        }
        return true;
    }
    public boolean validerNomMedicament(String nomMedicament)
    {
        if(nomMedicament.isEmpty())
        {
            this.notifierError("Le champ 'Nom Medicament' est un champ obligatoire");
            return false;
        }
        return true;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ajouterIdConsultationChoiceBox();
        ajouterNomMedicamentsChoiceBox();
        afficherOrdonnance();
    }
    
}
