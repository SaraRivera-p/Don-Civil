package gui;

import javax.swing.*;
import java.awt.*;
import services.EntradaService;
import services.InventarioService;
import models.Entrada;
import models.Producto;

public class PanelEntradas extends JPanel {

    public PanelEntradas(InventarioService inventario, EntradaService entradas) {

        setLayout(new GridLayout(5, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtCodigo = new JTextField();
        JTextField txtCantidad = new JTextField();

        add(new JLabel("Código del producto:"));
        add(txtCodigo);
        add(new JLabel("Cantidad ingresada:"));
        add(txtCantidad);

        JButton btnGuardar = new JButton("Registrar Entrada");

        btnGuardar.addActionListener(e -> {

            String codigo = txtCodigo.getText();
            int cantidad = Integer.parseInt(txtCantidad.getText());

            for (Producto p : inventario.getLista()) {
                if (p.getCodigo().equals(codigo)) {
                    p.setStock(p.getStock() + cantidad);
                }
            }

            entradas.registrar(new Entrada(codigo, cantidad));

            JOptionPane.showMessageDialog(this, "Entrada registrada");
        });

        add(new JLabel(""));
        add(btnGuardar);
    }
}