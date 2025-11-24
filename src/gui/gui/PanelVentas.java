package gui;

import javax.swing.*;
import java.awt.*;

import models.Producto;
import services.InventarioService;
import services.VentaService;

public class PanelVentas extends JPanel {

    private InventarioService inventario;
    private VentaService ventas;

    private JTextField txtCodigo;
    private JTextField txtCantidad;

    public PanelVentas(InventarioService inventario, VentaService ventas) {
        this.inventario = inventario;
        this.ventas = ventas;

        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("Código del Producto:"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("Cantidad a vender:"));
        txtCantidad = new JTextField();
        add(txtCantidad);

        JButton btnVender = new JButton("Registrar Venta");
        add(btnVender);

        btnVender.addActionListener(e -> registrarVenta());
    }

    private void registrarVenta() {
        String codigo = txtCodigo.getText().trim();
        String cantTexto = txtCantidad.getText().trim();

        if (codigo.isEmpty() || cantTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.");
            return;
        }

        int cant;
        try {
            cant = Integer.parseInt(cantTexto);
            if (cant <= 0) throw new Exception();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Cantidad inválida.");
            return;
        }

        Producto p = inventario.buscarProducto(codigo);
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Producto no encontrado.");
            return;
        }

        if (p.getStock() < cant) {
            JOptionPane.showMessageDialog(this,
                    "Stock insuficiente. Disponible: " + p.getStock());
            return;
        }

        // Descontar stock
        p.setStock(p.getStock() - cant);

        // Registrar venta
        ventas.registrarVenta(codigo, cant);

        JOptionPane.showMessageDialog(this, "Venta registrada correctamente.");
    }
}