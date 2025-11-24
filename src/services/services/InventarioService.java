package services;

import models.Producto;
import java.util.ArrayList;

public class InventarioService {

    private ArrayList<Producto> productos = new ArrayList<>();

    // ---------------------------
    // AGREGAR PRODUCTO
    // ---------------------------
    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    // ---------------------------
    // BUSCAR PRODUCTO POR CÓDIGO
    // ---------------------------
    public Producto buscarProducto(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    // ---------------------------
    // EDITAR PRODUCTO
    // ---------------------------
    public void editarProducto(Producto editado) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo().equalsIgnoreCase(editado.getCodigo())) {
                productos.set(i, editado);
                return;
            }
        }
    }

    // ---------------------------
    // ELIMINAR PRODUCTO
    // ---------------------------
    public void eliminarProducto(String codigo) {
        productos.removeIf(p -> p.getCodigo().equalsIgnoreCase(codigo));
    }

    // ---------------------------
    // OBTENER LISTA COMPLETA
    // ---------------------------
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    // ---------------------------
    // CALCULOS DEL DASHBOARD
    // ---------------------------
    public int getTotalItems() {
        return productos.size();
    }

    public int getStockTotal() {
        int total = 0;
        for (Producto p : productos) {
            total += p.getStock();
        }
        return total;
    }

    public double getValorTotal() {
        double total = 0;
        for (Producto p : productos) {
            total += p.getPrecio() * p.getStock();
        }
        return total;
    }
}