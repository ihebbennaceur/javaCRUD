import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Protrajet {
    private JButton envoyer;
    private JTextField inputvilledepart;
    private JTextField inpuvillearrive;
    private JTextField inputdate;
    private JTextField inputprix;
    private JTextField inputdescription;


    public Protrajet(int userId) {
 //les cases sont trop petit faut resize
        envoyer = new JButton("Envoyer");
        inputvilledepart = new JTextField(20);
        inpuvillearrive = new JTextField(20);
        inputdate = new JTextField(20);
        inputprix = new JTextField(20);
        inputdescription = new JTextField(20);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Ville de départ:"), gbc);
        gbc.gridx = 1;
        panel.add(inputvilledepart, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Ville d'arrivée:"), gbc);
        gbc.gridx = 1;
        panel.add(inpuvillearrive, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Date:"), gbc);
        gbc.gridx = 1;
        panel.add(inputdate, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        panel.add(new JLabel("Prix:"), gbc);
        gbc.gridx = 1;
        panel.add(inputprix, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        panel.add(inputdescription, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(envoyer, gbc);


        // crerr frame to hold the panel
        JFrame frame = new JFrame("Protrajet");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        envoyer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //data from text filed
                String villeDepart = inputvilledepart.getText();
                String villeArrivee = inpuvillearrive.getText();
                String date = inputdate.getText();
                String prix = inputprix.getText();
                String description = inputdescription.getText();

                boolean testvide=inputvilledepart.getText().isEmpty() ||
                        inpuvillearrive.getText().isEmpty() ||
                        inputdate.getText().isEmpty() ||
                        inputprix.getText().isEmpty() ||
                        inputdescription.getText().isEmpty();
                boolean cond=true;

                if (!prix.matches("\\d+")) {
                    cond = false;
                    JOptionPane.showMessageDialog(null, "Le prix est un float");
                }

                if(!inputdate.getText().matches("\\d{2}-\\d{2}-\\d{4}")) {cond=false;
                JOptionPane.showMessageDialog(null,"entrer une format date jj--mm--yyyy");}

  if (!testvide && cond) {
      Trajet tj = new Trajet();
      tj.setVilledepart(villeDepart);
      tj.setvillearrive(villeArrivee);
      tj.setDate(date);
      tj.setPrix(prix);
      tj.setDescription(description);


      try {
          Connection connexion = Connexion.getConnection();

          Trajet.ajouterTrajet(connexion, tj,userId);
          connexion.close();
          frame.dispose(); // Ferme la fenêtre actuelle

      } catch (SQLException ex) {
          ex.printStackTrace();
      }

  }

            }

            });


    }

    public static void main(String[] args) {
        //instance
        //Protrajet protrajet = new Protrajet(3);
    }
}
