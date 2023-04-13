package Esprit.Dao.Interfaces;

import Esprit.Entities.Article;
import Esprit.Entities.Commentaire;

import java.sql.SQLException;
import java.util.List;

public interface CommentaireInt {
    void insert(Commentaire commentaire);

    static List<Commentaire> getCommentairesForArticle(int articleId) throws SQLException {
        return null;
    }

    boolean update(Commentaire commentaire);

    void delete(int id);

    Commentaire findDepotById(int id);


    List<Commentaire> DisplayAll() throws SQLException;
}
