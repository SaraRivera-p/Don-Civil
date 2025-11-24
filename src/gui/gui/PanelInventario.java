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
        actualizarEstadisticas();
    }

    private void initTopCards() {

        JPanel topStats = new JPanel(new GridLayout(1, 3, 15, 15));
        topStats.setBorder(new EmptyBorder(15, 15, 15, 15));

        lblItems = new JLabel("0");
        lblStock = new JLabel("0");
        lblValor = new JLabel("0.00");

        topStats.add(card("TOTAL ITEMS", lblItems, new Color(255, 140, 0)));
        topStats.add(card("STOCK TOTAL", lblStock, new Color(0, 180, 0)));
        topStats.add(card("VALORIZADO (S/.)", lblValor, new Color(160, 70, 200)));

        add(topStats, BorderLayout.NORTH);
    }

    private JPanel card(String titulo, JLabel valor, Color accent) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(accent, 2),
                new EmptyBorder(10, 15, 10, 15)
        ));

        JLabel t = new JLabel(titulo);
        t.setFont(new Font("Segoe UI", Font.BOLD, 14));

        valor.setFont(new Font("Segoe UI", Font.BOLD, 26));
        valor.setForeground(accent);

        card.add(t, BorderLayout.NORTH);
        card.add(valor, BorderLayout.CENTER);

        return card;
    }

    private void initTable() {

        String[] cols = {"Cód.", "Producto", "Marca", "Stock", "Precio"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        table = new JTable(model);
        table.setRowHeight(28);

        JButton btnNuevo = new JButton("Nuevo Producto");
        btnNuevo.addActionListener(e -> new FormNuevoProducto(null, inventario, this));

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(btnNuevo);

        JPanel center = new JPanel(new BorderLayout());
        center.setBorder(new EmptyBorder(10, 15, 15, 15));
        center.add(new JScrollPane(table), BorderLayout.CENTER);
        center.add(bottom, BorderLayout.SOUTH);

        add(center, BorderLayout.CENTER);
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

    public void actualizarEstadisticas() {
        lblItems.setText(inventario.totalItems() + "");
        lblStock.setText(inventario.totalStock() + "");
        lblValor.setText(String.format("%.2f", inventario.totalValorizado()));
    }
}