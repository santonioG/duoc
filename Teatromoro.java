
package teatromoro;

import java.util.Scanner;

public class Teatromoro {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
       int edad, opcionZona, cantidadEntradas, repetirCompra; 
       double precioZona = 0, descuento = 0, totalFinal = 0; 
       String nombre;
        
        System.out.println(" "); // estetico
        System.out.println("******************************");
        System.out.println("   BIENVENIDO A TEATRO MORO   ");
        System.out.println("******************************");
        System.out.println(" "); // estetico
        
        
        
        for (int i=0; i<1; i++){
            
            System.out.println("Menu principal:");
            System.out.println(" "); // estetico
            
            System.out.println("1. Comprar entrada");
            System.out.println("2. Salir");
            System.out.println(" "); // estetico
            
            System.out.print("Selecciona una opcion: ");
            System.out.println(" "); // estetico
            
            int opcion = scan.nextInt();

            if (opcion == 1){
                System.out.println("Has seleccionado 'Comprar entrada'");
            } else if (opcion == 2) {
                System.out.println(" "); // estetico
                System.out.println("Has salido del programa.");
                break; // Sale del ciclo for
            } else {
                System.out.println("Opción no válida. Intenta de nuevo.");
            }
            
            System.out.println(" "); // estetico
            System.out.println("Ingrese su nombre: ");
            nombre=scan.next();
            
            System.out.println(" "); // estetico
            do {
                System.out.println("Ingrese su edad (6 a 100): ");
                edad = scan.nextInt();
                if (edad <6 || edad > 100){
                    System.out.println("Edad no valida");
                }
            }while(edad<6 || edad> 100);
            
            String[][] teatro = new String[6][6]; // crear columnas para plano teatro zonas 
            for (int fila = 0; fila <6; fila++){
                for (int colum = 0; colum <6; colum++){
                    teatro[fila][colum] = "[ ]";
                }
            }
            
            System.out.println(" "); // estetico
            
            System.out.println("Plano del teatro: ");
            System.out.println(" "); // estetico
            for (int fila = 0; fila< 6; fila++){
                for (int colum =0; colum< 6; colum++){
                    System.out.print(teatro[fila][colum] + " ");
                }
            switch (fila){
                case 1:
                    System.out.println("Zona A");
                    break;
                case 3: 
                    System.out.println("Zona B");
                    break;
                case 5:
                    System.out.println("Zona C");
                    break;
            }   
                System.out.println(); // estetico
            }
            
            do{
                System.out.println("Precio Zonas ");
                System.out.println(" "); // estetico
                
                System.out.println("1. Zona A $50.000");
                System.out.println("2. Zona B $34.000");
                System.out.println("3. Zona C $22.000");
                System.out.println(" "); // estetico
                System.out.println("Selecciona tu zona (1-3)");
                opcionZona = scan.nextInt();
                   
            }while (opcionZona < 1 || opcionZona > 3);
            
            switch(opcionZona){
                case 1:
                    precioZona=50000;
                    System.out.println("Seleccionaste Zona A");
                    break;
                case 2:
                    precioZona=34000;
                    System.out.println("Seleccionaste Zona B");
                    break;
                case 3:
                    precioZona=22000;
                    System.out.println("Seleccionaste Zona C");
                    break;
                default: 
                    System.out.println("Error");
            }
            
            do{
                System.out.println("Cuantas entradas desea? (1-5)");
                cantidadEntradas = scan.nextInt();
            }while(cantidadEntradas < 1 || cantidadEntradas > 10);
            
            if (edad < 18){
                descuento = 0.15;
            }else if(edad>=60){ 
                descuento = 0.3;
            }else{
                descuento = 0;
            }
            
           double totalParcial = (precioZona*cantidadEntradas);
           totalParcial = totalParcial - (totalParcial*descuento);
           
                   System.out.println(" "); // estetico
        System.out.println("******************************");
        System.out.println("  RESUMEN DE LA COMPRA   ");
        System.out.println("******************************");
        System.out.println(" "); // estetico
           
            System.out.println("Nombre: "+nombre);
            System.out.println("Entradas: " + cantidadEntradas + "x $" + precioZona);
            System.out.println("Descuento aplicado: " + (descuento*100) + "%");
            System.out.println("Total a pagar: $" + totalParcial);
            
                    System.out.println(" "); // estetico
        System.out.println("******************************");
        System.out.println("   RESUMEN DE LA COMPRA   ");
        System.out.println("******************************");
        System.out.println(" "); // estetico
            
            totalFinal = totalFinal + totalParcial;
            
            do{
                System.out.println("Desea realizar el pago? 1: si 2: no");
                repetirCompra = scan.nextInt();
                if (repetirCompra !=1 && repetirCompra !=2){
                    System.out.println("Opcion invalida");
                }
             
            }while((repetirCompra !=1 && repetirCompra !=2));
            if (repetirCompra ==1){
                
                System.out.println(" "); // estetico
                System.out.println("*** Compra realizada con exito ***");
                System.out.println(" "); // estetico
                System.out.println("Total a pagar: $" + totalFinal);
            }else{
                System.out.println(" "); // estetico
                System.out.println("Compra cancelada :( ");
            }

            System.out.println(" "); // estetico
            System.out.println("*** GRACIAS POR PREFERIR TEATRO MORO ***");
           
        }
         scan.close();
    }
    
}
