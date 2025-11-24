import javax.swing.*;
import java.awt.*;

public class FormNuevoProducto extends JDialog {

    public FormNuevoProducto(JFrame parent) {
        super(parent, "Nuevo Producto", true);
        setSize(400, 380);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(6, 2, 10, 10));
        
        JTextField txtCodigo = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtMarca  = new JTextField();
        JTextField txtStock  = new JTextField();
        JTextField txtPrecio = new JTextField();

        add(new JLabel("Código:"));
        add(txtCodigo); 
        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Marca:"));
        add(txtMarca);
        add(new JLabel("Stock:"));
        add(txtStock);
        add(new JLabel("Precio:"));
        add(txtPrecio);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(0, 150, 0));
        btnGuardar.setForeground(Color.WHITE);

        btnGuardar.addActionListener(e -> {

            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            String marca  = txtMarca.getText();
            int stock     = Integer.parseInt(txtStock.getText());
            double precio = Double.parseDouble(txtPrecio.getText());

            // Aquí agregamos a la tabla del MainWindow
            MainWindow mw = (MainWindow) parent;
            mw.agregarProductoATabla(codigo, nombre, marca, stock, precio);

            dispose();
        });

        add(new JLabel()); // espacio
        add(btnGuardar);

        setVisible(true);
    }
}