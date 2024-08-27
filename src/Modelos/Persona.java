package Modelos;

public class Persona {
    String nombre;
    String apellido;

    public Persona(String nombre,String apellido){
        this.nombre= nombre;
        this.apellido= apellido;
    }

    public String getNombre(){
        return nombre;
    }

    public String getApellido(){
        return apellido;
    }
    
}
