package gui;

import models.Producto;
import services.InventarioService;

import javax.swing.*;
import java.awt.*;

public class FormNuevoProducto extends JDialog {

    private JTextField txtCodigo, txtNombre, txtMarca, txtStock, txtPrecio;

    public FormNuevoProducto(JFrame parent, InventarioService inventario) {
        super(parent, "Nuevo Producto", true);
        setSize(400, 350);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        add(txtMarca);

        add(new JLabel("Stock:"));
        txtStock = new JTextField();
        add(txtStock);

        add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        add(txtPrecio);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            try {
                Producto p = new Producto(
                        txtCodigo.getText(),
                        txtNombre.getText(),
                        txtMarca.getText(),
                        Integer.parseInt(txtStock.getText()),
                        Double.parseDouble(txtPrecio.getText())
                );
                inventario.agregarProducto(p);
                JOptionPane.showMessageDialog(this, "Producto agregado");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos");
            }
        });

        add(btnGuardar);

        setVisible(true);
    }
}