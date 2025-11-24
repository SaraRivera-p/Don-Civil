package services;

import models.Venta;
import java.util.ArrayList;

public class VentaService {

    private ArrayList<Venta> ventas = new ArrayList<>();

    // REGISTRAR UNA VENTA
    public void registrarVenta(String codigo, int cantidad) {
        ventas.add(new Venta(codigo, cantidad));
    }

    // OBTENER TODAS LAS VENTAS
    public ArrayList<Venta> getVentas() {
        return ventas;
    }
}