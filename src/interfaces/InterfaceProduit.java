
package interfaces;

import entities.produit;
import java.util.List;
import javafx.collections.ObservableList;



/**
 *
 * @author roujas
 */
public interface InterfaceProduit {
    public void ajouterProduit(produit p );
     public void ajouterProduit2(produit p );
      public void modifierProduit(int Categorie_id, String nom, int matricule_asseu,int prix );
       public void supprimerProduit(produit p );
        public ObservableList<produit> afficherProduit();
}
