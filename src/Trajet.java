import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}


