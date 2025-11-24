package services;

import models.*;
import java.util.ArrayList;

public class InventarioService {

    private ArrayList<Producto> inventario = new ArrayList<>();

    public void agregarProducto(Producto p) {
        inventario.add(p);
        System.out.println("✔ Producto añadido.");
    }

    public void listarProductos() {
        if (inventario.isEmpty()) {
            System.out.println("Inventario vacío.");
            return;
        }
        inventario.forEach(System.out::println);
    }

    public Producto buscar(String codigo) {
        return inventario.stream()
                .filter(p -> p.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }

    public void actualizarStock(String codigo, int cantidad) {
        Producto p = buscar(codigo);
        if (p != null) {
            p.setCantidad(cantidad);
            System.out.println("✔ Stock actualizado.");
        } else {
            System.out.println("⚠ Producto no encontrado.");
        }
    }
}