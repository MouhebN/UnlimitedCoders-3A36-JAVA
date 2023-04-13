package Esprit.Dao.Classes;

import Esprit.Dao.Interfaces.CommentaireInt;
import Esprit.Entities.Commentaire;
import Esprit.utils.MyConnexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommentaireService implements CommentaireInt {
    @Override
    public void insert(Commentaire commentaire) {
        String requete = "insert into commentaire (commentairecontenu,commentairedate,article_id) values (?,?,?)";
        try {
            PreparedStatement ps = (PreparedStatement) MyConnexion.getInstance().getCnx().prepareStatement(requete);
            ps.setString(1,commentaire.getCommentairecontenu());
            ps.setDate(2,commentaire.getCommentairedate());
            ps.setInt(3 , commentaire.getArticle_id());



            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static List<Commentaire> getCommentairesForArticle(int articleId) throws SQLException {

        List<Commentaire> commentaires = new ArrayList<>();
        MyConnexion conn = MyConnexion.getInstance();


        // Retrieve the comments for the given article ID from the database
        // This is just an example - the exact implementation will depend on how you're storing the comments
        String sql = "SELECT * FROM commentaire WHERE article_id = ?";
        PreparedStatement statement = (PreparedStatement) MyConnexion.getInstance().getCnx().prepareStatement(sql);
        ;
        statement.setInt(1, articleId);
        ResultSet resultSet = statement.executeQuery();

        // Create Commentaire objects from the result set and add them to the list
        while (resultSet.next()) {
            Commentaire commentaire = new Commentaire(
                    resultSet.getInt("id"),
                    resultSet.getString("commentairecontenu"),
                    resultSet.getDate("commentairedate"),
                    resultSet.getInt("article_id")
            );
            commentaires.add(commentaire);
        }

        return commentaires;
    }

    @Override
    public boolean update(Commentaire commentaire) {
        return false;
    }

    @Override
    public void delete(int id) {
        String requete = "delete from commentaire where Id=?";
        try {
            PreparedStatement ps = (PreparedStatement) MyConnexion.getInstance().getCnx().prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("commentaire supprimée");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public Commentaire findDepotById(int id) {
        String req="select * from commentaire where id ="+id;
        Commentaire commentaire = new Commentaire();
        try {
            PreparedStatement pst = (PreparedStatement) MyConnexion.getInstance().getCnx().prepareStatement(req);

            ResultSet resultat = pst.executeQuery(req);
            // while(rs.next()){
            resultat.next();



            commentaire.setCommentairecontenu(resultat.getString(2));
            commentaire.setCommentairedate(resultat.getDate(3));
            commentaire.setArticle_id(resultat.getInt(4));  //}
        } catch (SQLException ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return commentaire;

    }

    @Override
    public List<Commentaire> DisplayAll() throws SQLException {
        List<Commentaire> listecommentaire = new ArrayList<Commentaire>();
        MyConnexion m = MyConnexion.getInstance();

        String requete = "select * from commentaire";
        Statement pst = MyConnexion.getCnx().createStatement();
        ResultSet resultat = pst.executeQuery(requete);




        while (resultat.next()) {
            Commentaire commentaire = new Commentaire();


     commentaire.setCommentairecontenu(resultat.getString(2));
     commentaire.setCommentairedate(resultat.getDate(3));
     commentaire.setArticle_id(resultat.getInt(4));




            listecommentaire.add(commentaire);
        }
        return listecommentaire;
    }
    private static CommentaireInt intarticle;

    public static CommentaireInt getInstance() {
        if (intarticle == null) {
            intarticle = (CommentaireService) new CommentaireService();
        }
        return intarticle;
    }

    public CommentaireService() {
    }
}
