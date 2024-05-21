import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class mdpoublie {
    private JTextField inputmail;
    private JLabel labelmail;
    private JTextField numcin;
    private JLabel cin;
    private JPanel panel1;
    private JButton recupererButton;
    private JButton loginpagebtn;

    private JTextArea voila;

    public mdpoublie() {
        recupererButton = new JButton("Recuperer");
        inputmail = new JTextField(12);
        numcin = new JTextField(10);
        cin = new JLabel("Cin");
        loginpagebtn = new JButton("Login");

        voila = new JTextArea();

        panel1 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(9, 9, 9, 9);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel1.add(new JLabel("Adresse e-mail:"), gbc);
        gbc.gridx  ++;
        panel1.add(inputmail, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel1.add(new JLabel("Numéro Cin:"), gbc);
        gbc.gridx = 1;
        panel1.add(numcin, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        gbc.gridy++;
        panel1.add(recupererButton, gbc);
        gbc.gridy++;
        voila.setText("         ");
        panel1.add(voila,gbc);

       gbc.gridy++;
        panel1.add(loginpagebtn, gbc);

        gbc.gridy++;
        gbc.gridx ++;

gbc.gridy++;


        JFrame frame = new JFrame("mot de passe oublie");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel1);
        frame.pack();
        frame.setVisible(true);


        recupererButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = inputmail.getText();
                String cin = numcin.getText();


                Connection connexion;

                try {
                    connexion = Connexion.getConnection();
                    User user = new User();
                    String password = user.getPassword(connexion, email, cin);

                    // affich le mot de passe dans la console
                    System.out.println("Le mot de passe associé à l'email " + email + " et au CIN " + cin + " est : " + password);
                    JOptionPane.showMessageDialog(null,"votre mot de passe est : "+password);
voila.append(password);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });

        loginpagebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                LoginForm loginform = new LoginForm();
            }
        });
    }



    public static void main(String[] args) {
        // Instance de la classe
        mdpoublie instance = new mdpoublie();
    }
}
