import javax.swing.*;
import java.awt.*;

public class PanelEntradas extends JPanel {

    public PanelEntradas() {
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Entradas y Salidas");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(title, BorderLayout.NORTH);
    }
}