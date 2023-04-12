/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Connexion;

/**
 *
 * @author L390
 */
public class UtilisateurCrud {

    public static Connection cnx2;

    public UtilisateurCrud() {
        cnx2 = Connexion.getInstance().getCnx();
    }

    public ObservableList<Utilisateur> getAllMedecins() throws SQLException {
        ObservableList<Utilisateur> medecins = FXCollections.observableArrayList();
        String query = "SELECT * FROM Utilisateur WHERE role = 'medecin'";
        try (PreparedStatement statement = cnx2.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Utilisateur medecin = new Utilisateur();
                medecin.setId(rs.getInt("id"));
                medecin.setNom(rs.getString("nom"));
                medecin.setPrenom(rs.getString("prenom"));

                medecins.add(medecin);
            }
        }
        return medecins;
    }

    public Utilisateur getPatientById(int id) throws SQLException {
        String query = "SELECT * FROM Utilisateur WHERE id = ? AND role = 'patient'";
        try (PreparedStatement statement = cnx2.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Utilisateur patient = new Utilisateur();
                patient.setId(rs.getInt("id"));
                patient.setNom(rs.getString("nom"));
                patient.setPrenom(rs.getString("prenom"));
                // set other patient properties here
                return patient;
            }
        }
        return null;
    }

    public Utilisateur getMedecinById(int id) throws SQLException {
        String query = "SELECT * FROM Utilisateur WHERE id = ? AND role = 'medecin'";
        try (PreparedStatement statement = cnx2.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Utilisateur medecin = new Utilisateur();
                medecin.setId(rs.getInt("id"));
                medecin.setNom(rs.getString("nom"));
                medecin.setPrenom(rs.getString("prenom"));           
                return medecin;
            }
        }
        return null;
    }
}
