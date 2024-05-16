import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class FormTrajet {
    private JLabel welcome;
    private JButton proposer;
    private JButton annuler;
    private JButton modifier;
    private JButton rechercher;



//public int userId;
    public FormTrajet(int userId) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Ajout d'une marge autour du panel

        welcome = new JLabel("Bienvenue dans la page de gestion des trajets");

        proposer = new JButton("Proposer un trajet");
        rechercher = new JButton("Rechercher un trajet");
        annuler = new JButton("Annuler un trajet");
        modifier = new JButton("Modifier un trajet");

        gbc.insets = new Insets(10, 10, 10, 10); // Ajout d'espacement entre les composants

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Largeur de deux colonnes
        gbc.anchor = GridBagConstraints.CENTER; // Alignement au centre
        panel.add(welcome, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1; // Réinitialisation de la largeur
        gbc.anchor = GridBagConstraints.LINE_START; // Alignement à gauche
        panel.add(proposer, gbc);

        gbc.gridx++;
        panel.add(rechercher, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(annuler, gbc);

        gbc.gridx++;
        panel.add(modifier, gbc);

        JFrame frame = new JFrame("Protrajet");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null); // Centrage de la fenêtre sur l'écran
        frame.setVisible(true);

        welcome.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

Protrajet postuletrajet = new Protrajet(userId);
            }
        });
        proposer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
frame.dispose();
                Protrajet protrajet = new Protrajet(userId);




            }
        });

        proposer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "cancel all");

            }
        });
    }
    public static void main(String[] args) {
        FormTrajet form = new FormTrajet(2);
    }
}
