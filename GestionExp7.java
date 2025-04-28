package gestionexp7;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class GestionExp7 {

    // variables estáticas
    static int contadorID = 1;
    static int totalVentas = 0;
    static double ingresoTotal = 0;
    static final double descuentoEstudiante = 0.10;
    static final double descuentoTerceraEdad = 0.15;
    static final double descuentoEstuEdad = 0.08;

    // variables de instancia
    static ArrayList<String> ubicacion = new ArrayList<>();
    static ArrayList<String> clientes = new ArrayList<>();
    static ArrayList<Double> montos = new ArrayList<>();
    static ArrayList<Integer> numEntradas = new ArrayList<>();
    static ArrayList<Double> descuentos = new ArrayList<>(); // para almacenar descuentos aplicados
    static ArrayList<Double> precioBases = new ArrayList<>(); // para costo base unitario
    static ArrayList<Integer> cantidades = new ArrayList<>(); // para cantidad de entradas

    // para imprimir boleta
    static HashMap<String, ArrayList<Integer>> comprasClientes = new HashMap<>();
    static ArrayList<String> asientos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            int opcion = obtenerOpcion(scan);
            switch (opcion) {
                case 1:
                    ventaEntradas(scan);
                    break;
                case 2:
                    mostrarEstadisticas();
                    break;
                case 3:
                    imprimirBoleta(scan);
                    break;
                case 4:
                    salir = true;
                    System.out.println("\nSaliendo del sistema. Gracias por usar *** Teatro Moro app ***");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.\n");
            }
        }
        scan.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n *** Sistema de ventas 'TEATRO MORO' ***");
        System.out.println("1. Venta de entradas");
        System.out.println("2. Estadisticas generales");
        System.out.println("3. Imprimir boleta");
        System.out.println("4. Salir");
        System.out.println("Seleccione una opcion: ");
    }

    private static int obtenerOpcion(Scanner scan) {
        int opcion = 0;
        try {
            opcion = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un numero valido.\n");
        }
        return opcion;
    }

    private static void ventaEntradas(Scanner scan) {
        System.out.println();
        System.out.println("Ingrese nombre del cliente");
        String cliente = scan.nextLine();
        while (cliente.isEmpty() || cliente.trim().length() < 2) {
            System.out.println("Nombre invalido. Intente nuevamente: ");
            cliente = scan.nextLine();
        }
        System.out.println();
        System.out.println("Ingrese edad del cliente");
        int edad;
        try {
            edad = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Edad invalida. Venta Cancelada\n");
            return;
        }
        System.out.println();
        System.out.println("Ingrese cantidad de entradas a comprar (Maximo 8)");
        int cantidadEntradas;
        try {
            cantidadEntradas = Integer.parseInt(scan.nextLine());
            if (cantidadEntradas < 1 || cantidadEntradas > 8) {
                System.out.println("Cantidad no valida. Venta cancelada.\n");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada invalida. Venta cancelada.\n");
            return;
        }
        System.out.println();
        System.out.println("Es estudiante? (s/n)");
        boolean esEstudiante = scan.nextLine().trim().equalsIgnoreCase("s");
        System.out.println();
        System.out.println("1. Balcon $20.000");
        System.out.println("2. Platea $30.000");
        System.out.println("3. VIP $40.000");
        System.out.println();
        System.out.println("Ingrese opcion");
        int tipoEntrada;
        try {
            tipoEntrada = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un numero valido. venta cancelada.\n");
            return;
        }
        String entrada = "";
        double precioBase = 0;
        switch (tipoEntrada) {
            case 1:
                entrada = "Balcon";
                precioBase = 20000;
                break;
            case 2:
                entrada = "Platea";
                precioBase = 30000;
                break;
            case 3:
                entrada = "VIP";
                precioBase = 40000;
                break;
            default:
                System.out.println("Opcion invalida. Venta cancelada.\n");
                return;
        }
        double descuento = calcularDescuento(edad, esEstudiante, precioBase);
        double precioFinal = (precioBase - descuento) * cantidadEntradas;
        if (cantidadEntradas > 3) {
            System.out.println("Aplica descuento adicional por comprar mas de 3 entradas (8%)");
            precioFinal -= precioFinal * descuentoEstuEdad;
        }
        // Guardar datos y mostrar resultado
        clientes.add(cliente);
        ubicacion.add(entrada);
        montos.add(precioFinal);
        descuentos.add(descuento);
        precioBases.add(precioBase);
        cantidades.add(cantidadEntradas);
        numEntradas.add(contadorID);
        totalVentas++;
        ingresoTotal += precioFinal;
        System.out.println("*** Venta realizada con exito!. ***");
        System.out.println("Cliente: " + cliente);
        System.out.println("Tipo de entrada: " + entrada);
        System.out.printf("Precio por entrada: $%.0f%n", precioFinal / cantidadEntradas);
        System.out.println("Descuento aplicado: $" + (int) descuento);
        System.out.println("Total pagado: $" + (int) precioFinal);
        System.out.println("Numero de entrada: " + contadorID + "\n");
        contadorID++;
    }

    private static double calcularDescuento(int edad, boolean esEstudiante, double precioBase) {
        double descuento = 0;
        if (edad >= 60 && esEstudiante) {
            System.out.println("Aplica descuento por tercera edad y estudiante (25%)");
            descuento = precioBase * (descuentoEstudiante + descuentoTerceraEdad);
        } else if (edad >= 60) {
            System.out.println("Aplica descuento por tercera edad (15%)");
            descuento = precioBase * descuentoTerceraEdad;
        } else if (esEstudiante) {
            System.out.println("Aplica descuento de estudiante (10%)");
            descuento = precioBase * descuentoEstudiante;
        } else {
            System.out.println("Sin descuento aplicado.");
        }
        return descuento;
    }

    private static void mostrarEstadisticas() {
        System.out.println("\n*** Resumen de Ventas Realizadas ***");
        if (clientes.isEmpty()) {
            System.out.println("No hay ventas registradas.\n");
        } else {
            for (int i = 0; i < clientes.size(); i++) {
                System.out.printf("Venta %d: Nombre: %s, Cantidad de entradas: %d, Ubicación: %s, Precio Final: $%.0f, Descuento: $%.0f%n",
                    i + 1,
                    clientes.get(i),
                    cantidades.get(i),
                    ubicacion.get(i),
                    montos.get(i),
                    descuentos.get(i)
                );
            }
            System.out.println();
            System.out.println("--- Estadisticas Generales ---");
            System.out.println("Total de ventas: " + totalVentas);
            System.out.printf("Ingreso total: $%.0f%n", ingresoTotal);
            System.out.println("------------------------------\n");
        }
    }

    private static void imprimirBoleta(Scanner scan) {
        System.out.println("\n*** Boleta Detallada ***");
        System.out.print("Ingrese el nombre del cliente: ");
        String nombreCliente = scan.nextLine();

        if (clientes.contains(nombreCliente)) {
            int idx = clientes.indexOf(nombreCliente);
            String lugar = ubicacion.get(idx);
            double precioBase = precioBases.get(idx);
            int cant = cantidades.get(idx);
            double descuento = descuentos.get(idx);
            double total = montos.get(idx);

            System.out.println("\n--- BOLETA DE COMPRA ---");
            System.out.println("Cliente: " + nombreCliente);
            System.out.println("Ubicacion: " + lugar);
            System.out.printf("Costo base unitario: $%.0f%n", precioBase);
            System.out.println("Cantidad de entradas: " + cant);
            System.out.printf("Costo base total: $%.0f%n", precioBase * cant);
            System.out.printf("Descuento aplicado: $%.0f%n", descuento * cant);
            System.out.printf("Costo final: $%.0f%n", total);
            System.out.println("\nGracias por su compra! Disfrute del espectaculo");
            System.out.println("------------------------\n");
        } else {
            System.out.println("No se encontró una venta para este cliente.");
        }
    }
}

