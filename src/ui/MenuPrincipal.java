package src.ui;

import models.*;
import services.*;
import java.util.Scanner;

public class MenuPrincipal {

    private Scanner sc = new Scanner(System.in);
    private InventarioService inventario = new InventarioService();
    private VentaService ventas = new VentaService();

    public void iniciar() {
        int opcion;

        do {
            System.out.println("\n=== SISTEMA DON CIVIL ===");
            System.out.println("1. Agregar producto");
            System.out.println("2. Listar inventario");
            System.out.println("3. Registrar venta");
            System.out.println("4. Listar ventas");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> agregarProducto();
                case 2 -> inventario.listarProductos();
                case 3 -> registrarVenta();
                case 4 -> ventas.listarVentas();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private void agregarProducto() {
        System.out.println("Tipo (1=Herramienta, 2=Material, 3=Plomería): ");
        int tipo = sc.nextInt();
        sc.nextLine();

        System.out.print("Código: ");
        String codigo = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Cantidad: ");
        int cantidad = sc.nextInt();
        System.out.print("Precio: ");
        double precio = sc.nextDouble();
        sc.nextLine();

        switch (tipo) {
            case 1 -> {
                System.out.print("Tipo herramienta: ");
                String th = sc.nextLine();
                inventario.agregarProducto(new Herramienta(codigo, nombre, cantidad, precio, th));
            }
            case 2 -> {
                System.out.print("Categoría: ");
                String cat = sc.nextLine();
                inventario.agregarProducto(new MaterialConstruccion(codigo, nombre, cantidad, precio, cat));
            }
            case 3 -> {
                System.out.print("Tamaño: ");
                String t = sc.nextLine();
                inventario.agregarProducto(new Plomeria(codigo, nombre, cantidad, precio, t));
            }
        }
    }

    private void registrarVenta() {
        System.out.print("Fecha (dd/mm/aaaa): ");
        sc.nextLine();
        String fecha = sc.nextLine();

        Venta v = new Venta(fecha);

        System.out.print("Código del producto: ");
        String cod = sc.nextLine();

        Producto p = inventario.buscar(cod);

        if (p != null) {
            v.agregarProducto(p);
            ventas.registrarVenta(v);
        } else {
            System.out.println("⚠ Producto no encontrado.");
        }
    }
}