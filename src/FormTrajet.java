import javax.swing.*;
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
        welcome.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);


            }
        });
        proposer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Protrajet protrajet = new Protrajet();



            }
        });

    }
    public static void main(String[] args) {
        //FormTrajet form = new FormTrajet();
    }
}
