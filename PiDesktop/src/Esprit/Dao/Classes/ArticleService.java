package Esprit.Dao.Classes;

import Esprit.Dao.Interfaces.ArticleInt;
import Esprit.Entities.Article;
import Esprit.utils.MyConnexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArticleService implements ArticleInt {
    public ArticleService() {
    }

    @Override
    public void insertArticle(Article article) {
        String requete = "insert into article (titre,article_desc,article_date,nbcomment,nblike,nbdislike,file) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = (PreparedStatement) MyConnexion.getInstance().getCnx().prepareStatement(requete);

            ps.setString(1,article.getTitre());
            ps.setString(2, article.getArticle_desc());
            ps.setDate(3,article.getArticle_date());
            ps.setInt(4,article.getNbcomment());
            ps.setInt(5,article.getNblike());
            ps.setInt(6,article.getNbdislike());
            ps.setString(7, article.getFile());


            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }




    public void update (Article article ) {
        String requete = "UPDATE article SET titre=?, article_date=?, article_desc=?, file=?, nblike=?, nbdislike=?, nbcomment=? WHERE id=?";
        try {
            PreparedStatement ps = MyConnexion.getInstance().getCnx().prepareStatement(requete);
            ps.setString(1, article.getTitre());
            ps.setDate(2, article.getArticle_date());
            ps.setString(3, article.getArticle_desc());
            ps.setString(4, article.getFile());
            ps.setInt(5, article.getNblike());
            ps.setInt(6, article.getNbdislike());
            ps.setInt(7, article.getNbcomment());
            ps.setInt(8, article.getId());
            ps.executeUpdate();
            System.out.println("Article updated successfully");
        } catch (SQLException ex) {
            System.out.println("Error updating article: " + ex.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String requete = "delete from article where Id=?";
        try {
            PreparedStatement ps = (PreparedStatement) MyConnexion.getInstance().getCnx().prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("article supprimée");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public Article findDepotById(int id) {
        String req="select * from article where id ="+id;
        Article article=new Article();
        try {
            PreparedStatement pst = (PreparedStatement) MyConnexion.getInstance().getCnx().prepareStatement(req);

            ResultSet resultat = pst.executeQuery(req);
            // while(rs.next()){
            resultat.next();
            article.setTitre(resultat.getString(2));
            article.setArticle_desc(resultat.getString(3));
            article.setArticle_date(resultat.getDate(4));
            article.setNbcomment(resultat.getInt(5));
            article.setNblike(resultat.getInt(6));
            article.setNbdislike(resultat.getInt(7));
            article.setFile(resultat.getString(8));
            //}
        } catch (SQLException ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return article;
    }

    @Override
    public List<Article> DisplayAll() throws SQLException {
        List<Article> listearticle = new ArrayList<Article>();
        MyConnexion m = MyConnexion.getInstance();

        String requete = "select * from article";
        Statement pst = MyConnexion.getCnx().createStatement();
        ResultSet resultat = pst.executeQuery(requete);




        while (resultat.next()) {
            Article article = new Article();


            article.setId(resultat.getInt(1));
            article.setTitre(resultat.getString(2));
            article.setArticle_desc(resultat.getString(3));
            article.setArticle_date(resultat.getDate(4));
            article.setNbcomment(resultat.getInt(5));
            article.setNblike(resultat.getInt(6));
            article.setNbdislike(resultat.getInt(7));
            article.setFile(resultat.getString(8));



            listearticle.add(article);
        }
        return listearticle;
    }

    private static ArticleInt intarticle;

    public static ArticleInt getInstance() {
        if (intarticle == null) {
            intarticle = (ArticleService) new ArticleService();
        }
        return intarticle;
    }
}
