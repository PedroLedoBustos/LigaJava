package Modelos;

public class Entrenador extends Persona {
    private Integer anioLiciencia;
    private Integer idEquipo;

    public Entrenador(String nombre, String apellido, Integer anioLicencia, Integer idEquipo) {
        super(nombre, apellido);
        this.anioLiciencia= anioLicencia;
        this.idEquipo= idEquipo;
    }

    public String getNombre(){
        return this.nombre;
    }

    public Integer getAnioLicencia(){
        return anioLiciencia;
    }

    public Integer getIdEquipo(){
        return idEquipo;
    }
    
}
