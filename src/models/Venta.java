import java.util.ArrayList;
import java.util.List;

public class Venta {
    private static int contador = 1;
    private int idVenta;
    private String fecha;
    private List<Producto> productos;

    public Venta(String fecha) {
        this.idVenta = contador++;
        this.fecha = fecha;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    @Override
    public String toString() {
        return "Venta #" + idVenta + " | Fecha: " + fecha + " | Items: " + productos.size();
    }
}