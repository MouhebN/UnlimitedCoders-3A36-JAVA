/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.commande;
import interfaces.InterfaceCommande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;

/**
 *
 * @author rouja
 */
public class CommandeCrud implements InterfaceCommande {

    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();

    @Override
    public void ajouterCommande(commande p) {
        try {
            String req = "INSERT INTO `commande`(prix, quantite) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setDouble(1, p.getPrix());
            ps.setInt(2, p.getQuantite());
            ps.executeUpdate();
            System.out.println("commande  ajouté !!");
            System.out.println(p);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierCommande(int id, int prix, int quantite) {
    try {
        String requete = "UPDATE commande SET prix=?, quantite=? WHERE id=?";
        java.sql.PreparedStatement ps = conn.prepareStatement(requete);
        ps.setInt(1, prix);
        ps.setInt(2, quantite);
        ps.setInt(3, id); // ajouter cette ligne pour spécifier la valeur de l'identifiant

        int resultat = ps.executeUpdate();
        if (resultat == 1) {
            System.out.println("Commande modifiée avec succès");
        } else {
            System.out.println("Impossible de modifier la commande");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


    @Override
    public void supprimerCommande(int id) {
        try {
            String req = "DELETE FROM commande WHERE id=?";
            java.sql.PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, id);
            int resultat = ps.executeUpdate();
            if (resultat == 1) {
                System.out.println("commande supprimé avec succès");
            } else {
                System.out.println("Impossible de supprimer le commande");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<commande> afficherCommande() {
        ObservableList<commande> list = FXCollections.observableArrayList();
        try {

            String req = "SELECT * FROM `commande`";
            Statement st = conn.createStatement();
            ResultSet Rs = st.executeQuery(req);
            while (Rs.next()) {
                commande p = new commande();
                p.setId(Rs.getInt(1));
                p.setPrix(Rs.getDouble(2));

                p.setQuantite(Rs.getInt(3));

                list.add(p);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
      return list ;
    }
}
