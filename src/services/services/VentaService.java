package services;

import java.util.ArrayList;
import models.Venta;

public class VentaService {

    private ArrayList<Venta> ventas = new ArrayList<>();

    public void registrar(Venta v) {
        ventas.add(v);
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }
}