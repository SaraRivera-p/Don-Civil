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

        add(new JLabel("Código del producto vendido:"));
        add(txtCodigo);

        add(new JLabel("Cantidad vendida:"));
        add(txtCantidad);

        JButton btnGuardar = new JButton("Registrar Venta");

        btnGuardar.addActionListener(e -> {

            String codigo = txtCodigo.getText();
            int cantidad = Integer.parseInt(txtCantidad.getText());

            for (Producto p : inventario.getLista()) {
                if (p.getCodigo().equals(codigo)) {

                    if (cantidad > p.getStock()) {
                        JOptionPane.showMessageDialog(this, "Stock insuficiente.");
                        return;
                    }

                    p.setStock(p.getStock() - cantidad);
                    ventas.registrar(new Venta(codigo, cantidad, p.getPrecio()));
                }
            }

            JOptionPane.showMessageDialog(this, "Venta registrada.");
        });

        add(new JLabel(""));
        add(btnGuardar);
    }
}