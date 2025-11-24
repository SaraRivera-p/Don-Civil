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

        add(new JLabel("Código del Producto:"));
        add(txtCodigo);

        add(new JLabel("Cantidad Entrante:"));
        add(txtCantidad);

        JButton btnGuardar = new JButton("Registrar Entrada");

        btnGuardar.addActionListener(e -> {

            String codigo = txtCodigo.getText();
            int cantidad = Integer.parseInt(txtCantidad.getText());

            boolean encontrado = false;

            for (Producto p : inventario.getLista()) {

                if (p.getCodigo().equalsIgnoreCase(codigo)) {
                    p.setStock(p.getStock() + cantidad);
                    entradas.registrar(new Entrada(codigo, cantidad));
                    encontrado = true;
                }
            }

            if (!encontrado) {
                JOptionPane.showMessageDialog(this, "Producto no encontrado.");
                return;
            }

            JOptionPane.showMessageDialog(this, "Entrada registrada correctamente.");
        });

        add(new JLabel(""));
        add(btnGuardar);
    }
}