package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import services.InventarioService;
import models.Producto;

public class PanelInventario extends JPanel {

    private InventarioService inventario;
    private JTable table;
    private JLabel lblItems, lblStock, lblValor;

    public PanelInventario(InventarioService inventario) {
        this.inventario = inventario;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        initTopCards();
        initTable();
        cargarTabla();
    }

    private void initTopCards() {

        JPanel top = new JPanel(new GridLayout(1, 3, 15, 15));
        top.setBorder(new EmptyBorder(15, 15, 15, 15));

        lblItems = new JLabel("0");
        lblStock = new JLabel("0");
        lblValor = new JLabel("0.00");

        top.add(card("TOTAL ITEMS", lblItems, new Color(255, 140, 0)));
        top.add(card("STOCK TOTAL", lblStock, new Color(0, 180, 0)));
        top.add(card("VALORIZADO (S/.)", lblValor, new Color(160, 70, 200)));

        add(top, BorderLayout.NORTH);
    }

    private JPanel card(String titulo, JLabel valor, Color color) {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 2),
                new EmptyBorder(10, 10, 10, 10)
        ));

        JLabel t = new JLabel(titulo);
        t.setFont(new Font("Segoe UI", Font.BOLD, 16));

        valor.setFont(new Font("Segoe UI", Font.BOLD, 30));
        valor.setForeground(color);

        panel.add(t, BorderLayout.NORTH);
        panel.add(valor, BorderLayout.CENTER);

        return panel;
    }

    private void initTable() {

        String[] columnas = {"Cód.", "Producto", "Marca", "Stock", "Precio"};

        table = new JTable(new DefaultTableModel(columnas, 0));
        table.setRowHeight(28);

        JButton btnNuevo = new JButton("Nuevo Producto");
        btnNuevo.addActionListener(e ->
                new FormNuevoProducto(null, inventario, this)
        );

        JPanel abajo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        abajo.add(btnNuevo);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(new EmptyBorder(10, 15, 15, 15));
        contenedor.add(new JScrollPane(table), BorderLayout.CENTER);
        contenedor.add(abajo, BorderLayout.SOUTH);

        add(contenedor, BorderLayout.CENTER);
    }

    public void cargarTabla() {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Producto p : inventario.getLista()) {
            model.addRow(new Object[]{
                    p.getCodigo(),
                    p.getNombre(),
                    p.getMarca(),
                    p.getStock(),
                    "S/. " + p.getPrecio()
            });
        }

        actualizarEstadisticas();
    }

    private void actualizarEstadisticas() {
        lblItems.setText(String.valueOf(inventario.totalItems()));
        lblStock.setText(String.valueOf(inventario.totalStock()));
        lblValor.setText(String.format("%.2f", inventario.totalValorizado()));
    }
}