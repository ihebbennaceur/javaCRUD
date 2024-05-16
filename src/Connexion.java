import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

    // xamp
    private static final String HOST = "localhost";
    private static final String DB = "ihebbase";
    private static final String LOGIN = "root";
    private static final String MDP = "";

    // methode de co
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + DB, LOGIN, MDP);
    }
}
//pour ajouter C:\Users\ihebb\IdeaProjects\projetjava\mysql-connector-j-8.4.0.jar
//aller modules apres dependecies apres ajoute le path de ficher jar