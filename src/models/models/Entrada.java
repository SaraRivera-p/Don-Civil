package models;

import java.time.LocalDateTime;

public class Entrada {

    private String codigo;
    private int cantidad;
    private LocalDateTime fecha;

    public Entrada(String codigo, int cantidad) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.fecha = LocalDateTime.now();
    }

    public String getCodigo() { return codigo; }
    public int getCantidad() { return cantidad; }
    public LocalDateTime getFecha() { return fecha; }
}