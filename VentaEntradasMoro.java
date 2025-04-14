
package Teatro;

import java.util.ArrayList; 
import java.util.Scanner;

public class VentaEntradasMoro {

    // variables estáticas: compartidas por toda la clase
    static int contadorID = 1;
    static int totalVentas = 0;
    static double ingresoTotal = 0;
    static final double descuentoEstudiante = 0.10;
    static final double descuentoTerceraEdad = 0.15;
    static final double descuentoEstuEdad = 0.08;
     
    //variables de instancia usando paralelas
    static ArrayList<String> ubicacion = new ArrayList<>();
    static ArrayList<String> clientes = new ArrayList<>();
    static ArrayList<Double> montos = new ArrayList<>();
    static ArrayList<Integer> numEntradas = new ArrayList<>();
   
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        double descuento = 0;
        double precioFinal = 0;
        int tipoEntrada = 0; 
        int edad = 0;
        boolean salir = false;
        
        while (!salir) {
            System.out.println(" *** Sistema de ventas 'TEATRO MORO' *** ");
            System.out.println(" ");//solo estetico
            
            System.out.println("1. Venta de entradas");
            System.out.println("2. Promociones");
            System.out.println("3. Busqueda de entradas");
            System.out.println("4. Eliminacion de entradas");
            System.out.println("5. Estadisticas generales");
            System.out.println("6. Salir");
             System.out.println(" ");//solo estetico
             
            System.out.println("Seleccione una opcion: ");
            
            String opcionMenu = scan.nextLine();
            int opcion = 0;
            
            try {
                opcion = Integer.parseInt(opcionMenu);
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero del 1 al 6. \n");
                continue;
            }
            
            if (opcion < 1 || opcion > 6){
                System.out.println("Opcion fuera de rango. Intente nuevamente. \n");
                continue;
            }
            
            //venta de entradas
            switch (opcion){
                case 1:
                     System.out.println(" ");//solo estetico
                    System.out.println("Ingrese nombre del cliente");
                    String cliente = scan.nextLine();
                    
                    while (cliente.isEmpty() || cliente.trim().length() < 2) {
                        System.out.println("Nombre invalido. Intente nuevamente: ");
                        cliente = scan.nextLine();
                    }
                    
                    System.out.println(" ");//solo estetico
                    System.out.println("Ingrese edad del cliente");
                    
                    try {
                        edad = Integer.parseInt(scan.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Edad invalida. Venta Cancelada\n");
                        continue;
                    }
                    
                    System.out.println(" ");//solo estetico
                    System.out.println("Ingrese cantidad de entradas a comprar (Maximo 8)");
                    int cantidadEntradas = 0;
                    
                    try {
                        cantidadEntradas = Integer.parseInt(scan.nextLine());
                        if (cantidadEntradas < 1 || cantidadEntradas > 8) {
                            System.out.println("Cantidad no valida. Venta cancelada.\n");
                            continue;
                        }
                    } catch (NumberFormatException e){
                        System.out.println("Entrada invalida. Venta cancelada.\n");
                        continue;
                    }
                    
                    System.out.println(" ");//solo estetico
                    System.out.println("Es estudiante? (s/n)");
                    String estudianteResp = scan.nextLine().trim().toLowerCase();
                    boolean esEstudiante = estudianteResp.equals("s");
                    
                    //ubicacion entrada
                    System.out.println(" ");//solo estetico
                    System.out.println("1. General $20.000");
                    System.out.println("2. Platea $30.000");
                    System.out.println("3. VIP $40.000");
                    System.out.println(" ");//solo estetico
                    System.out.println("Ingrese opcion");
                    
                    try {
                        tipoEntrada = Integer.parseInt(scan.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Debe ingresar un numero valido. venta cancelada.\n");
                        continue;
                    }
                    
                    String entrada = " ";
                    double precioBase = 0;
                    
                    switch (tipoEntrada) {
                        case 1:
                            entrada = "General";
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
                            continue;
                    }
                    
                    //calculo descuento y precio final
                    if (edad >=60 && esEstudiante){ 
                        System.out.println(" ");//solo estetico
                        System.out.println("Aplica descuento por tercera edad y estudiante (25%)");
                        descuento = precioBase * (descuentoEstudiante + descuentoTerceraEdad);
                    } else if (edad >=60) {
                        System.out.println(" ");//solo estetico
                        System.out.println("Aplica descuento por tercera edad (15%");
                        descuento = precioBase * descuentoTerceraEdad;
                    } else if (esEstudiante) {
                        System.out.println(" ");//solo estetico
                        System.out.println("Aplica descuento de estudiante (10%)");
                        descuento = precioBase * descuentoEstudiante;
                    }else {
                        System.out.println(" ");//solo estetico
                        System.out.println("Sin descuento aplicado.");
                    }
                    
                    precioFinal = (precioBase - descuento) * cantidadEntradas;
                    
                    if (cantidadEntradas > 3) {
                        double descuentoCantidad = precioFinal * descuentoEstuEdad;
                        System.out.println("Aplica descuento adicional por comprar mas de 3 entradas (8%)");
                        System.out.println(" ");//solo estetico
                        precioFinal -= descuentoCantidad;
                    }
                    
                    //guardar datos
                    clientes.add(cliente);
                    ubicacion.add(entrada);
                    montos.add(precioFinal);
                    numEntradas.add(contadorID++);
                    
                    //sumar totales
                    totalVentas++;
                    ingresoTotal += precioFinal;
                    
                    System.out.println(" *** Venta realizada con exito!. *** ");
                     System.out.println(" ");//solo estetico
                    System.out.println("Cliente: " + cliente);
                    System.out.println("Tipo de entrada: " + entrada);
                    System.out.printf("Precio por entrada (con descuentos): $%.0f\n",(precioFinal / cantidadEntradas));
                    System.out.println("Descuento aplicado: $" + descuento);
                    System.out.printf("Total pagado: $%.0f\n",precioFinal);
                    System.out.println("numero de entrada: " + (contadorID - 1) + "\n");
                    System.out.println(" ");
                    break;
                    
                //ver promociones   
                case 2:
                    System.out.println("\n ---- Descuentos disponibles ---");
                    System.out.println(" ");//solo estetico
                    System.out.println("Estudiantes: 10% de descuento");
                    System.out.println("Tercera edad: 15% de descuento");
                    System.out.println("Estudiante + tercera edad: 25% de descuento");
                    System.out.println("Por compras sobre 3 entradas: 8% de descuento");
                    System.out.println(" ");//solo estetico
                    System.out.println(" -------------------------------- ");
                    break;
                    
                //buscar venta    
                case 3:
                    System.out.println("Ingrese numero de cliente a buscar: ");
                    String entradaBuscarStr = scan.nextLine();
                    boolean encontrado = false;
                    
                    try {
                        int entradaBuscar = Integer.parseInt(entradaBuscarStr);
                        
                        for (int i = 0; i < numEntradas.size(); i++) {
                            if (numEntradas.get(i) == entradaBuscar) {
                                
                                System.out.println("\nVenta encontrada:");
                                System.out.println(" ");//solo estetico
                                System.out.println("Cliente: " + clientes.get(i));
                                System.out.println("Tipo entrada: " + ubicacion.get(i));
                                System.out.printf("Total pagado: $%.0f\n", montos.get(i));
                                System.out.println("Numero de entrada: " + numEntradas.get(i));
                                System.out.println("");
                                encontrado = true;
                                break;
                            }
                        }
                        
                        if (!encontrado) {
                            System.out.println("No se encontraron ventas con ese numero de entrada.\n");
                        }
                        
                    }catch (NumberFormatException e) {
                        System.out.println("Debe ingresar un numero valido.\n");
                    }
                    break;
                    
                //anular venta
                case 4:
                    System.out.println("Ingrese el numero de cliente a anular: ");
                    String entradaEliminarStr = scan.nextLine();
                    boolean seEncontro = false;
                    boolean eliminado = false;
                    
                    try {
                        int entradaEliminar = Integer.parseInt(entradaEliminarStr);

                    for (int i = 0; i < numEntradas.size(); i++) {
                        if (numEntradas.get(i) == entradaEliminar) {
                            seEncontro = true;
                            
                            //informacion compra
                            System.out.println("\nInformacion de la venta a anular:");
                            System.out.println(" ");//solo estetico
                            System.out.println("Cliente: " + clientes.get(i));
                            System.out.println("Tipo entrada: " + ubicacion.get(i));
                            System.out.printf("monto pagado: $%.0f\n", montos.get(i));
                            System.out.println("Numero de entrada: " + numEntradas.get(i));
                            
                            //confirmar anulacion venta
                            System.out.println(" ");//solo estetico
                            System.out.println("Estas seguro que deseas anular esta venta? (s/n): ");
                            String confirmacion = scan.nextLine().trim().toLowerCase();
                            
                            if (confirmacion.equals("s")) {
                            //actualizar totales
                            ingresoTotal -= montos.get(i);
                            totalVentas--;
                            //eliminar datos
                            clientes.remove(i);
                            ubicacion.remove(i);
                            montos.remove(i);
                            numEntradas.remove(i);
                            
                            eliminado = true;
                            System.out.println(" ");//solo estetico
                            System.out.println("Venta anulada con exito\n");
                            }else {
                                 System.out.println(" ");//solo estetico
                                System.out.println("Anulacion cancelada por el usuario");
                            }
                            break;
                        }
                    }
                    
                    if (!seEncontro) {
                         System.out.println(" ");//solo estetico
                        System.out.println("No se encontro ninguna venta con ese numero.\n");
                    }
                    
                  } catch (NumberFormatException e){
                       System.out.println(" ");//solo estetico
                        System.out.println("Debe ingresar un numero valido.\n");
                  }
                    break;
                    
                //estadisticas
                case 5:
                    System.out.println(" --- Estadisticas generales --- ");
                    System.out.println(" ");//solo estetico
                    System.out.println("Total de ventas: " + totalVentas);
                    System.out.println("Ingreso total: $" + ingresoTotal);
                    System.out.println(" ");//solo estetico
                    System.out.println(" ------------------------------ ");
                    break;  
                    
                //salir
                case 6:
                    salir = true;
                    System.out.println(" ");//solo estetico
                    System.out.println("Saliendo del sistema. Gracias por usar *** Teatro Moro app ***");
                    break;
            }   
        }
        scan.close();
    }
}
