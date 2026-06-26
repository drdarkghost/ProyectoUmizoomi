package cr.ac.ucr.paraiso.view;

import cr.ac.ucr.paraiso.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Interfaz extends JFrame {

    // Controlador encargado de administrar toda la lógica del juego
    private GameController controller;

    // Panel donde se dibuja el mapa del juego
    private JPanel mapPanel;

    // Área de texto donde se mostrarán los mensajes del juego
    private JTextArea messagesArea;

    // Constructor de la interfaz.
    // Inicializa la ventana y crea todos los componentes gráficos.
    public Interfaz() {

        // Configuración de la ventana principal
        setTitle("Proyecto Umizoomi");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Se crea el controlador del juego
        controller = new GameController();

        // Se construyen las diferentes secciones de la interfaz
        createTitle();
        createCenterPanel();
        createMessagesArea();
        createKeyControls();

        // Se muestra la ventana
        setVisible(true);

        // Permite que la ventana detecte las teclas presionadas
        setFocusable(true);
        requestFocusInWindow();
    }

    // Crea el título ubicado en la parte superior de la ventana.
    private void createTitle() {

        JLabel title = new JLabel("PROYECTO UMIZOOMI", SwingConstants.CENTER);

        // Configuración visual del título
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setOpaque(true);
        title.setBackground(new Color(40, 60, 100));
        title.setForeground(Color.WHITE);
        title.setPreferredSize(new Dimension(1200, 70));

        add(title, BorderLayout.NORTH);
    }

    // Crea la parte central de la interfaz.
    // Contiene el mapa del juego y el panel de información del héroe.
    private void createCenterPanel() {

        JPanel center = new JPanel(new BorderLayout());

        // Panel donde se dibuja el mapa
        mapPanel = new JPanel(new GridLayout(9, 9));
        mapPanel.setBackground(new Color(70, 120, 70));
        mapPanel.setBorder(BorderFactory.createTitledBorder("Mapa"));

        // Dibuja el estado inicial del mapa
        drawMap();

        // Panel lateral con la información del héroe
        JPanel infoPanel = new JPanel();

        infoPanel.setPreferredSize(new Dimension(300, 700));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Información"));

        // Muestra los datos principales del héroe
        infoPanel.add(new JLabel("Nombre: " + controller.getHero().getName()));
        infoPanel.add(Box.createVerticalStrut(20));

        infoPanel.add(new JLabel("Vida: " + controller.getHero().getCurrentHealth()));
        infoPanel.add(Box.createVerticalStrut(20));

        infoPanel.add(new JLabel("Oro: " + controller.getHero().getGold()));
        infoPanel.add(Box.createVerticalStrut(20));

        infoPanel.add(new JLabel("Inventario:"));
        infoPanel.add(Box.createVerticalStrut(20));

        // Agrega el mapa y la información al panel central
        center.add(mapPanel, BorderLayout.CENTER);
        center.add(infoPanel, BorderLayout.EAST);

        add(center, BorderLayout.CENTER);
    }

    // Crea el área de mensajes ubicada en la parte inferior de la ventana.
    private void createMessagesArea() {

        messagesArea = new JTextArea();

        // Evita que el usuario pueda escribir en esta área
        messagesArea.setEditable(false);

        messagesArea.setText("Los mensajes del juego aparecerán aquí...");

        JScrollPane scroll = new JScrollPane(messagesArea);
        scroll.setPreferredSize(new Dimension(1200, 120));

        add(scroll, BorderLayout.SOUTH);
    }

    // Dibuja el mapa utilizando la información almacenada en el controlador.
    private void drawMap() {

        // Elimina el contenido anterior para volver a dibujarlo
        mapPanel.removeAll();

        // Obtiene la matriz del mapa desde el controlador
        char[][] map = controller.getMap();

        // Recorre todas las filas y columnas del mapa
        for (int row = 0; row < map.length; row++) {

            for (int col = 0; col < map[row].length; col++) {

                // Crea una etiqueta para representar una casilla
                JLabel cell = new JLabel(
                        String.valueOf(map[row][col]),
                        SwingConstants.CENTER);

                // Configuración visual de cada casilla
                cell.setFont(new Font("Arial", Font.BOLD, 24));
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cell.setOpaque(true);
                cell.setBackground(Color.WHITE);

                // Agrega la casilla al panel del mapa
                mapPanel.add(cell);
            }
        }

        // Actualiza el panel para reflejar los cambios
        mapPanel.revalidate();
        mapPanel.repaint();
    }

    // Configura los controles del teclado para mover al héroe.
    private void createKeyControls() {

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                // Obtiene la posición actual del héroe
                int row = controller.getHero().getPosX();
                int col = controller.getHero().getPosY();

                String message = "";

                // Movimiento hacia arriba (W)
                if (e.getKeyCode() == KeyEvent.VK_W) {

                    message = controller.moveHero(row - 1, col);

                }
                // Movimiento hacia abajo (S)
                else if (e.getKeyCode() == KeyEvent.VK_S) {

                    message = controller.moveHero(row + 1, col);

                }
                // Movimiento hacia la izquierda (A)
                else if (e.getKeyCode() == KeyEvent.VK_A) {

                    message = controller.moveHero(row, col - 1);

                }
                // Movimiento hacia la derecha (D)
                else if (e.getKeyCode() == KeyEvent.VK_D) {

                    message = controller.moveHero(row, col + 1);

                }

                // Redibuja el mapa para mostrar la nueva posición del héroe
                drawMap();

                // Muestra en pantalla el resultado del movimiento realizado
                messagesArea.setText(message);
            }
        });
    }
}