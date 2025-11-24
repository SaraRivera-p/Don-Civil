package services;

import models.*;
import java.util.ArrayList;

public class VentaService {
    private ArrayList<Venta> ventas = new ArrayList<>();

    public void registrarVenta(Venta v) {
        ventas.add(v);
        System.out.println("✔ Venta registrada correctamente.");
    }

    public void listarVentas() {
        ventas.forEach(System.out::println);
    }
}