package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import services.VentaService;
import services.EntradaService;
import models.Venta;
import models.Entrada;

public class PanelHistorial extends JPanel {

    public PanelHistorial(VentaService ventas, EntradaService entradas) {
        setLayout(new BorderLayout());

        String[] cols = {"Tipo", "Código", "Cantidad", "Fecha"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);

        for (Venta v : ventas.getVentas()) {
            model.addRow(new Object[]{"VENTA", v.getCodigoProducto(), v.getCantidad(), v.getFecha()});
        }

        for (Entrada e : entradas.getEntradas()) {
            model.addRow(new Object[]{"ENTRADA", e.getCodigoProducto(), e.getCantidad(), e.getFecha()});
        }

        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}