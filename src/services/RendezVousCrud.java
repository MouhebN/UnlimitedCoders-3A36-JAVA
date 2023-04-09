/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.RendezVous;
import entities.Utilisateur;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Connexion;

/**
 *
 * @author L390
 */
public class RendezVousCrud {

    public static Connection cnx2;

    public RendezVousCrud() {
        cnx2 = Connexion.getInstance().getCnx();
    }

    public void ajouterRdv() {
        try {
            String requete = "INSERT INTO rendez_vous (date, utilisateur_id, patient_id, etat, description) VALUES ('2023-03-11 08:30:00', '6',' 8',' urgent', 'java') ";
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);

            System.out.println(" YAAY RendezVous ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ajouterRdv2(RendezVous r) {
        try {
            String requete2 = "INSERT INTO rendez_vous (date,utilisateur_id, patient_id,etat, description)"
                    + " VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = cnx2.prepareStatement(requete2);
            statement.setTimestamp(1, new java.sql.Timestamp(r.getDate().getTime()));
            statement.setInt(2, r.getMedecin().getId());
            System.out.println(r.getMedecin().getId());
            statement.setInt(3, r.getPatient().getId());
            statement.setString(4, r.getEtat());
            statement.setString(5, r.getDescription());
            statement.executeUpdate();
            System.out.println(" YAAY RendezVous2 ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<RendezVous> afficherRendezvouses() {
        List<RendezVous> myList = new ArrayList<>();
        try {

            String requete3 = "SELECT * FROM rendez_vous  ";
            Statement st = cnx2.createStatement();

            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                RendezVous rdv = new RendezVous();
                rdv.setId(rs.getInt(1));
                rdv.setDate(rs.getTimestamp("date"));
                rdv.setDescription(rs.getString("Description"));
                rdv.setEtat(rs.getString("Etat"));
                Utilisateur medecin = new Utilisateur(rs.getInt("utilisateur_id"));
                rdv.setMedecin(medecin);
                Utilisateur patient = new Utilisateur(rs.getInt("patient_id"));
                rdv.setPatient(patient);
                myList.add(rdv);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;

    }

    public void supprimerRdv(int id) {
        try {
            String requete = "DELETE FROM rendez_vous WHERE id=?";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setInt(1, id);
            int resultat = pst.executeUpdate();
            if (resultat == 1) {
                System.out.println("Rendez-vous supprimé avec succès");
            } else {
                System.out.println("Impossible de supprimer le rendez-vous");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierRdv(int id, Date date, Utilisateur medecin, Utilisateur patient, String etat, String description) {
        try {
            String requete = "UPDATE rendez_vous SET date=?, utilisateur_id=?, patient_id=?, etat=?, description=? WHERE id=?";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setDate(1, new java.sql.Date(date.getTime()));
            pst.setInt(2, medecin.getId());
            pst.setInt(3, patient.getId());
            pst.setString(4, etat);
            pst.setString(5, description);
            pst.setInt(6, id);
            int resultat = pst.executeUpdate();
            if (resultat == 1) {
                System.out.println("Rendez-vous modifié avec succès");
            } else {
                System.out.println("Impossible de modifier le rendez-vous");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void modifierRdv2(int id, Timestamp date, Utilisateur patient, String etat, String description) {
    try {
        String requete = "UPDATE rendez_vous SET date=?, patient_id=?, etat=?, description=? WHERE id=?";
        PreparedStatement pst = cnx2.prepareStatement(requete);
        pst.setTimestamp(1, date);
        pst.setInt(2, patient.getId());
        pst.setString(3, etat);
        pst.setString(4, description);
        pst.setInt(5, id);
        int resultat = pst.executeUpdate();
        if (resultat == 1) {
            System.out.println("Rendez-vous modifié avec succès");
        } else {
            System.out.println("Impossible de modifier le rendez-vous");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    public List<RendezVous> getRendezVousByUtilisateurId(int utilisateurId) {
    List<RendezVous> myList = new ArrayList<>();
    try {
        String requete = "SELECT * FROM rendez_vous WHERE patient_id = ?";
        PreparedStatement pst = cnx2.prepareStatement(requete);
        pst.setInt(1, utilisateurId);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            RendezVous rdv = new RendezVous();
            rdv.setId(rs.getInt(1));
            rdv.setDate(rs.getTimestamp("date"));
            rdv.setDescription(rs.getString("Description"));
            rdv.setEtat(rs.getString("Etat"));
            Utilisateur medecin = new Utilisateur(rs.getInt("utilisateur_id"));
            rdv.setMedecin(medecin);
            Utilisateur patient = new Utilisateur(rs.getInt("patient_id"));
            rdv.setPatient(patient);
            myList.add(rdv);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return myList;
}
public boolean isRdvAlreadyReserved(int medecinId, Timestamp dateTime) {
    try {
        String requete = "SELECT * FROM rendez_vous WHERE utilisateur_id=? AND date=?";
        PreparedStatement statement = cnx2.prepareStatement(requete);
        statement.setInt(1, medecinId);
        statement.setTimestamp(2, dateTime);
        ResultSet rs = statement.executeQuery();
        return rs.next();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());;
        return false;
    }
}


}
