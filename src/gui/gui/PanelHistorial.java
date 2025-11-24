package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import services.EntradaService;
import services.VentaService;
import utils.ExportarPDF;

public class PanelHistorial extends JPanel {

    private EntradaService entradas;
    private VentaService ventas;
    private JTable table;
    private DefaultTableModel model;

    public PanelHistorial(EntradaService entradas, VentaService ventas) {
        this.entradas = entradas;
        this.ventas = ventas;

        setLayout(new BorderLayout());

        // --------------------------
        // TABLA DE HISTORIAL
        // --------------------------
        model = new DefaultTableModel(new String[]{"Tipo", "Código", "Cantidad", "Fecha"}, 0);
        table = new JTable(model);

        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        // BOTÓN EXPORTAR
        JButton btnPDF = new JButton("Exportar PDF");
        btnPDF.addActionListener(e -> {
            ExportarPDF.exportarTabla(
                    table
            );
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnPDF);

        add(panelBotones, BorderLayout.SOUTH);

        // Cargar datos
        cargarHistorial();
    }

    // --------------------------
    // CARGAR TODOS LOS REGISTROS
    // --------------------------
    public void cargarHistorial() {
        model.setRowCount(0); // limpiar tabla

        // Cargar ventas
        ventas.getVentas().forEach(v -> {
            model.addRow(new Object[] {
                    "VENTA",
                    v.getCodigo(),
                    v.getCantidad(),
                    v.getFecha()
            });
        });

        // Cargar entradas
        entradas.getEntradas().forEach(e -> {
            model.addRow(new Object[]{
                    "ENTRADA",
                    e.getCodigo(),
                    e.getCantidad(),
                    e.getFecha()
            });
        });
    }
}