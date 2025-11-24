package gui;

import models.Producto;
import services.InventarioService;

import javax.swing.*;
import java.awt.*;

public class FormEditarProducto extends JDialog {

    private JTextField txtNombre, txtMarca, txtStock, txtPrecio;

    public FormEditarProducto(JFrame parent, InventarioService inventario, String codigo) {
        super(parent, "Editar Producto", true);
        setSize(400, 350);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(5, 2, 10, 10));

        Producto p = inventario.buscarProducto(codigo);
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Producto no encontrado");
            dispose();
            return;
        }

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField(p.getNombre());
        add(txtNombre);

        add(new JLabel("Marca:"));
        txtMarca = new JTextField(p.getMarca());
        add(txtMarca);

        add(new JLabel("Stock:"));
        txtStock = new JTextField(String.valueOf(p.getStock()));
        add(txtStock);

        add(new JLabel("Precio:"));
        txtPrecio = new JTextField(String.valueOf(p.getPrecio()));
        add(txtPrecio);

        JButton btnGuardar = new JButton("Guardar cambios");
        btnGuardar.addActionListener(e -> {
            try {
                p.setNombre(txtNombre.getText());
                p.setMarca(txtMarca.getText());
                p.setStock(Integer.parseInt(txtStock.getText()));
                p.setPrecio(Double.parseDouble(txtPrecio.getText()));

                JOptionPane.showMessageDialog(this, "Producto actualizado");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos");
            }
        });

        add(btnGuardar);
        setVisible(true);
    }
}