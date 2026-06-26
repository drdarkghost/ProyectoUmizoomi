package cr.ac.ucr.paraiso.view;

import cr.ac.ucr.paraiso.model.Map;
import javax.swing.*;
import java.awt.*;

public class Interfaz extends JFrame {

    public void run() {

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
        Map gameMap = new Map();
        char[][] map = gameMap.getMap();

        JPanel mapPanel = new JPanel(new GridLayout(9, 9));
        mapPanel.setBackground(new Color(70, 120, 70));
        mapPanel.setBorder(BorderFactory.createTitledBorder("Game Map"));

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {

                JLabel cell = new JLabel(String.valueOf(map[row][col]), SwingConstants.CENTER);

                cell.setFont(new Font("Arial", Font.BOLD, 24));
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cell.setOpaque(true);
                cell.setBackground(Color.WHITE);

                mapPanel.add(cell);
            }
        }

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

        centro.add(mapPanel, BorderLayout.CENTER);
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
