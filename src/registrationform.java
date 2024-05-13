import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;

public class registrationform extends JDialog {
    private JTextField tfname;
    private JTextField tfemail;
    private JTextField tfphone;
    private JTextField tfpassword;
    private JTextField tfpassword2;
    private JButton btnregister;
    private JButton btncancel;
    //private JButton connecter;
    private JButton inscriptionButton; // ajout du bouton d'inscription

    // Constructor
    public registrationform(JFrame parent) {
        super(parent);

        // Initialize components
        tfname = new JTextField(20);
        tfemail = new JTextField(20);
        tfphone = new JTextField(20);
        tfpassword = new JTextField(20);
        tfpassword2 = new JTextField(20);

        btnregister = new JButton("Register");
        btncancel = new JButton("Cancel");

        //connecter = new JButton("Se connecter ici");
        //connecter.setForeground(Color.BLUE);
        //connecter.setCursor(new Cursor(Cursor.HAND_CURSOR));

        inscriptionButton = new JButton("S'inscrire"); // Initialisation du bouton d'inscription

        // initilzer layout
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // ajoute des composantes
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        contentPanel.add(tfname, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        contentPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        contentPanel.add(tfemail, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        contentPanel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        contentPanel.add(tfphone, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        contentPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        contentPanel.add(tfpassword, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        contentPanel.add(new JLabel("Confirm Password:"), gbc);
        gbc.gridx = 1;
        contentPanel.add(tfpassword2, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        contentPanel.add(btnregister, gbc);
        //gbc.gridy++;
        gbc.gridx = 1;
        contentPanel.add(btncancel, gbc);
        gbc.gridx = 1;
        gbc.gridy++;
        //contentPanel.add(connecter, gbc);

        setTitle("Create New Account");
        setContentPane(contentPanel);

        pack(); // pack all diag

        setModal(true);
        setLocationRelativeTo(parent);

        btnregister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inscripuser();
            }
        });
        btncancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  //fermer
            }
        });

        inscriptionButton.addActionListener(new ActionListener() { // ajou l'écouteur d'action pour le bouton d'inscription
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button clicked");
                registrationform inscription = new registrationform(null);
                inscription.setVisible(true);
            }
        });

        setVisible(true); // show dialog

        inscriptionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                LoginForm inscription = new LoginForm();
            }
        });
    }

    private void inscripuser() {
        String name = tfname.getText();
        String email = tfemail.getText();
        String phone = tfphone.getText();
        String password = tfpassword.getText();
        String confirmPassword = tfpassword2.getText();
        boolean condition = true;

        // les champ vide ?
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tous les champs doivent être remplis.");
            condition = false;
        }

        // Verfier mdp
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Les mots de passe ne correspondent pas.");
            condition = false;
        }

        //  format de l'email
        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(null, "L'email ne contient pas le symbole '@'.");
            condition = false;
        } else {
            int atIndex = email.indexOf('@');
            int dotIndex = email.lastIndexOf('.');
            if (atIndex == -1 || dotIndex <= atIndex + 1 || dotIndex == email.length() - 1) {
                JOptionPane.showMessageDialog(null, "Le format de l'email est incorrect.");
                condition = false;
            }
        }
        if (condition) {
            Connection connexion = null; //conexion a la db

            try {
                connexion = Connexion.getConnection();
                User utilisateur = new User();

                utilisateur.setname(name);
                utilisateur.setemail(email);
                utilisateur.setphone(phone);
                utilisateur.setpassword(password);
                utilisateur.ajouterUtilisateur(connexion);

                JOptionPane.showMessageDialog(null, "Inscription réussie ! Vous pouvez maintenant vous connecter.");
dispose();


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static void main(String[] args) {
        //registrationform frm = new registrationform(null);
    }
}
