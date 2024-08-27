package Utilidades;

import java.util.Scanner;

public class Utilidades {
    static Scanner scanner= new Scanner(System.in);

    // Metodo que permite leer cadenas de caracteres
    public static String leerString(String dato){
        System.out.println(dato);
        String respuesta= scanner.nextLine();
        return respuesta;
    }

    // Metodo que permite leer números enteros
    public static Integer leerInteger(String dato){
        while (true) {
            try {
                System.out.println(dato);
                String respuesta= scanner.nextLine();
                Integer respInt= Integer.parseInt(respuesta);
                return respInt;
                
            } catch (Exception e) {
                System.out.println("Introduce un valor válido.");
            }
            
        }
    }

    // Metodo que permite leer números con decimales
    public static Float leerFloat(String dato){
        while (true) {
            try {
                System.out.println(dato);
                String respuesta= scanner.nextLine();
                Float respFloat= Float.parseFloat(respuesta);
                return respFloat;
                
            } catch (Exception e) {
                System.out.println("Introduce un valor válido.");
            }
            
        }
    }
    
}
