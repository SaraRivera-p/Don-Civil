package gui;

import javax.swing.*;
import java.awt.*;
import services.VentaService;
import services.InventarioService;
import models.Producto;
import models.Venta;

public class PanelVentas extends JPanel {

    public PanelVentas(InventarioService inventario, VentaService ventas) {

        setLayout(new GridLayout(5, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtCodigo = new JTextField();
        JTextField txtCantidad = new JTextField();

        add(new JLabel("Código del Producto:"));
        add(txtCodigo);

        add(new JLabel("Cantidad Vendida:"));
        add(txtCantidad);

        JButton btnGuardar = new JButton("Registrar Venta");

        btnGuardar.addActionListener(e -> {

            String codigo = txtCodigo.getText();
            int cantidad = Integer.parseInt(txtCantidad.getText());

            boolean encontrado = false;

            for (Producto p : inventario.getLista()) {

                if (p.getCodigo().equalsIgnoreCase(codigo)) {

                    encontrado = true;

                    if (cantidad > p.getStock()) {
                        JOptionPane.showMessageDialog(this,
                                "No hay suficiente stock.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    p.setStock(p.getStock() - cantidad);

                    ventas.registrar(new Venta(codigo, cantidad, p.getPrecio()));

                    JOptionPane.showMessageDialog(this, "Venta registrada correctamente.");
                }
            }

            if (!encontrado) {
                JOptionPane.showMessageDialog(this, "Producto no encontrado.");
            }
        });

        add(new JLabel(""));
        add(btnGuardar);
    }
}