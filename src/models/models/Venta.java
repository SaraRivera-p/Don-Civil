package models;

import java.time.LocalDateTime;

public class Venta {

    private String codigoProducto;
    private int cantidad;
    private double precioUnitario;
    private LocalDateTime fecha;

    public Venta(String codigoProducto, int cantidad, double precioUnitario) {
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.fecha = LocalDateTime.now();
    }

    public String getCodigoProducto() { return codigoProducto; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public LocalDateTime getFecha() { return fecha; }
}