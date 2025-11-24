package gui;

import javax.swing.*;
import java.awt.*;
import services.InventarioService;
import services.EntradaService;
import services.VentaService;

public class MainWindow extends JFrame {

    private JPanel mainPanel;

    private InventarioService inventarioService = new InventarioService();
    private EntradaService entradaService = new EntradaService();
    private VentaService ventaService = new VentaService();

    public MainWindow() {

        setTitle("Don Civil - Dashboard Inventario");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initSideMenu();
        initMainPanel();

        setVisible(true);
    }

    private void initSideMenu() {
        JPanel sideMenu = new JPanel();
        sideMenu.setBackground(new Color(30,30,30));
        sideMenu.setPreferredSize(new Dimension(220, getHeight()));
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(" DON CIVIL ");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JButton btnInventario = sideButton("Inventario");
        JButton btnEntradas = sideButton("Registrar Entrada");
        JButton btnVentas = sideButton("Registrar Venta");
        JButton btnHistorial = sideButton("Historial");

        // CAMBIO DE PANELES SIN PERDER DATOS
        btnInventario.addActionListener(e -> showPanel(new PanelInventario(inventarioService)));
        btnEntradas.addActionListener(e -> showPanel(new PanelEntradas(inventarioService, entradaService)));
        btnVentas.addActionListener(e -> showPanel(new PanelVentas(inventarioService, ventaService)));
        btnHistorial.addActionListener(e -> showPanel(new PanelHistorial(ventaService, entradaService)));

        sideMenu.add(title);
        sideMenu.add(btnInventario);
        sideMenu.add(btnEntradas);
        sideMenu.add(btnVentas);
        sideMenu.add(btnHistorial);

        sideMenu.add(Box.createVerticalGlue());

        JButton exit = sideButton("Salir");
        exit.setForeground(Color.ORANGE);
        exit.addActionListener(e -> System.exit(0));
        sideMenu.add(exit);

        add(sideMenu, BorderLayout.WEST);
    }

    private JButton sideButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(220, 40));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(45,45,45));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        return btn;
    }

    private void initMainPanel() {
        mainPanel = new JPanel(new BorderLayout());
        showPanel(new PanelInventario(inventarioService)); 
        add(mainPanel, BorderLayout.CENTER);
    }

    public void showPanel(JPanel panel) {
        mainPanel.removeAll();
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}