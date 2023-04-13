package Esprit.Dao.Interfaces;

import Esprit.Entities.Article;

import java.sql.SQLException;
import java.util.List;

public interface ArticleInt {
    void insertArticle(Article article);


    void delete(int id);

    Article findDepotById(int id);


    List<Article> DisplayAll() throws SQLException;
}
