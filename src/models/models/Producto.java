package models;

public class Producto {

    private String codigo;
    private String nombre;
    private String marca;
    private int stock;
    private double precio;

    public Producto(String codigo, String nombre, String marca, int stock, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.marca = marca;
        this.stock = stock;
        this.precio = precio;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getMarca() { return marca; }
    public int getStock() { return stock; }
    public double getPrecio() { return precio; }

    public void setStock(int stock) {
        this.stock = stock;
    }
}