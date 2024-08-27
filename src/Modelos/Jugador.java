package Modelos;

public class Jugador extends Persona{
    Float altura;
    Float peso;
    Integer dorsal;
    Integer idEquipo;

    public Jugador(String nombre, String apellido,Float altura, Float peso, Integer dorsal, Integer idEquipo) {
        super(nombre, apellido);
        this.altura= altura;
        this.peso=peso;
        this.dorsal= dorsal;
        this.idEquipo= idEquipo;
    }

    public Float getAltura() {
        return this.altura;
    }

    public Float getPeso() {
        return this.peso;
    }

    public Integer getDorsal() {
        return this.dorsal;
    }

    public Integer getIdEquipo() {
        return this.idEquipo;
    }

    
    
}
