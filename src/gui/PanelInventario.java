import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelInventario extends JPanel {

    public PanelInventario() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Inventario Actual");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(title, BorderLayout.NORTH);

        // Tabla
        String[] columnas = {"Código", "Nombre", "Marca", "Stock", "Precio"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        JTable tabla = new JTable(model);
        JScrollPane scroll = new JScrollPane(tabla);

        add(scroll, BorderLayout.CENTER);

        // Botón agregar
        JButton btnNuevo = new JButton("Nuevo Producto");
        btnNuevo.setBackground(new Color(0, 123, 255));
        btnNuevo.setForeground(Color.WHITE);
        btnNuevo.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(btnNuevo);

        add(bottom, BorderLayout.SOUTH);
    }
}