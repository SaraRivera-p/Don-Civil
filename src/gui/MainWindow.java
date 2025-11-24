import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {

    private JPanel sideMenu;
    private JPanel mainPanel;

    private JButton btnInventario;
    private JButton btnVentas;
    private JButton btnEntradas;
    private JButton btnSalir;

    public MainWindow() {
        setTitle("Don Civil - Sistema de Inventario");
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        initSideMenu();
        initMainPanel();

        setVisible(true);
    }

    private void initSideMenu() {
        sideMenu = new JPanel();
        sideMenu.setBackground(new Color(33, 37, 41)); 
        sideMenu.setPreferredSize(new Dimension(220, 650));
        sideMenu.setLayout(new GridLayout(6, 1, 5, 5));

        JLabel titulo = new JLabel("   DON CIVIL");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));

        btnInventario = createMenuButton("Inventario");
        btnVentas = createMenuButton("Ventas");
        btnEntradas = createMenuButton("Entradas / Salidas");
        btnSalir = createMenuButton("Cerrar Sesión");

        // Eventos
        btnInventario.addActionListener(e -> setScreen(new PanelInventario()));
        btnVentas.addActionListener(e -> setScreen(new PanelVentas()));
        btnEntradas.addActionListener(e -> setScreen(new PanelEntradas()));
        btnSalir.addActionListener(e -> System.exit(0));

        sideMenu.add(titulo);
        sideMenu.add(btnInventario);
        sideMenu.add(btnVentas);
        sideMenu.add(btnEntradas);
        sideMenu.add(btnSalir);

        add(sideMenu, BorderLayout.WEST);
    }

    private JButton createMenuButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(new Color(52, 58, 64));
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        b.setFocusPainted(false);
        return b;
    }

    private void initMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(new PanelInventario()); 
        add(mainPanel, BorderLayout.CENTER);
    }

    private void setScreen(JPanel panel) {
        mainPanel.removeAll();
        mainPanel.add(panel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
