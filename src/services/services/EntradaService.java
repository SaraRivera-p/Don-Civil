package services;

import models.Entrada;
import java.util.ArrayList;

public class EntradaService {

    private ArrayList<Entrada> entradas = new ArrayList<>();

    // REGISTRAR UNA ENTRADA
    public void registrarEntrada(String codigo, int cantidad) {
        entradas.add(new Entrada(codigo, cantidad));
    }

    // OBTENER TODAS LAS ENTRADAS
    public ArrayList<Entrada> getEntradas() {
        return entradas;
    }
}