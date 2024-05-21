import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.util.Date;
import javax.swing.JFrame;


public class Trajet {
    private int id;
    private String villedepart;
    private String villearrive;
    private String date;
    private String prix;
    private String description;
    private String heure;
    private int userid;

    public Trajet() {}

    public void setDate(String date) {this.date = date;}

    public String getdate() {return date;}

    public String getDate() {
        return date;
    }
    public int getUserid(){
        User utilisateur =new User();
        return this.userid=getUserid();}

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public  void setvillearrive(String villearrive) {this.villearrive=villearrive;}
    public String getvillearrive() {return villearrive;}


    public void setVilledepart(String villedepart) {
        this.villedepart = villedepart;
    }

    public String getVilledepart() {
        return villedepart;
    }




    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getPrix() {
        return prix;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    //User cinuser = new User();


    public static void ajouterTrajet(Connection connexion, Trajet trajet, int userId) throws SQLException {
        String query = "INSERT INTO trajets (villedepart, villearrivee, date, prix, description, userid) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connexion.prepareStatement(query)) {
            // set parameters
            statement.setString(1, trajet.getVilledepart());
            statement.setString(2, trajet.getvillearrive());
            statement.setString(3, trajet.getdate());
            statement.setString(4, trajet.getPrix());
            statement.setString(5, trajet.getDescription());
            statement.setInt(6, userId);

            // Execute query
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Trajet inséré avec succès !");
                JOptionPane.showMessageDialog(null, "Trajet inseré avec succès");
            }
        }
    }

    void setId(int id) {this.id=id;}
    public int getId() {return id;}

    public static List<Trajet> getTrajetsByUserId(Connection connexion, int userId) throws SQLException {
        List<Trajet> trajets = new ArrayList<>();
        String query = "SELECT * FROM trajets WHERE userid = ?";
        try (PreparedStatement statement = connexion.prepareStatement(query)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Trajet trajet = new Trajet();
                    trajet.setId(resultSet.getInt("id"));
                    trajet.setVilledepart(resultSet.getString("villedepart"));
                    trajet.setvillearrive(resultSet.getString("villearrivee"));
                    trajet.setDate(resultSet.getString("date"));
                    trajet.setPrix(resultSet.getString("prix"));
                    trajet.setDescription(resultSet.getString("description"));
                    trajet.setUserid(resultSet.getInt("userid"));
                    trajets.add(trajet);
                }
            }
        }
        return trajets;
    }
    public static List<Trajet> rechercherTrajets(Connection connexion, String ville) throws SQLException {
        List<Trajet> trajets = new ArrayList<>();
        //vb trajets contient des elemet de typ trajet

        String query = "SELECT * FROM trajets WHERE villedepart LIKE ? OR villearrivee LIKE ?";
        try (PreparedStatement statement = connexion.prepareStatement(query)) {
            statement.setString(1, "%" + ville + "%");
            statement.setString(2, "%" + ville + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Trajet trajet = new Trajet();
                    trajet.setId(resultSet.getInt("id"));
                    trajet.setVilledepart(resultSet.getString("villedepart"));
                    trajet.setvillearrive(resultSet.getString("villearrivee"));
                    trajet.setDate(resultSet.getString("date"));
                    trajet.setPrix(resultSet.getString("prix"));
                    trajet.setDescription(resultSet.getString("description"));
                    trajet.setUserid(resultSet.getInt("userid"));
                    trajets.add(trajet);
                }
            }
        }
        return trajets;
    }

    public static Trajet getTrajetById(Connection connexion, int trajetId) throws SQLException {
        Trajet trajet = null;
        String query = "SELECT * FROM trajets WHERE id = ?";
        try (PreparedStatement statement = connexion.prepareStatement(query)) {
            statement.setInt(1, trajetId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    trajet = new Trajet();
                    trajet.setId(resultSet.getInt("id"));
                    trajet.setVilledepart(resultSet.getString("villedepart"));
                    trajet.setvillearrive(resultSet.getString("villearrivee"));
                    trajet.setDate(resultSet.getString("date"));
                    trajet.setPrix(resultSet.getString("prix"));
                    trajet.setDescription(resultSet.getString("description"));
                    trajet.setUserid(resultSet.getInt("userid"));
                }
            }
        }
        return trajet;
    }

    public static void modifierTrajet(Connection connexion, Trajet trajet) throws SQLException {
        String query = "UPDATE trajets SET villedepart = ?, villearrivee = ?, date = ?, prix = ?, description = ? WHERE id = ?";
        try (PreparedStatement statement = connexion.prepareStatement(query)) {
            statement.setString(1, trajet.getVilledepart());
            statement.setString(2, trajet.getvillearrive());
            statement.setString(3, trajet.getDate());
            statement.setString(4, trajet.getPrix());
            statement.setString(5, trajet.getDescription());
            statement.setInt(6, trajet.getId());

            statement.executeUpdate();
        }
    }

    public static void supprimerTrajet(Connection connexion, int trajetId) throws SQLException {
        String query = "DELETE FROM trajets WHERE id = ?";
        try (PreparedStatement statement = connexion.prepareStatement(query)) {
            statement.setInt(1, trajetId);
            statement.executeUpdate();
        }
    }
}





