package gui;

import models.Producto;
import services.InventarioService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelInventario extends JPanel {

    private InventarioService inventario;
    private JTable tabla;
    private DefaultTableModel modelo;

    public PanelInventario(InventarioService inventario) {
        this.inventario = inventario;
        setLayout(new BorderLayout());

        // ---------------------------
        // TARJETAS SUPERIORES
        // ---------------------------
        JPanel panelTop = new JPanel(new GridLayout(1, 3, 10, 10));
        panelTop.add(card("TOTAL ITEMS", String.valueOf(inventario.getTotalItems()), Color.ORANGE));
        panelTop.add(card("STOCK TOTAL", String.valueOf(inventario.getStockTotal()), Color.GREEN));
        panelTop.add(card("VALORIZADO (S/.)", String.format("%.2f", inventario.getValorTotal()), Color.MAGENTA));

        add(panelTop, BorderLayout.NORTH);

        // ---------------------------
        // TABLA DE PRODUCTOS
        // ---------------------------
        modelo = new DefaultTableModel(new String[]{"Cód.", "Producto", "Marca", "Stock", "Precio"}, 0);
        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // ---------------------------
        // BOTONES CRUD
        // ---------------------------
        JPanel panelBotones = new JPanel();

        JButton btnNuevo = new JButton("Nuevo producto");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");

        panelBotones.add(btnNuevo);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        add(panelBotones, BorderLayout.SOUTH);

        // EVENTOS ----------------------

        btnNuevo.addActionListener(e -> {
            new FormNuevoProducto((JFrame) SwingUtilities.getWindowAncestor(this), inventario).setVisible(true);
            cargarTabla();
        });

        btnEditar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un producto");
                return;
            }
            String codigo = tabla.getValueAt(fila, 0).toString();

            new FormEditarProducto((JFrame) SwingUtilities.getWindowAncestor(this), inventario, codigo).setVisible(true);
            cargarTabla();
        });

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un producto");
                return;
            }
            String codigo = tabla.getValueAt(fila, 0).toString();
            inventario.eliminarProducto(codigo);
            cargarTabla();
        });

        cargarTabla();
    }

    // ---------------------------
    // TARJETAS DEL DASHBOARD
    // ---------------------------
    private JPanel card(String titulo, String valor, Color colorBorde) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createLineBorder(colorBorde, 3));

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 15));

        JLabel lblValor = new JLabel(valor, SwingConstants.CENTER);
        lblValor.setFont(new Font("Arial", Font.BOLD, 30));

        p.add(lblTitulo, BorderLayout.NORTH);
        p.add(lblValor, BorderLayout.CENTER);

        return p;
    }

    // ---------------------------
    // CARGAR TABLA
    // ---------------------------
    private void cargarTabla() {
        modelo.setRowCount(0);

        for (Producto p : inventario.getProductos()) {
            modelo.addRow(new Object[]{
                    p.getCodigo(),
                    p.getNombre(),
                    p.getMarca(),
                    p.getStock(),
                    "S/. " + p.getPrecio()
            });
        }
    }
}