package cr.ac.ucr.paraiso.view;

import cr.ac.ucr.paraiso.controller.GameController;
import cr.ac.ucr.paraiso.model.Cell;
import cr.ac.ucr.paraiso.model.Hero;
import cr.ac.ucr.paraiso.model.Item;

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

    // Para modificar el área de texto donde se mostrarán
    private JLabel nameLabel;
    private JLabel classLabel;
    private JLabel healthLabel;
    private JLabel attackLabel;
    private JLabel goldLabel;
    private JLabel keyLabel;
    private JTextArea inventoryArea;

    // Constructor de la interfaz.
    // Inicializa la ventana y crea todos los componentes gráficos.
    public Interfaz(GameController controller) {

        // Configuración de la ventana principal
        setTitle("Proyecto Umizoomi");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Utiliza el controlador para respetar MVC
        this.controller = controller;

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
        mapPanel = new JPanel(new GridLayout(12, 12));
        mapPanel.setBackground(new Color(70, 120, 70));
        mapPanel.setBorder(BorderFactory.createTitledBorder("Mapa"));

        drawMap();

        // Panel con la información del héroe
        JPanel infoPanel = new JPanel();

        infoPanel.setPreferredSize(new Dimension(300, 700));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Información"));

        JLabel heroTitle = new JLabel("HERO STATUS");
        heroTitle.setFont(new Font("Arial", Font.BOLD, 22));
        heroTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        infoPanel.add(heroTitle);
        infoPanel.add(Box.createVerticalStrut(25));

        nameLabel = new JLabel();
        classLabel = new JLabel();
        healthLabel = new JLabel();
        attackLabel = new JLabel();
        goldLabel = new JLabel();
        keyLabel = new JLabel();

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(15));

        infoPanel.add(classLabel);
        infoPanel.add(Box.createVerticalStrut(15));

        infoPanel.add(healthLabel);
        infoPanel.add(Box.createVerticalStrut(15));

        infoPanel.add(attackLabel);
        infoPanel.add(Box.createVerticalStrut(15));

        infoPanel.add(goldLabel);
        infoPanel.add(Box.createVerticalStrut(15));

        infoPanel.add(keyLabel);
        infoPanel.add(Box.createVerticalStrut(20));

        infoPanel.add(new JLabel("Inventory"));

        inventoryArea = new JTextArea(6, 15);
        inventoryArea.setEditable(false);

        JScrollPane inventoryScroll = new JScrollPane(inventoryArea);

        infoPanel.add(inventoryScroll);

        center.add(mapPanel, BorderLayout.CENTER);
        center.add(infoPanel, BorderLayout.EAST);

        add(center, BorderLayout.CENTER);

        updateHeroInfo();
    }

    // Crea el área de mensajes ubicada en la parte inferior de la ventana.
    private void createMessagesArea() {

        messagesArea = new JTextArea();

        // Evita que el usuario pueda escribir en esta área
        messagesArea.setEditable(false);

        messagesArea.setText("=== Game Log ===\n");

        JScrollPane scroll = new JScrollPane(messagesArea);
        scroll.setPreferredSize(new Dimension(1200, 120));

        add(scroll, BorderLayout.SOUTH);
    }

    // Dibuja el mapa utilizando la información almacenada en el controlador.
    private void drawMap() {

        // Elimina el contenido anterior para volver a dibujarlo
        mapPanel.removeAll();

        // Obtiene la matriz del mapa desde el controlador
        Cell[][] map = controller.getMap();

        // Recorre todas las filas y columnas del mapa
        for (int row = 0; row < map.length; row++) {

            for (int col = 0; col < map[row].length; col++) {

                // Crea una etiqueta para representar una casilla
                JLabel cell = new JLabel(
                        String.valueOf(map[row][col].getSymbol()), SwingConstants.CENTER);

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

    //Modificar los datos del Heroe
    private void updateHeroInfo() {

        Hero hero = controller.getHero();

        nameLabel.setText("Name: " + hero.getName());

        classLabel.setText("Class: " + hero.getHeroClass());

        healthLabel.setText(
                "Health: "
                        + hero.getCurrentHealth()
                        + " / "
                        + hero.getMaxHealth());

        attackLabel.setText(
                "Attack: "
                        + hero.getAttackPower());

        goldLabel.setText(
                "Gold: "
                        + hero.getGold());

        keyLabel.setText(
                "Key: "
                        + (hero.hasKey() ? "Yes" : "No"));

        String inventoryText = "";

        for (int i = 0; i < hero.getInventory().length; i++) {

            Item item = hero.getItem(i);

            if (item != null) {

                inventoryText += (i + 1)
                        + ". "
                        + item.getName()
                        + "\n";

            }

        }

        if (inventoryText.isEmpty()) {

            inventoryText = "Empty";

        }

        inventoryArea.setText(inventoryText);

    }

    // Configura los controles del teclado para mover al héroe.
    private void createKeyControls() {

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                int row = controller.getHero().getPosX();
                int col = controller.getHero().getPosY();

                String message = "";

                // Combat (SPACE)
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {

                    message = controller.attackEnemy();

                    drawMap();
                    updateHeroInfo();

                    messagesArea.append(message + "\n");
                    messagesArea.setCaretPosition(
                            messagesArea.getDocument().getLength());

                    return;
                }

                if (e.getKeyCode() == KeyEvent.VK_1) {

                    if (controller.getHero().useItem(0)) {

                        message = "Item used.";

                    } else {

                        message = "No item in slot 1.";

                    }

                    updateHeroInfo();

                    messagesArea.append(message + "\n");

                    return;

                }

                if (e.getKeyCode() == KeyEvent.VK_2) {

                    if (controller.getHero().useItem(1)) {

                        message = "Item used.";

                    } else {

                        message = "No item in slot 2.";

                    }

                    updateHeroInfo();

                    messagesArea.append(message + "\n");

                    return;

                }

                if (e.getKeyCode() == KeyEvent.VK_3) {

                    if (controller.getHero().useItem(2)) {

                        message = "Item used.";

                    } else {

                        message = "No item in slot 3.";

                    }

                    updateHeroInfo();

                    messagesArea.append(message + "\n");

                    return;

                }

                if (e.getKeyCode() == KeyEvent.VK_4) {

                    if (controller.getHero().useItem(3)) {

                        message = "Item used.";

                    } else {

                        message = "No item in slot 4.";

                    }

                    updateHeroInfo();

                    messagesArea.append(message + "\n");

                    return;

                }

                if (e.getKeyCode() == KeyEvent.VK_5) {

                    if (controller.getHero().useItem(4)) {

                        message = "Item used.";

                    } else {

                        message = "No item in slot 5.";

                    }

                    updateHeroInfo();

                    messagesArea.append(message + "\n");

                    return;

                }

                switch (e.getKeyCode()) {

                    case KeyEvent.VK_W:
                        message = controller.moveHero(row - 1, col);
                        break;

                    case KeyEvent.VK_S:
                        message = controller.moveHero(row + 1, col);
                        break;

                    case KeyEvent.VK_A:
                        message = controller.moveHero(row, col - 1);
                        break;

                    case KeyEvent.VK_D:
                        message = controller.moveHero(row, col + 1);
                        break;

                    case KeyEvent.VK_F5:
                        controller.saveGame();
                        message = "Game Saved.";
                        break;

                    case KeyEvent.VK_F9:
                        controller.loadGame();
                        message = "Game Loaded.";
                        break;

                }

                drawMap();

                updateHeroInfo();

                messagesArea.append(message + "\n");

                messagesArea.setCaretPosition(messagesArea.getDocument().getLength());

                if (message.contains("Congratulations")
                        || message.contains("Game Over")) {

                    removeKeyListener(getKeyListeners()[0]);

                }
            }
        });

    }

}