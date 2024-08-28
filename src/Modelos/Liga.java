package Modelos;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import BaseDatos.Conexion;
import BaseDatos.ConsultasSQL;
import Utilidades.Utilidades;

public class Liga {
    Conexion conn;
    Connection conexion;
    ConsultasSQL consulta;
    ArrayList<Equipo> equipos;
    ArrayList<Entrenador> entrenadores;
    ArrayList<Jugador> jugadores;

    public Liga(Connection conexion){
        this.conexion=conexion;
        conn= new Conexion();
        consulta= new ConsultasSQL();
        equipos= new ArrayList<>();
        entrenadores= new ArrayList<>();
        jugadores= new ArrayList<>();
        
    }

    public void cerrarConexion(){
        conn.cerrarConexionDB(conexion);
    }

    public void verEquipos(){
        equipos= consulta.consultarEquipos(this.conexion);
        if (equipos.isEmpty()){
            System.out.println("No hay equipos registrados");
        }else{
            for(Equipo equipo:equipos){
                System.out.println("ID: "+ equipo.getId()+ " Nombre: "+ equipo.getNombre()+ " Ciudad: "+ equipo.getCiudad()+ " Puntos: "+ equipo.getPuntos());
            }
        }
    }

    public void verEntrenadores(){
        entrenadores= consulta.consultarEntrenadores(conexion);
        if (entrenadores.isEmpty()){
            System.out.println("No hay entrenadores registrados");
        }else{
            for(Entrenador entrenador:entrenadores){
                System.out.println("Nombre: "+ entrenador.getNombre()+ " Apellido: "+ entrenador.getApellido()+ " Año Licencia: "+ entrenador.getAnioLicencia()+ " Id equipo: "+ entrenador.getIdEquipo());
            }
        }
    }

    public void verJugadores(){
        jugadores= consulta.consultarJugadores(conexion);
        if (jugadores.isEmpty()){
            System.out.println("No hay jugadores registrados");
        }else{
            for(Jugador jugador:jugadores){
                System.out.println("Nombre: "+ jugador.getNombre()+ 
                " Apellido: "+ jugador.getApellido()+ 
                " Altura "+ jugador.getAltura()+ 
                " Peso: "+ jugador.getPeso()+ 
                " Dorsal: "+ jugador.getDorsal()+ 
                " Id del equipo: "+ jugador.getIdEquipo());
            }
        }
    }

    public void altaEquipo(){
        System.out.println("-------- ALTA DE EQUIPO ---------");
        equipos= consulta.consultarEquipos(this.conexion);
        Integer id= Utilidades.leerInteger("Introduce el id del equipo: ");
        String nombre= Utilidades.leerString("Introduce el nombre del equipo: ");
        String ciudad= Utilidades.leerString("Introduce la ciudad del equipo: ");

        
        Equipo equipo= new Equipo(id, nombre, ciudad);
        Boolean equipoExiste= false;

        for(Equipo team:equipos){
            if(equipo.getId().equals(team.getId())){
                System.out.println("Lo siento, ya existe un equipo con ese id.");
                equipoExiste= true;
                break;
            }
        }

        if(!equipoExiste){
            consulta.insertarEquipos(this.conexion, equipo);
            Entrenador entrenador= equipo.altaEntrenador();
            consulta.insertarEntrenador(conexion, entrenador);
            ArrayList<Jugador> plantilla= equipo.altaPlantilla();
            consulta.insertarJugadores(conexion, plantilla);
        }
    }

    public void bajaEquipo(){
        System.out.println("-------- BAJA EQUIPO ---------");
        equipos= consulta.consultarEquipos(this.conexion);
        Boolean equipoExiste= false;
        Integer idEquipo= Utilidades.leerInteger("Introduce el id del equipo: ");
        for(Equipo team:equipos){
            if(team.getId().equals(idEquipo)){
                equipoExiste=true;
            }
        }

        if(equipoExiste.equals(false)){
            System.out.println("Lo siento, no existe ningun equipo con ese id.");
        }else{
            consulta.eliminarEntrenador(this.conexion, idEquipo);
            consulta.eliminarJugadores(this.conexion, idEquipo);
            consulta.eliminarEquipos(this.conexion, idEquipo);
        }

    }

    public void verEntrenadoresLicencia(){
        System.out.println("-------- ENTRENADORES ORDENADOS POR AÑO LICENCIA ---------");
        entrenadores= consulta.consultarEntrenadores(this.conexion);

        Collections.sort(entrenadores, new Comparator<Entrenador>() {

            @Override
            public int compare(Entrenador entrenador1, Entrenador entrenador2) {
                return entrenador1.getAnioLicencia().compareTo(entrenador2.getAnioLicencia());
            }
            
        });

        for(Entrenador entrenador: entrenadores){
            System.out.println("Nombre: "+ entrenador.getNombre() + " Apellido: "+ entrenador.getApellido()+ " Año Licencia: "+ entrenador.getAnioLicencia()+ " Id equipo: "+ entrenador.getIdEquipo());
        }
    }

    public void modificarNombre(){
        System.out.println("-------- MODIFICAR NOMBRE EQUIPO ---------");
        equipos= consulta.consultarEquipos(this.conexion);
        Boolean equipoExiste= false;
        Integer idEquipo= Utilidades.leerInteger("Introduce el id del equipo que quieres modificar: ");
        for(Equipo team:equipos){
            if(team.getId().equals(idEquipo)){
                equipoExiste=true;
            }
        }

        if(equipoExiste){
            String nombreNuevo= Utilidades.leerString("Introduce el nuevo nombre del equipo: ");
            consulta.modificarNombreEquipo(conexion, nombreNuevo, idEquipo);
        }else{
            System.out.println("Lo siento, no hay ningún equipo registrado con ese id");
        }

    }

    private Jugador jugadorAlto(ArrayList<Jugador>plantilla){
        Jugador alto;
        if(plantilla.isEmpty()){
            alto=null;
        }else{
            alto=plantilla.get(0);
            for(Jugador player:plantilla){
                if(player.getAltura()>alto.getAltura()){
                    alto=player;
                }
            }
        }
        return alto;
    }

    public void jugadoresAltos() {
        System.out.println("-------- JUGADOR MAS ALTO DE CADA EQUIPO ---------");
        equipos = consulta.consultarEquipos(this.conexion);
        ArrayList<Jugador> jugadoresAltos = new ArrayList<>();
    
        for (Equipo equipo : equipos) {
            Integer idEquipo = equipo.getId();
            ArrayList<Jugador> plantilla = consulta.sacarPlantilla(this.conexion, idEquipo);
    
            if (!plantilla.isEmpty()) {
                Jugador alto = jugadorAlto(plantilla);
                jugadoresAltos.add(alto);
            }
        }
    
        // Mostrar los jugadores más altos
        for (Jugador player : jugadoresAltos) {
            System.out.println("Nombre: " + player.getNombre() + " " + player.getApellido() + 
                               " Altura: " + player.getAltura() + " IdEquipo: " + player.getIdEquipo());
        }
    }

    public void verEquipo(){
        System.out.println("-------- VER EQUIPO COMPLETO ---------");
        equipos= consulta.consultarEquipos(this.conexion);
        Boolean equipoExiste= false;
        Equipo equipo= null;
        Integer idEquipo= Utilidades.leerInteger("Introduce el id del equipo: ");
        for(Equipo team:equipos){
            if(team.getId().equals(idEquipo)){
                equipoExiste=true;
                equipo=team;
            }
        }

        if(equipoExiste.equals(false)){
            System.out.println("Lo siento, no existe ningun equipo con ese id.");
        }else{
            System.out.println("NOMBRE EQUIIPO: "+ equipo.getNombre()+ " CIUDAD: "+ equipo.getCiudad()+ " PUNTOS: "+ equipo.getPuntos()); 
            Entrenador entrenador= consulta.consultarEntrenador(conexion, equipo.getId());
            System.out.println("    ENTRENADOR: "+ entrenador.getNombre()+" "+ entrenador.getApellido()+ " AÑO DE LICENCIA: "+ entrenador.getAnioLicencia());
            ArrayList<Jugador> plantilla= consulta.sacarPlantilla(conexion, equipo.getId());
            if(plantilla.isEmpty()){
                System.out.println("ESTE EQUIPO NO TIENE JUGADORES REGISTRADOS");
            }else{
                int contador=0;
                for(Jugador jugador:plantilla){
                    contador++;
                    System.out.println("        "+contador+"-NOMBRE: "+ jugador.getNombre()+ " "+ jugador.getApellido()+ " ALTURA: "+ jugador.getAltura()+ " PESO: "+ jugador.getPeso()+ " DORSAL: "+ jugador.getDorsal());
                }
            }
        }

    }

    public void verClasificacion(){
        System.out.println("-------- CLASIFICACIÓN ---------");
        equipos=consulta.consultarEquipos(this.conexion);
        Collections.sort(equipos, new Comparator<Equipo>() {

            @Override
            public int compare(Equipo e1, Equipo e2) {
                return e2.getPuntos().compareTo(e1.getPuntos());
            }
            
        });

        int contador=0;
        for(Equipo equipo:equipos){
            contador++;
            System.out.println(contador+"º "+ equipo.getNombre()+ " PUNTOS: "+ equipo.getPuntos());
        }
    }

    public void jugarPartido(){
        Integer idEquipo1= Utilidades.leerInteger("Introduce el id del equipo 1: ");
        Integer idEquipo2= Utilidades.leerInteger("Introduce el id del equipo 2: ");
        Equipo equipo1= consulta.consultarEquipo(conexion, idEquipo1);
        Equipo equipo2= consulta.consultarEquipo(conexion, idEquipo2);

        if(equipo1==null || equipo2==null){
            System.out.println("Se ha producido un error al jugar el partido, los id introducidos no corresponden con los equipos registrados");
        }else{
            Boolean partidoFinalizado= false;
            while (!partidoFinalizado) {
                System.out.println("OPCIONES:");
                System.out.println("1- Equipo: "+ equipo1.getNombre()+ " GANA");
                System.out.println("2- Equipo: "+ equipo1.getNombre()+ " Equipo: "+ equipo2.getNombre()+ " EMPATAN");
                System.out.println("3- Equipo: "+ equipo2.getNombre()+ " GANA");
                String ganador= Utilidades.leerString("Introduce una opcion: ");
                switch (ganador) {
                    case "1":
                        equipo1.sumarPuntos(3);
                        consulta.sumarPuntos(conexion, equipo1.getId(),equipo1.getPuntos());
                        System.out.println("Partido finalizado: se han sumado 3 puntos al equipo: "+ equipo1.getNombre());
                        partidoFinalizado=true;
                        break;
                    case "2":
                        equipo1.sumarPuntos(1);
                        consulta.sumarPuntos(conexion, equipo1.getId(),equipo1.getPuntos());
                        equipo2.sumarPuntos(1);
                        consulta.sumarPuntos(conexion, equipo2.getId(),equipo2.getPuntos());
                        System.out.println("Partido finalizado: se ha sumado 1 puntos al equipo: "+ equipo1.getNombre()+ " y al equipo: "+equipo2.getNombre());
                        partidoFinalizado=true;
                        break;
                    case "3":
                        equipo2.sumarPuntos(3);
                        consulta.sumarPuntos(conexion, equipo2.getId(),equipo2.getPuntos());
                        System.out.println("Partido finalizado: se han sumado 3 puntos al equipo: "+ equipo2.getNombre());
                        partidoFinalizado=true;
                        break;
                
                    default:
                    System.out.println("Escoge una opción válida");
                        break;
                }
                
            }
        }
    }

}
