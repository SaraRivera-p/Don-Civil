package models;

import java.time.LocalDateTime;

public class Venta {

    private String codigo;
    private int cantidad;
    private LocalDateTime fecha;

    public Venta(String codigo, int cantidad) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.fecha = LocalDateTime.now();
    }

    public String getCodigo() { return codigo; }
    public int getCantidad() { return cantidad; }
    public LocalDateTime getFecha() { return fecha; }
}