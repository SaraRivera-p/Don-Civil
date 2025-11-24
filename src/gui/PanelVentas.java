package gui;

import javax.swing.*;
import java.awt.*;

public class PanelVentas extends JPanel {

    public PanelVentas() {
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Gestión de Ventas");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(title, BorderLayout.NORTH);
    }
}