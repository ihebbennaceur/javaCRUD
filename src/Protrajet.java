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

    public Protrajet() {
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


            }
        });
    }

    public static void main(String[] args) {
        //instance
        Protrajet protrajet = new Protrajet();
    }
}
