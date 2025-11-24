package gui;

import javax.swing.*;
import java.awt.*;

import services.InventarioService;
import services.EntradaService;
import services.VentaService;

public class MainWindow extends JFrame {

    private InventarioService inventario = new InventarioService();
    private EntradaService entradas = new EntradaService();
    private VentaService ventas = new VentaService();

    public MainWindow() {

        setTitle("Don Civil - Dashboard Inventario");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // MENU LATERAL
        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(6, 1, 5, 5));
        menu.setPreferredSize(new Dimension(220, 0));
        menu.setBackground(new Color(30, 30, 30));

        JLabel titulo = new JLabel("  DON CIVIL ⚒");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        JButton btnInv = new JButton("Inventario");
        JButton btnEnt = new JButton("Registrar Entrada");
        JButton btnVen = new JButton("Registrar Venta");
        JButton btnHis = new JButton("Historial");
        JButton btnSalir = new JButton("Salir");

        JButton[] botones = {btnInv, btnEnt, btnVen, btnHis, btnSalir};
        for (JButton b : botones) {
            b.setForeground(Color.WHITE);
            b.setBackground(new Color(45, 45, 45));
            b.setFocusPainted(false);
        }

        menu.add(titulo);
        menu.add(btnInv);
        menu.add(btnEnt);
        menu.add(btnVen);
        menu.add(btnHis);
        menu.add(btnSalir);

        add(menu, BorderLayout.WEST);

        // PANEL CENTRAL
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // MOSTRAR INVENTARIO AL INICIO
        mainPanel.add(new PanelInventario(inventario), BorderLayout.CENTER);

        // EVENTOS
        btnInv.addActionListener(e -> cargarPanel(mainPanel, new PanelInventario(inventario)));
        btnEnt.addActionListener(e -> cargarPanel(mainPanel, new PanelEntradas(inventario, entradas)));
        btnVen.addActionListener(e -> cargarPanel(mainPanel, new PanelVentas(inventario, ventas)));
        btnHis.addActionListener(e -> cargarPanel(mainPanel, new PanelHistorial(entradas, ventas)));
        btnSalir.addActionListener(e -> System.exit(0));
    }

    private void cargarPanel(JPanel contenedor, JPanel panel) {
        contenedor.removeAll();
        contenedor.add(panel, BorderLayout.CENTER);
        contenedor.revalidate();
        contenedor.repaint();
    }
}