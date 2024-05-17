import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FormTrajet {
    private JLabel welcome;
    private JLabel villeprecise;

    private JButton proposer;
    private JButton logout;
    private JButton mestrajets;
    private JButton rechercher;
    private JButton modifier;
    private JButton supprimer;



    //public int userId;
    public FormTrajet(int userId) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Ajout d'une marge autour du panel

        welcome = new JLabel("Bienvenue dans la page de gestion des trajets");
        villeprecise = new JLabel("une ville precise ?");

        proposer = new JButton("Proposer un trajet");
        rechercher = new JButton("Voir tout les trajets");
        logout = new JButton("Logout");
        mestrajets = new JButton("Mes Trajets");

        JTextField inputTrajetIdModif = new JTextField(11);
        JTextField inputTrajetIdSuppr = new JTextField(11);
        modifier = new JButton("Modifier Trajet");
        supprimer = new JButton("Supprimer Trajet");

        JTextField inputVille = new JTextField(11);

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

        gbc.gridy++;
        panel.add(rechercher, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(villeprecise, gbc);
        gbc.gridx++;
        panel.add(inputVille, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("ID Trajet à modifier :"), gbc);
        gbc.gridx++;
        panel.add(inputTrajetIdModif, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("ID Trajet à supprimer :"), gbc);
        gbc.gridx++;
        panel.add(inputTrajetIdSuppr, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(mestrajets, gbc);

        gbc.gridx++;
        panel.add(modifier, gbc);

        gbc.gridx++;
        panel.add(supprimer, gbc);

        gbc.gridy++;
        gbc.gridx = 4;
        panel.add(logout, gbc);

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

//Protrajet postuletrajet = new Protrajet(userId);
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
        mestrajets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connexion = Connexion.getConnection();
                    List<Trajet> trajets = Trajet.getTrajetsByUserId(connexion, userId);
                    connexion.close();

                    StringBuilder trajetsString = new StringBuilder();
                    for (Trajet trajet : trajets) {
                        trajetsString.append("ID: ").append(trajet.getId())
                                .append(", Ville de départ: ").append(trajet.getVilledepart())
                                .append(", Ville d'arrivée: ").append(trajet.getvillearrive())
                                .append(", Date: ").append(trajet.getDate())
                                .append(", Prix: ").append(trajet.getPrix())
                                .append(", Description: ").append(trajet.getDescription())
                                .append("\n");
                    }
                    if (trajetsString.length() > 0) {
                        JOptionPane.showMessageDialog(null, trajetsString.toString(), "Mes trajets", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Aucun trajet trouvé.", "Mes trajets", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "cancel all");
                frame.dispose();
                LoginForm newlog = new LoginForm();

            }
        });
        rechercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ville = inputVille.getText(); // Supposons que inputVille est votre champ de saisie pour la ville
                try {
                    Connection connexion = Connexion.getConnection();
                    List<Trajet> trajets = Trajet.rechercherTrajets(connexion, ville);
                    connexion.close();

                    // Afficher les trajets trouvés dans une fenêtre de dialogue ou tout autre composant graphique
                    StringBuilder trajetsString = new StringBuilder();
                    for (Trajet trajet : trajets) {
                        trajetsString.append("ID: ").append(trajet.getId()).append(", Ville de départ: ").append(trajet.getVilledepart()).append(", Ville d'arrivée: ").append(trajet.getvillearrive()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, trajetsString.toString(), "Résultats de la recherche", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


        supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String trajetIdStr = inputTrajetIdSuppr.getText();
                if (!trajetIdStr.isEmpty()) {
                    try {
                        int trajetId = Integer.parseInt(trajetIdStr);
                        Connection connexion = Connexion.getConnection();
                        Trajet.supprimerTrajet(connexion, trajetId);
                        connexion.close();
                        JOptionPane.showMessageDialog(null, "Trajet supprimé avec succès !");
                    } catch (SQLException | NumberFormatException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });





        modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String trajetIdStr = inputTrajetIdModif.getText();
                if (!trajetIdStr.isEmpty()) {
                    try {
                        int trajetId = Integer.parseInt(trajetIdStr);
                        Connection connexion = Connexion.getConnection();
                        Trajet trajet = Trajet.getTrajetById(connexion, trajetId);
                        connexion.close();

                        if (trajet != null) {
                            // Afficher les informations du trajet à modifier
                            JTextField newVilleDepart = new JTextField(trajet.getVilledepart());
                            JTextField newVilleArrive = new JTextField(trajet.getvillearrive());
                            JTextField newDate = new JTextField(trajet.getDate());
                            JTextField newPrix = new JTextField(trajet.getPrix());
                            JTextField newDescription = new JTextField(trajet.getDescription());

                            Object[] fields = {
                                    "Ville de départ:", newVilleDepart,
                                    "Ville d'arrivée:", newVilleArrive,
                                    "Date:", newDate,
                                    "Prix:", newPrix,
                                    "Description:", newDescription
                            };

                            int option = JOptionPane.showConfirmDialog(null, fields, "Modifier Trajet", JOptionPane.OK_CANCEL_OPTION);
                            if (option == JOptionPane.OK_OPTION) {
                                trajet.setVilledepart(newVilleDepart.getText());
                                trajet.setvillearrive(newVilleArrive.getText());
                                trajet.setDate(newDate.getText());
                                trajet.setPrix(newPrix.getText());
                                trajet.setDescription(newDescription.getText());

                                connexion = Connexion.getConnection();
                                Trajet.modifierTrajet(connexion, trajet);
                                connexion.close();
                                JOptionPane.showMessageDialog(null, "Trajet modifié avec succès !");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Trajet non trouvé.");
                        }
                    } catch (SQLException | NumberFormatException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });





    }
    public static void main(String[] args) {
        // FormTrajet form = new FormTrajet(2);
    }
}
