import Esprit.Dao.Classes.ArticleService;
import Esprit.Entities.Article;

import java.sql.Date;
import java.sql.SQLException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {
        // Press Alt+Entrée with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");


        Article articleo = new Article(34, "Nouveau titre", new Date(System.currentTimeMillis()), "Nouvelle description", "nouveau fichier", 10, 5, 20);
        ArticleService article = new ArticleService();

        //Créer un nouvel objet Article à mettre à jour

        //Appeler la méthode update() pour mettre à jour l'article
        article.update(articleo);

    }


/*
        ArticleService articleService = new ArticleService() ;
        Date d = new Date(2022-03-21) ;
        Article a1 = new Article() ;
        a1.setTitre("ommmarr");
        a1.setArticle_desc("omarr");
        a1.setArticle_date(d);
        a1.setFile("ajoudesktop");
        a1.setNbcomment(2);
        a1.setNblike(3);
        a1.setNbdislike(4);
        articleService.insertArticle(a1);
        System.out.printf("ajouter avec succes");

   System.out.println(articleService.DisplayAll());
        System.out.println( articleService.findDepotById(33) ) ;
        articleService.delete(33);


    }

/*
    Article article = new Article(); // create an instance of the class that contains the delete method
    int idToDelete = 33; // set the id of the article to be deleted

    article.delete(idToDelete); // call the delete method with the id of the article to be deleted*/
}