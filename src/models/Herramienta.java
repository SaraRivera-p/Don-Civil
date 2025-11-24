package models;

public class Herramienta extends Producto {
    private String tipo;

    public Herramienta(String codigo, String nombre, int cantidad, double precio, String tipo) {
        super(codigo, nombre, cantidad, precio);
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "[Herramienta] " + super.toString() + " | Tipo: " + tipo;
    }
}