package gui;

import javax.swing.*;
import java.awt.*;

import models.Producto;
import services.EntradaService;
import services.InventarioService;

public class PanelEntradas extends JPanel {

    private InventarioService inventario;
    private EntradaService entradas;

    private JTextField txtCodigo;
    private JTextField txtCantidad;

    public PanelEntradas(InventarioService inventario, EntradaService entradas) {
        this.inventario = inventario;
        this.entradas = entradas;

        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("Código del Producto:"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("Cantidad Entrante:"));
        txtCantidad = new JTextField();
        add(txtCantidad);

        JButton btnRegistrar = new JButton("Registrar Entrada");
        add(btnRegistrar);

        btnRegistrar.addActionListener(e -> registrarEntrada());
    }

    private void registrarEntrada() {
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

        // Actualizar Stock
        p.setStock(p.getStock() + cant);

        // Registrar en historial
        entradas.registrarEntrada(codigo, cant);

        JOptionPane.showMessageDialog(this, "Entrada registrada correctamente.");
    }
}