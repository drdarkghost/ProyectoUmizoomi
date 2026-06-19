package cr.ac.ucr.paraiso.view;

import javax.swing.*;
import java.awt.*;

public class Interfaz extends JFrame {

    public Interfaz() {

        setTitle("Proyecto Umizoomi");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("PROYECTO UMIZOOMI", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 32));
        titulo.setOpaque(true);
        titulo.setBackground(new Color(40, 60, 100));
        titulo.setForeground(Color.WHITE);
        titulo.setPreferredSize(new Dimension(1200, 70));

        add(titulo, BorderLayout.NORTH);

        // Panel principal
        JPanel centro = new JPanel(new BorderLayout());

        // Zona del mapa
        JPanel mapaPanel = new JPanel();
        mapaPanel.setBackground(new Color(70, 120, 70));
        mapaPanel.setBorder(BorderFactory.createTitledBorder("Mapa"));

        JLabel mapaLabel = new JLabel("Aquí se mostrará el mapa");
        mapaLabel.setFont(new Font("Arial", Font.BOLD, 24));

        mapaPanel.add(mapaLabel);

        // Panel lateral
        JPanel infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(300, 700));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        infoPanel.setBorder(
                BorderFactory.createTitledBorder("Información")
        );

        infoPanel.add(new JLabel("Nombre: "));
        infoPanel.add(Box.createVerticalStrut(20));

        infoPanel.add(new JLabel("Vida: "));
        infoPanel.add(Box.createVerticalStrut(20));

        infoPanel.add(new JLabel("Oro: "));
        infoPanel.add(Box.createVerticalStrut(20));

        infoPanel.add(new JLabel("Inventario: "));
        infoPanel.add(Box.createVerticalStrut(20));

        centro.add(mapaPanel, BorderLayout.CENTER);
        centro.add(infoPanel, BorderLayout.EAST);

        add(centro, BorderLayout.CENTER);

        // Mensajes
        JTextArea mensajes = new JTextArea();
        mensajes.setEditable(false);
        mensajes.setText("Mensajes del juego aparecerán aquí...");

        JScrollPane scroll = new JScrollPane(mensajes);
        scroll.setPreferredSize(new Dimension(1200, 120));

        add(scroll, BorderLayout.SOUTH);

        setVisible(true);
    }
}
