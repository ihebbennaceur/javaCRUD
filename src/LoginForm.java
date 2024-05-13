import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginForm extends JFrame {
    private JTextField inputusername;
    private JTextField inputpassword;
    private JButton loginButton;
    private JButton forgetMyPasswordButton;
    private JButton inscriptionButton;
    private JLabel usertitle;
    private JLabel pwdtitle;
    private JLabel logintitle;


        public LoginForm() {
            setTitle("Connexion");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(600, 500);
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            inputusername = new JTextField(20);
            inputpassword= new JTextField(20);
            loginButton = new JButton("Connexion");
            forgetMyPasswordButton = new JButton("Mot de passe oublié ?");
            inscriptionButton = new JButton("Inscription");

            // Username label and text field
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(new JLabel("Nom d'utilisateur:"), gbc);
            gbc.gridx = 1;
            add(inputusername, gbc);

            // Password label and password field
            gbc.gridx = 0;
            gbc.gridy = 1;
            add(new JLabel("Mot de passe:"), gbc);
            gbc.gridx = 1;
            add(inputpassword, gbc);

            // Login button
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            add(loginButton, gbc);

            // Forget password button
            gbc.gridy = 3;
            add(forgetMyPasswordButton, gbc);

            // Inscription button
            gbc.gridy = 4;
            add(inscriptionButton, gbc);

            inscriptionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    registrationform inscription = new registrationform(null);
                   // inscription.setVisible(true); //ca l affiche 2 fois
                }
            });

            setLocationRelativeTo(null);
            setVisible(true);

            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    User utilisateur = new User();
                    Connection connexion = null; //conexion a la db

                    String username = inputusername.getText();
                    String password = inputpassword.getText();

                    try {
                        connexion = Connexion.getConnection();

boolean condition2=false;
                        utilisateur.selectiondb(connexion,"*","projetjava",username,password);

                        String a=utilisateur.getEmail(); //il faut les mettre apres la connexion avec db sion a et b prennet des valeur vides
                        String b=utilisateur.getPassword();

                        if ( (username.equals(a)) && (password.equals(b)) ){
                            condition2=true;
                            System.out.println("connexion est correct");
                        } else {
                            System.out.println("connexion est incorrect");}

                        if (condition2) { FormTrajet formTrajet = new FormTrajet();
                        setVisible(true);}

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }



                }
            });
        }
        public static void main(String[] args) {
            LoginForm loginf = new LoginForm();
        }
    }