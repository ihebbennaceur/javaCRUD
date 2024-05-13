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




    public FormTrajet() {

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();



        gbc.insets = new Insets(9, 9, 9, 9);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(welcome, gbc);

        gbc.gridy++;
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
        frame.setVisible(true);

        welcome.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

Protrajet postuletrajet = new Protrajet();
            }
        });
        proposer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Protrajet protrajet = new Protrajet();



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
        FormTrajet form = new FormTrajet();
    }
}
