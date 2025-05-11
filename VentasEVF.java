
package Sistema;

import java.util.ArrayList;
import java.util.Scanner;

public class VentasEVF {
        
  //Variables estáticas  
  static int contadorID = 1;
  static int totalVentas = 0;
  static double ingresoTotal = 0;
  static final double descuentoNinos = 0.10;
  static final double descuentoMujer = 0.20;
  static final double descuentoEstudiante = 0.15;
  static final double descuentoTerceraEdad = 0.25;
  
  //Variables de instancia
  static ArrayList<String> ubicacion = new ArrayList<>();
  static ArrayList<String> clientes = new ArrayList<>();
  static ArrayList<Double> montos = new ArrayList<>();
  static ArrayList<Integer> numEntradas = new ArrayList <>();
  static ArrayList<Double> descuentos = new ArrayList<>();
  static ArrayList<Double> preciosBase = new ArrayList<>();
  static ArrayList<Integer> cantidades = new ArrayList<>();  
  static ArrayList<ArrayList<String>> asientosPorCliente = new ArrayList<>();
  static ArrayList <String> asientos = new ArrayList<>();
  
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
                    imprimirBoleta(scan);
                    break;
                case 3:
                    salir = true;
                    System.out.println("\nSaliendo del sistema. Gracias por usar *** TEATRO MORO APP ***");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo\n");
            }
        }
        scan.close();
    }
    
    private static void mostrarMenu() {
        
        System.out.println("\n *** VENTA DE ENTRAS 'TEATRO MORO' ***");
        System.out.println();
        System.out.println("1.- Venta de entradas");
        System.out.println("2.- Imprimir boleta");
        System.out.println("3.- Salir");
        System.out.println("Seleccione una opcion: ");
    }
    
    private static int obtenerOpcion(Scanner scan){
        int opcion = 0;
        try {
            opcion = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un numero valido.\n");
        }
        return opcion;
    }
    
    private static void ventaEntradas(Scanner scan) {
       
        System.out.println(); //acá se pide el nombre y se valida
        System.out.println("Ingrese nombre del cliente");
        String cliente = scan.nextLine();
        
        while (cliente.isEmpty() || cliente.trim().length() <2 || !cliente.matches("[a-zA-Z]+")){
            System.out.println("Nombre invalido. Debe tener al menos dos caracteres y solo contener letras. Intentelo nuevamente");
            cliente = scan.nextLine();
        }
        
        System.out.println();//edad y su validación 
        System.out.println("Ingrese edad del cliente (1 a 120)");
        int edad;
       
       while (true) {
        try {
            edad = Integer.parseInt(scan.nextLine());
            if (edad >=1 && edad <=120){
                break;
            }else {
                System.out.println("edad no valida. Debe estar entre 1 y 120, ingrese nuevamente");
            }
        } catch (NumberFormatException e) {
            System.out.println("Edad invalida. Intenta nuevamente\n");
        }
       }

        String genero = ""; 
        int opcionGenero = 0;
        while (true) {
        System.out.println();
        System.out.println("Cual es tu genero?");
        System.out.println("1.- Hombre");
        System.out.println("2.- Mujer");
        System.out.println("3.- Otro");

        try {
            opcionGenero = Integer.parseInt(scan.nextLine());
        
        switch (opcionGenero){
            case 1:
                genero = "Hombre";
                break;
            case 2:
                genero = "Mujer";
                break;
            case 3:
                genero = "Otro";
                break;
            default:
                System.out.println("Opcion no valida. Intenta nuevamente.\n");
                continue;
        }
        break;
                } catch (NumberFormatException e) {
            System.out.println("Debes ingresar un numero valido.");
        }
     }  
        
        String inputEstudiante = "";
        boolean esEstudiante = false;
        
        while(true){
        System.out.println();
        System.out.println("Es estudiante? (s/n)");
        inputEstudiante = scan.nextLine().trim().toLowerCase();
        
        if (inputEstudiante.equals("s") || inputEstudiante.equals("n")) {
            esEstudiante = inputEstudiante.equals("s");
            break;
        } else {
            System.out.println("Opcion no valida. Intenta nuevamente.");
        }
      }
        
        int cantidadEntradas = 0;
        
        while (true){
        System.out.println();
        System.out.println("Ingrese cantidad de entradas a comprar (maximo 4)");
        
        try {
            cantidadEntradas = Integer.parseInt(scan.nextLine());
            
            if (cantidadEntradas >=1 && cantidadEntradas <=4) {
                break;
            } else {
                System.out.println("Cantidad no valida. Debe ser entre 1 y 4. Intenta nuevamente");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada invalida. Debes ingresar un numero. Intenta nuevamente.\n");
        }
      }
        
        String entrada = "";
        double precioBase = 0;
        int tipoEntrada = 0;
        
        while (true) {
        System.out.println();
        System.out.println("1.- VIP $50.000");
        System.out.println("2.- Palco $40.000");
        System.out.println("3.- Platea baja $35.000");
        System.out.println("4.- Platea alta $30.000");
        System.out.println("5.- Galeria $20.000");
        System.out.println();
        System.out.println("Ingrese opcion");
        
        try {
            tipoEntrada = Integer.parseInt(scan.nextLine());

        switch (tipoEntrada) {
            case 1:
                entrada = "VIP";
                precioBase = 50000;
                break;
            case 2:
                entrada = "Palco";
                precioBase = 40000;
                break;
            case 3:
                entrada = "Platea baja";
                precioBase = 35000;
                break;
            case 4:
                entrada = "Platea alta";
                precioBase = 30000;
                break;
            case 5:
                entrada = "Galeria";
                precioBase = 20000;
                break;
            default:
                System.out.println("Opcion invalida. intenta nuevamente.\n");
                continue;
        }
            break;
            } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un numero valido. Intenta nuvamente.\n");
        }
        }
        
        System.out.println();
        double descuento = calcularDescuento(edad, esEstudiante, genero, precioBase);
        double precioFinal = (precioBase - descuento) * cantidadEntradas;
        
        //Guardar datos y mostrar resultado
        clientes.add(cliente);
        ubicacion.add(entrada);
        montos.add(precioFinal);
        descuentos.add(descuento);
        preciosBase.add(precioBase);
        cantidades.add(cantidadEntradas);
        numEntradas.add(contadorID);
        totalVentas++;
        ingresoTotal += precioFinal;
        
        //asignar asientos automaticamente
        ArrayList<String> asientosCliente = new ArrayList<>();
        for (int i = 0; i < cantidadEntradas; i++){
            String asientoAsignado = "A" + (asientos.size() + 1);
            asientos.add(asientoAsignado); // lista global
            asientosCliente.add(asientoAsignado);
            System.out.println("Asiento asignado: " + asientoAsignado);
        }
        asientosPorCliente.add(asientosCliente);//guardar lista
        
        System.out.println();
        System.out.println(" *** Venta realizada con exito! *** ");
        System.out.println();
        System.out.println("Cliente: " + cliente);
        System.out.println("Tipo de entrada: " + entrada);
        System.out.println("Asientos asignados: " + asientosCliente);
        System.out.printf("Precio por entrada: $%.0f%n", precioFinal / cantidadEntradas);
        System.out.println("Descuento aplicado: $" + (int) descuento);
        System.out.println("Total pagado: $" + (int) precioFinal);
        System.out.println("Numero de entrada: " + contadorID + "\n");
        contadorID++;
    }
    
    private static double calcularDescuento(int edad, boolean esEstudiante, String genero, double precioBase) {
        
        double descuento = 0;
        if (edad >= 60 && esEstudiante) {
            System.out.println("Aplica descuento por tercera edad y estudiante (35%)");
            descuento = precioBase * (descuentoEstudiante + descuentoTerceraEdad);
        } else if (edad >=60) {
            System.out.println("Aplica descuento por tercera edad (25%)");
            descuento = precioBase * descuentoTerceraEdad;
        } else if (edad <=12 && esEstudiante) {
            System.out.println("Aplica descuento por niño/a y estudiante (25%)");
            descuento = precioBase * (descuentoEstudiante + descuentoNinos);
        } else if (edad <=12) {
            System.out.println("Aplica descuento por niño/a (10%)");
            descuento = precioBase * descuentoNinos;
        } else if (edad >12 && genero.equalsIgnoreCase("mujer")) {
            System.out.println("Aplica descuento por ser mujer(20%)");
            descuento = precioBase * descuentoMujer;
        } else if (esEstudiante) {
            System.out.println("Aplica descuento por estudiante (15%)");
            descuento = precioBase * descuentoEstudiante;
        } else {
            System.out.println("Sin descuento aplicado.");
        }        
        return descuento;
    }
    
    private static void imprimirBoleta(Scanner scan) {
     
        System.out.println();
        System.out.println("\n*** Boleta detallada *** ");
        System.out.println("Ingrese el numbre del cliente: ");
        String nombreCliente = scan.nextLine();
        
        if (clientes.contains(nombreCliente)) {
            int idx = clientes.indexOf(nombreCliente);
            String lugar = ubicacion.get(idx);
            double precioBase = preciosBase.get(idx);
            int cant = cantidades.get(idx);
            double descuento = descuentos.get(idx);
            double total = montos.get(idx);
            
            System.out.println("\n ---- Boleta de compra ---- ");
            System.out.println();
            System.out.println("Cliente: " + nombreCliente);
            System.out.println("Ubicacion: " + lugar);
            System.out.println("Asientos asignados: " + asientosPorCliente.get(idx));
            System.out.printf("Costo base unitario: $%.0f%n", precioBase);
            System.out.println("Cantidad de entradas: " + cant);
            System.out.printf("Costo base total: $%.0f%n", precioBase * cant);
            System.out.printf("Descuento aplicado: $%.0f%n", descuento * cant);
            System.out.printf("Costo final: $%.0f%n", total);
            System.out.println("\nGracias por su compra!");
            System.out.println("------------------------------\n");
        } else {
            System.out.println("No se encontro una venta para este cliente.");
        }
    }
    
}
