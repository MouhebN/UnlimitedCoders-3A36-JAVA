/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Calendrier;
import entities.RendezVous;
import entities.Utilisateur;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static services.RendezVousCrud.cnx2;

/**
 *
 * @author L390
 */
public class CalendrierCrud {
     public void ajouterDispo(Calendrier c)  {
      try{
        String requete2 = "INSERT INTO calendrier (utilisateur_id,heure_debut,heure_fin)"
                + " VALUES (?, ?, ?)";
        PreparedStatement statement = cnx2.prepareStatement(requete2); 
         statement.setInt(1, c.getMedecin().getId());
        statement.setTimestamp(2, Timestamp.valueOf(c.getHeure_debut()));
        statement.setTimestamp(3, Timestamp.valueOf(c.getHeure_fin()));

  
        statement.executeUpdate();
        System.out.println(" YAAY Disponibilité ajoutée");

        
      }catch (SQLException ex) {
               System.out.println(ex.getMessage());
        
     }
            
       }
      public List<Calendrier> afficherDispo(int utilisateurId){ 
         List<Calendrier> myList = new ArrayList<>();
         try{ 
         
         String requete3 = "SELECT * FROM calendrier WHERE utilisateur_id=?";
         
        PreparedStatement statement = cnx2.prepareStatement(requete3);
        statement.setInt(1, utilisateurId);
         
         ResultSet rs =  statement.executeQuery();
         while (rs.next()){
             Calendrier cal = new Calendrier(); 
             cal.setId(rs.getInt(1));
             cal.setHeure_debut(rs.getTimestamp("heure_debut").toLocalDateTime());
             cal.setHeure_fin(rs.getTimestamp("heure_fin").toLocalDateTime());
             Utilisateur medecin = new Utilisateur(rs.getInt("utilisateur_id"));
             cal.setMedecin(medecin);
             myList.add(cal);
         }
         }catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }
         return myList ;
     
          
   
}
       public void supprimerDispo(int id) { 
    try {
        String requete = "DELETE FROM calendrier WHERE id=?";
        PreparedStatement pst = cnx2.prepareStatement(requete);
        pst.setInt(1, id);
        int resultat = pst.executeUpdate();
        if (resultat == 1) {
            System.out.println("Disponibilité supprimé avec succès");
        } else {
            System.out.println("Impossible de supprimer la disponibilité");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
       }
    public void modifierDispo(int id, LocalDateTime heureDebut, LocalDateTime heureFin) {
    try {
        String requete = "UPDATE calendrier SET heure_debut=?, heure_fin=? WHERE id=?";
        PreparedStatement pst = cnx2.prepareStatement(requete);
        pst.setTimestamp(1, Timestamp.valueOf(heureDebut));
        pst.setTimestamp(2, Timestamp.valueOf(heureFin));
        pst.setInt(3, id);
        int resultat = pst.executeUpdate();
        if (resultat == 1) {
            System.out.println("Disponibilité modifiée avec succès");
        } else {
            System.out.println("Impossible de modifier la disponibilité");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


}
