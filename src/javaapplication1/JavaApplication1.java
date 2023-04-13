/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

//import entities.categorie;
//import entities.produit;
import java.util.List;
import entities.commande;
import entities.produit;
import services.ProduitCrud;
import utils.MyConnection;
//import services.CategorieCrud;
//import services.ProduitCrud;

import services.CommandeCrud;

/**
 *
 * @author rouja
 */
public class JavaApplication1 {

   
    public static void main(String[] args) {
       MyConnection cnx = MyConnection.getInstance();
    ProduitCrud c = new ProduitCrud();
    //*List<produit> produits = c.afficherProduit();
  // for (produit p : produits) {
      // System.out.println(p.toString());
   // }
       //produit p = new produit(124343,"stethoscope",455,"khg");
       
        
      //  produit p2 = new produit(105,"gant",455,"kj",45);
        
      CommandeCrud p = new CommandeCrud() ;
          //categorie p = new categorie("rde","erd");
           //CategorieCrud c = new CategorieCrud() ;
        
   
        
        
       
       //c.ajouterProduit(p);
         //System.out.println(c.afficherProduit());
          //c.modifierProduit(
          
         //p.modifierCommande(65,480,750);
              // c.ajouterCategorie(p);
            //c.ajouterCommande(p);
              // System.out.println(c.afficherCategorie());
              System.out.println(p.afficherCommande());
           
        //  c.supprimerCategorie(11);
      // c.supprimerProduit(p);
       //p.supprimerCommande(63);
    }
    
}
