import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Trajet {
    private int id;
    private String villedepart;
    private String villearrive;
    private Date date;
    private double prix;
    private String description;

    public Trajet() {}

    public void setDate(Date date) {this.date = date;}

    public Date getDate() {return date;}

    public  void setvillearrive(String villearrive) {this.villearrive=villearrive;}
    public String getvillearrive() {return villearrive;}


    public void setVilledepart(String villedepart) {
        this.villedepart = villedepart;
    }

    public String getVilledepart() {
        return villedepart;
    }




    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getPrix() {
        return prix;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static void ajouterTrajet(Connection connexion, Trajet trajet) throws SQLException {
        String query = "INSERT INTO trajets (villedepart, villearrive, date, prix, description) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connexion.prepareStatement(query)) {
            // Set parameters
            statement.setString(1, trajet.getVilledepart());
            statement.setString(2, trajet.getvillearrive());
            statement.setDate(3, new java.sql.Date(trajet.getDate().getTime())); // Convertir la Date en java.sql.Date
            statement.setDouble(4, trajet.getPrix());
            statement.setString(5, trajet.getDescription());
            // Execute query
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Trajet inséré avec succès !");
            }

    }
}}

