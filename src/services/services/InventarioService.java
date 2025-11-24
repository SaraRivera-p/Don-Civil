package services;

import java.util.ArrayList;
import models.Producto;

public class InventarioService {

    private ArrayList<Producto> lista = new ArrayList<>();

    public void agregar(Producto p) {
        lista.add(p);
    }

    public ArrayList<Producto> getLista() {
        return lista;
    }

    public int totalItems() {
        return lista.size();
    }

    public int totalStock() {
        int total = 0;
        for (Producto p : lista) total += p.getStock();
        return total;
    }

    public double totalValorizado() {
        double total = 0;
        for (Producto p : lista) total += p.getPrecio() * p.getStock();
        return total;
    }
}