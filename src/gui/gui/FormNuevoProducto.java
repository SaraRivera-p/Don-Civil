package gui;

import javax.swing.*;
import java.awt.*;
import services.InventarioService;
import models.Producto;

public class FormNuevoProducto extends JDialog {

    public FormNuevoProducto(JFrame parent, InventarioService inventario, PanelInventario panel) {
        super(parent, "Nuevo Producto", true);

        setSize(400, 350);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(6, 2, 10, 10));

        JTextField txtCodigo = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtMarca = new JTextField();
        JTextField txtStock = new JTextField();
        JTextField txtPrecio = new JTextField();

        add(new JLabel("Código:"));
        add(txtCodigo);
        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Marca:"));
        add(txtMarca);
        add(new JLabel("Stock:"));
        add(txtStock);
        add(new JLabel("Precio:"));
        add(txtPrecio);

        JButton btnGuardar = new JButton("Guardar");

        btnGuardar.addActionListener(e -> {
            Producto p = new Producto(
                    txtCodigo.getText(),
                    txtNombre.getText(),
                    txtMarca.getText(),
                    Integer.parseInt(txtStock.getText()),
                    Double.parseDouble(txtPrecio.getText())
            );

            inventario.agregar(p);
            panel.cargarTabla();
            dispose();
        });

        add(new JLabel(""));
        add(btnGuardar);

        setVisible(true);
    }
}