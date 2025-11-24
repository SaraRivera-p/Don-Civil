package models;

import java.time.LocalDateTime;

public class Entrada {

    private String codigoProducto;
    private int cantidad;
    private LocalDateTime fecha;

    public Entrada(String codigoProducto, int cantidad) {
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        this.fecha = LocalDateTime.now();
    }

    public String getCodigoProducto() { return codigoProducto; }
    public int getCantidad() { return cantidad; }
    public LocalDateTime getFecha() { return fecha; }
}