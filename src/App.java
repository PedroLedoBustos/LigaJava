import BaseDatos.Conexion;
import Modelos.Liga;
import Utilidades.Utilidades;

public class App {
    static Conexion conexion= new Conexion();
    static Liga liga= new Liga(conexion.getConexion());

    public static void main(String[] args) throws Exception {
        Boolean salir= false;
        while (!salir) {
            salir= menu();
        }
        
    }

    private static Boolean menu(){
        Boolean salir= false;
        System.out.println("###############################");
        System.out.println("####### MENU PRINCIPAL ########");
        System.out.println("###############################");
        System.out.println("1 Alta equipo");
        System.out.println("2 Baja equipo");
        System.out.println("3 Ver equipos");
        System.out.println("4 Ver jugadores");
        System.out.println("5 Ver entrenadores");
        System.out.println("6 Ver entrenador por año de licencia equipo");
        System.out.println("7 Ver jugadores mas altos de cada equipo");
        System.out.println("8 Modificar nombre del equipo");
        System.out.println("9 Ver equipo completo");
        System.out.println("10 Jugar partido");
        System.out.println("11 Ver clasificación");

        System.out.println("0 Salir");

        String opcion= Utilidades.leerString("Opcion: ");

        switch (opcion) {
            case "1":
                liga.altaEquipo();
                break;
            case "2":
                liga.bajaEquipo();
                break;
            case "3":
                liga.verEquipos();
                break;
            case "4":
                liga.verJugadores();
                break;
            case "5":
                liga.verEntrenadores();
                break;
            case "6":
                liga.verEntrenadoresLicencia();
                break;
            case "7":
                liga.jugadoresAltos();
                break;
            case "8":
                liga.modificarNombre();
                break;
            case "9":
                System.out.println("Ver equipo completo");
                liga.verEquipo();
                break;
            case "10":
                liga.jugarPartido();
                break;
            case "11":
                liga.verClasificacion();
                break;
            case "0":
                System.out.println("Saliendo...");
                liga.cerrarConexion();
                salir=true;
                break;
        
            default:
                System.out.println("Escoge una opción válida");
                break;
        }
        return salir;

    }
}
