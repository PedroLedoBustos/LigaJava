package Modelos;

import java.util.ArrayList;

import Utilidades.Utilidades;

public class Equipo {
    private Integer id;
    private String nombre;
    private String ciudad;


    public Equipo(Integer id, String nombre, String ciudad){
        this.id=id;
        this.nombre=nombre;
        this.ciudad= ciudad;
    }


    public Integer getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public String getCiudad(){
        return ciudad;
    }

    public Entrenador altaEntrenador(){
        String nombre= Utilidades.leerString("Introduce el nombre del entrenador: ");
        String apellido= Utilidades.leerString("Introduce el apellido del entrenador: ");
        Integer anioLicencia= Utilidades.leerInteger("Introduce el año de licencia del entrenador: ");

        Entrenador entrenador= new Entrenador(nombre, apellido, anioLicencia, this.id);
        return entrenador;
    }

    private Jugador altaJugador(){
        String nombre= Utilidades.leerString("Introduce el nombre del jugador: ");
        String apellido= Utilidades.leerString("Introduce el apellido del jugador: ");
        Float altura= Utilidades.leerFloat("Introduce la altura del jugador: ");
        Float peso= Utilidades.leerFloat("Introduce el peso del jugador: ");
        Integer dorsal= Utilidades.leerInteger("Introduce el dorsal del jugador: ");

        Jugador jugador= new Jugador(nombre, apellido,altura,peso,dorsal,this.id);
        return jugador;
    }

    public ArrayList<Jugador> altaPlantilla(){
        ArrayList<Jugador> plantilla= new ArrayList<>();
        Integer numeroJugadores= Utilidades.leerInteger("Introduce el número de jugadores que quieres que tenga la plantilla: ");
        Boolean jugadorExiste= false;
        while(plantilla.size()<numeroJugadores){
            Jugador jugador= altaJugador();
            if(plantilla.isEmpty()){
                plantilla.add(jugador);
            }else{
                for(Jugador player: plantilla){
                    if(jugador.getDorsal().equals(player.getDorsal())){
                        System.out.println("Lo siento, ya existe un jugador con el dorsal: "+ jugador.getDorsal());
                        jugadorExiste= true;
                    }
                }
                if(jugadorExiste.equals(false)){
                    plantilla.add(jugador);
                }
            }
        }
        return plantilla;
    }

    
}
