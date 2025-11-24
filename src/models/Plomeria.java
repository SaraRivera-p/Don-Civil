package models;

public class Plomeria extends Producto {
    private String tamaño;

    public Plomeria(String codigo, String nombre, int cantidad, double precio, String tamaño) {
        super(codigo, nombre, cantidad, precio);
        this.tamaño = tamaño;
    }

    @Override
    public String toString() {
        return "[Plomería] " + super.toString() + " | Tamaño: " + tamaño;
    }
}
