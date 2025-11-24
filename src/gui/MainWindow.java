import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainWindow extends JFrame {

    private JPanel sideMenu;
    private JPanel topStats;
    private JTable table;

    public MainWindow() {
        FlatLightLaf.setup();  // Aplicar estilo moderno

        setTitle("Don Civil - Dashboard Inventario");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initSideMenu();
        initTopCards();
        initTable();

        setVisible(true);
    }

    private void initSideMenu() {
        sideMenu = new JPanel();
        sideMenu.setBackground(new Color(30, 30, 30));
        sideMenu.setPreferredSize(new Dimension(220, getHeight()));
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(" DON CIVIL ⚒");
        title.setForeground(Color.WHITE);
        title.setBorder(new EmptyBorder(20, 10, 20, 10));
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));

        sideMenu.add(title);
        sideMenu.add(sideButton("Registrar Entrada"));
        sideMenu.add(sideButton("Ver Inventario"));
        sideMenu.add(sideButton("Ventas"));
        sideMenu.add(sideButton("Historial"));

        JButton exit = sideButton("Salir");
        exit.setForeground(Color.ORANGE);
        sideMenu.add(Box.createVerticalGlue());
        sideMenu.add(exit);

        add(sideMenu, BorderLayout.WEST);
    }

    private JButton sideButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(220, 40));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(45, 45, 45));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        return btn;
    }

    private void initTopCards() {
        topStats = new JPanel();
        topStats.setLayout(new GridLayout(1, 3, 15, 15));
        topStats.setBorder(new EmptyBorder(15, 15, 15, 15));

        topStats.add(statCard("TOTAL ITEMS", "5", new Color(255, 140, 0)));
        topStats.add(statCard("STOCK TOTAL", "22", new Color(0, 180, 0)));
        topStats.add(statCard("VALORIZADO (S/.)", "117.50", new Color(160, 70, 200)));

        add(topStats, BorderLayout.NORTH);
    }

    private JPanel statCard(String title, String value, Color accent) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(accent, 2, true),
                new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel t = new JLabel(title);
        t.setFont(new Font("Segoe UI", Font.BOLD, 14));
        t.setForeground(Color.DARK_GRAY);

        JLabel v = new JLabel(value);
        v.setFont(new Font("Segoe UI", Font.BOLD, 32));
        v.setForeground(accent);

        card.add(t, BorderLayout.NORTH);
        card.add(v, BorderLayout.CENTER);

        return card;
    }

    private void initTable() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 15, 15, 15));

        JLabel label = new JLabel("Existencias en Almacén");
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(label, BorderLayout.NORTH);

        // Modelo de tabla
        String[] cols = {"Cód.", "Producto", "Marca", "Stock", "Precio", "Editar"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        table = new JTable(model);

        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(245, 245, 245));

        // BOTÓN NUEVO PRODUCTO
        JButton btnNuevo = new JButton("➕ Nuevo Producto");
        btnNuevo.setBackground(new Color(30, 144, 255));
        btnNuevo.setForeground(Color.WHITE);
        btnNuevo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnNuevo.setFocusPainted(false);
        btnNuevo.addActionListener(e -> new FormNuevoProducto(this));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(btnNuevo);

        panel.add(topPanel, BorderLayout.SOUTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        add(panel, BorderLayout.CENTER);
    }

    // MÉTODO PARA AGREGAR PRODUCTOS A LA TABLA
    public void agregarProductoATabla(String codigo, String nombre, String marca, int stock, double precio) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();

        Object[] fila = {
                codigo,
                nombre,
                marca,
                stock,
                "S/. " + precio,
                "✏"
        };

        model.addRow(fila);
    }
}