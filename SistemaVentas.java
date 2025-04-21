
package debug;

import java.util.ArrayList; 
import java.util.Scanner;
import java.util.HashMap;

public class SistemaVentas {

    //Variables de instancia
    static ArrayList<String> clientes = new ArrayList<>();
    static ArrayList<String> asientos = new ArrayList<>();
    static ArrayList<Double> precios = new ArrayList<>();
    static ArrayList<Boolean> estadoAsientos = new ArrayList<>(); //true=reservado, false=disponible
    static ArrayList<Double> ingresosTotales = new ArrayList<>();
    static ArrayList<String> reservas = new ArrayList<>(); // Clientes con reserva
    static HashMap<String, Integer> clienteAsientoMap = new HashMap<>();
   
    //Variables estáticas
    static final int capacidadSala = 50; //Número total de asientos
    static int totalVentas = 0; //Total de entradas vendidas
    static double totalIngresos = 0; //Total de ingresos generados
    static final double precioEntrada = 25000;  // Precio unitario de entrada
    
    //Variables locales
    static String nombreCliente;
    static String tipoAccion;
    static int asientoSeleccionado;
    static boolean reservado;
    
    static HashMap<String, ArrayList<Integer>> comprasClientes = new HashMap<>();
    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        inicializarSala();
        
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int opcion = obtenerOpcion(scan);
            
            switch (opcion) {
                case 1: //reservar entradas
                    realizarReserva(scan);
                    break;
                case 2: //comprar entradas
                    realizarCompra(scan);
                    break;
                case 3:
                    mostrarAsientosDisponibles(); //mostrar estado de los asientos
                    break;
                case 4://Modificar una venta existente
                    modificarVenta(scan);
                    break;
                case 5://pasar reserva a compra
                    reservaVenta(scan);
                    break;
                case 6: //imprimir boleta
                    imprimirBoleta(scan);
                    break;
                case 7: //estadisticas
                    verEstadisticas();
                    break;
                case 8: //salir
                    salir = true;
                    System.out.println("Saliendo del sistema. Gracias por ultilizar el sistema de ventas!");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
                    break;
            }
        }
        scan.close();
    }
   
    private static void mostrarMenu() {
        System.out.println("\n *** Sistema de venta de entradas ***");
        System.out.println("1. Reservar entradas");
        System.out.println("2. Comprar entradas");
        System.out.println("3. Ver estado de los asientos");
        System.out.println("4. Modidificar una venta existente");
        System.out.println("5. Convertir reserva en compra");
        System.out.println("6. Imprimir boleta");
        System.out.println("7. Ver estadisticas generales");
        System.out.println("8. Salir");
        System.out.println("Seleccione una opcion");
    }
    
    //obtener la opcion del usuario
    private static int obtenerOpcion(Scanner scan) {
       int opcion = 0;
       try {
           opcion = Integer.parseInt(scan.nextLine());
       } catch (NumberFormatException e) {
           System.out.println("Entrada no válida. Debe ingresar un número.");
       }
        return opcion;
    }
    
    //Inicializar la sala con asientos disponibles
     private static void inicializarSala() {
         for (int i = 0; i < capacidadSala; i++) {
             asientos.add("Asiento " + (i + 1));
             estadoAsientos.add(false);  // Todos los asientos inicialmente están disponibles
             precios.add(precioEntrada);
         }
     }
    
    //Función para realizar una reserva
      private static void realizarReserva(Scanner scan) {
           System.out.println("\n*** Reservar Entradas ***");
           System.out.print("Ingrese su nombre: ");
           nombreCliente = scan.nextLine(); 
           
            while (nombreCliente.isEmpty() || nombreCliente.trim().length() < 2) {
                        System.out.println("Nombre invalido. Intente nuevamente: ");
                        nombreCliente = scan.nextLine();
                    }
           
            mostrarAsientosDisponibles();
            
            System.out.print("Seleccione un asiento para reservar (1 - " + capacidadSala + "): ");
            asientoSeleccionado = Integer.parseInt(scan.nextLine()) - 1;
            
            if (asientoSeleccionado >= 0 && asientoSeleccionado < capacidadSala && !estadoAsientos.get(asientoSeleccionado)) {
               estadoAsientos.set(asientoSeleccionado, true);
               reservas.add(nombreCliente);
               clienteAsientoMap.put(nombreCliente, asientoSeleccionado);
                
               System.out.println("Asiento " + (asientoSeleccionado + 1) + " reservado exitosamente.");
               System.out.println("La reserva estará vigente por 10 minutos.");
            } else {
                 System.out.println("El asiento no está disponible o la selección es inválida.");
            }           
      }
    
    //Función para realizar una compra
      private static void realizarCompra(Scanner scan) {
           System.out.println("\n*** Comprar Entradas ***");
           System.out.print("Ingrese su nombre: ");
           nombreCliente = scan.nextLine(); 
           
           while (nombreCliente.isEmpty() || nombreCliente.trim().length() < 2) {
                        System.out.println("Nombre invalido. Intente nuevamente: ");
                        nombreCliente = scan.nextLine();
                    }
           
           mostrarAsientosDisponibles();
           
         System.out.print("Cuantos asientos desea comprar? ");
         int cantidad = Integer.parseInt(scan.nextLine());
           
            ArrayList<Integer> asientosComprados = new ArrayList<>();
            double totalCompra = 0;
            
             for (int i = 0; i < cantidad; i++) {
                 System.out.print("Seleccione asiento #" + (i + 1) + ": ");
                 int asiento = Integer.parseInt(scan.nextLine()) - 1;             
           
          if (asiento >= 0 && asiento < capacidadSala && !estadoAsientos.get(asiento)) {
               estadoAsientos.set(asiento, true);
               asientosComprados.add(asiento);
               totalCompra += precioEntrada;
               totalVentas++; 
               } else {
                 System.out.println("Asiento " + (asiento + 1) + " no disponible o inválido.");
                  i--; // repetir este asiento
               }
          }
          totalIngresos += totalCompra;  
          // Guardar la compra del cliente
          clientes.add(nombreCliente);
          comprasClientes.put(nombreCliente, asientosComprados);
          if (!asientosComprados.isEmpty()) {
              clienteAsientoMap.put(nombreCliente, asientosComprados.get(0));
          }
          
          System.out.println("Compra exitosa. Total pagado: $" + totalCompra);
      }
      
      private static void reservaVenta(Scanner scan){
           System.out.println("\n*** Convertir Reserva en Compra ***");
           System.out.print("Ingrese el nombre del cliente con reserva: ");
           nombreCliente = scan.nextLine();
           
           if (reservas.contains(nombreCliente)) {
               reservas.remove(nombreCliente);  // Sacamos de la lista de reservas
               clientes.add(nombreCliente);     // Lo pasamos a la lista de clientes
               
               totalVentas++;
               totalIngresos += precioEntrada;
               
                System.out.println("Reserva convertida en compra para " + nombreCliente);
                System.out.println("Gracias por confirmar su compra. Precio: $" + precioEntrada);
                 
           } else {
               System.out.println("No se encontró ninguna reserva con ese nombre.");
           }
      }
      
       // Función para modificar una venta existente
      private static void modificarVenta(Scanner scan) {
          
         System.out.println("\n*** Modificar Venta ***");
         System.out.print("Ingrese el nombre del cliente: ");
         nombreCliente = scan.nextLine();
         
          if (comprasClientes.containsKey(nombreCliente)) {
              ArrayList<Integer> actuales = comprasClientes.get(nombreCliente);
              for (int asiento : actuales) {
                  estadoAsientos.set(asiento, false);
              }

              System.out.println("Seleccione nuevos asientos:");
              mostrarAsientosDisponibles();

              ArrayList<Integer> nuevosAsientos = new ArrayList<>();
              double totalModificado = 0;

              for (int i = 0; i < actuales.size(); i++) {
                  System.out.print("Nuevo asiento #" + (i + 1) + ": ");
                  int nuevo = Integer.parseInt(scan.nextLine()) - 1;

                  if (nuevo >= 0 && nuevo < capacidadSala && !estadoAsientos.get(nuevo)) {
                      estadoAsientos.set(nuevo, true);
                      nuevosAsientos.add(nuevo);
                      totalModificado += precioEntrada;
                  } else {
                      System.out.println("Asiento inválido o ocupado. Intente otro.");
                      i--;
                  }
              }

              comprasClientes.put(nombreCliente, nuevosAsientos);
              System.out.println("Venta modificada correctamente.");
          } else {
              System.out.println("No se encontró una venta para este cliente.");
           }
      }
    
       // Función para imprimir la boleta
      private static void imprimirBoleta(Scanner scan) {
         System.out.println("\n*** Imprimir Boleta ***");
         System.out.print("Ingrese el nombre del cliente: ");
         nombreCliente = scan.nextLine();
         
          if (clientes.contains(nombreCliente) && comprasClientes.containsKey(nombreCliente)) {
        ArrayList<Integer> asientosCliente = comprasClientes.get(nombreCliente);
        double total = asientosCliente.size() * precioEntrada;

        System.out.println("\n--- BOLETA DE COMPRA ---");
        System.out.println("Cliente: " + nombreCliente);
        System.out.println("Asientos adquiridos: ");
        
        for (int asiento : asientosCliente) {
            System.out.println(" - Ubicacion: " + asientos.get(asiento));
        }

        System.out.println("Cantidad de entradas: " + asientosCliente.size());
        System.out.println("Precio unitario: $" + precioEntrada);
        System.out.println("TOTAL: $" + total);
        System.out.println("------------------------\n");

         
      } else {
         System.out.println("No se encontró una venta para este cliente."); 
      }
    }
      
      private static void verEstadisticas() {
          System.out.println("\n*** Estadisticas Generales ***");
          System.out.println("Entradas vendidas: " + totalVentas);
          System.out.println("Total ingresos generados: $" + totalIngresos);
          System.out.println("Cantidad de clientes con compra: " + clientes.size());
          System.out.println("Cantidad de clientes con reserva: " + reservas.size());
      }
      
       // Mostrar los asientos disponibles
        private static void mostrarAsientosDisponibles() {
            System.out.println("\nAsientos disponibles:");
             for (int i = 0; i < capacidadSala; i++) {
                 String colorInicio;
                 String estadoTexto;
                 
               if (estadoAsientos.get(i)) {
                   colorInicio = "\u001B[31m";//rojo para ocupado
                   estadoTexto = "OCUPADO";
                   
             }  else {
                   colorInicio = "\u001B[32m";//verde para disponible
                   estadoTexto = "DISPONIBLE";
               } 
               
               String colorFin = "\u001B[0m"; // reset
               System.out.println(colorInicio + (i + 1) + ". " + asientos.get(i) + " - " + estadoTexto + " - Precio: $" + precios.get(i) + colorFin);
           }
         }  
       }
