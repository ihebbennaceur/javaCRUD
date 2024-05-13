import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class User {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String password;


    //constructeur
    User() {
    }

    public void setid(int id) {
        this.id = id;
    }

    public void setname(String name) {
        this.name = name;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public void setphone(String phone) {
        this.phone = phone;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public void afficherdata() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Password: " + password);
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getPassword() {
        return this.password;
    }

    public void ajouterUtilisateur(Connection connexion) throws SQLException {

        try {
            String query = "INSERT INTO projetjava (id,name, email, phone, password) VALUES (default,?, ?, ?, ?)";
            try (PreparedStatement statement = connexion.prepareStatement(query)) {
                // definir parms de la requet
                statement.setString(1, name);
                statement.setString(2, email);
                statement.setString(3, phone);
                statement.setString(4, password);
                // executer la requête sql
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Utilisateur inséré avec succès !");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'insertion de l'utilisateur dans la base de données : " + e.getMessage());
        }

    }

    //vb1 c le nom de la column
    //vb2 c le nom de la table
    public void selectiondb(Connection connexion, String vb1, String vb2, String email, String password) {
        try {
            String requetsql = "SELECT " + vb1 + " FROM " + vb2 + " WHERE email = ? AND password = ?";
            try (PreparedStatement statement = connexion.prepareStatement(requetsql)) {
                statement.setString(1, email);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // L'utilisateur a été trouvé dans la base de données
                        this.email = resultSet.getString("email");
                        this.password = resultSet.getString("password");
                    } else {
                        // L'utilisateur n'a pas été trouvé dans la base de données
                        this.email = null;
                        this.password = null;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la sélection de l'utilisateur dans la base de données : " + e.getMessage());
        }
    }
}





