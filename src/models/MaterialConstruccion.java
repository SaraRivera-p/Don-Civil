public class MaterialConstruccion extends Producto {
    private String categoria;

    public MaterialConstruccion(String codigo, String nombre, int cantidad, double precio, String categoria) {
        super(codigo, nombre, cantidad, precio);
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "[Material] " + super.toString() + " | Categoria: " + categoria;
    }
}