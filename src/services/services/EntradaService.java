package services;

import java.util.ArrayList;
import models.Entrada;

public class EntradaService {

    private ArrayList<Entrada> entradas = new ArrayList<>();

    public void registrar(Entrada e) {
        entradas.add(e);
    }

    public ArrayList<Entrada> getEntradas() {
        return entradas;
    }
}