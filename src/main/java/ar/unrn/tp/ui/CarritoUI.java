package ar.unrn.tp.ui;

import ar.unrn.tp.modelo.Tarjeta;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.List;
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CarritoUI extends JFrame {

    private JPanel contentPane;

    private Tarjeta tarjetaSeleccionada;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CarritoUI frame = new CarritoUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public CarritoUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel ProductosNewLabel = new JLabel("Productos: ");
        ProductosNewLabel.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 14));
        ProductosNewLabel.setBounds(189, 11, 185, 14);
        contentPane.add(ProductosNewLabel);

        List list = new List();
        list.setBounds(23, 41, 384, 133);
        contentPane.add(list);

        JButton verMontoNewButton = new JButton("Ver Monto Total");
        verMontoNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        verMontoNewButton.setBounds(125, 180, 199, 23);
        contentPane.add(verMontoNewButton);

        JButton comprarNewButton = new JButton("Comprar");
        comprarNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        comprarNewButton.setBounds(125, 214, 199, 23);
        contentPane.add(comprarNewButton);
    }
}
