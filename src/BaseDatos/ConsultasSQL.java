package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Modelos.Entrenador;
import Modelos.Equipo;
import Modelos.Jugador;

public class ConsultasSQL {

    public ArrayList<Equipo> consultarEquipos(Connection conexion){
        String sql= "SELECT id,nombre,ciudad FROM equipo";
        ArrayList<Equipo> equipos= new ArrayList<>();
        try {PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet resultado= statement.executeQuery();

                while (resultado.next()) {
                    int id= resultado.getInt("Id");
                    String nombre= resultado.getString("nombre");
                    String ciudad= resultado.getString("Ciudad");

                    Equipo equipo= new Equipo(id, nombre, ciudad);
                    equipos.add(equipo);     
                }
            
        } catch (Exception e) {
            System.out.println("Se ha producido un error al consultar los equipos en la base de datos");
        }
        return equipos;

    }

    public void insertarEquipos(Connection conexion, Equipo equipo){
        String sql= "INSERT INTO equipo(id, nombre, ciudad) VALUES (?,?,?)";
        try {PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, equipo.getId());
            statement.setString(2, equipo.getNombre());
            statement.setString(3, equipo.getCiudad());

        int filasInsertadas = statement.executeUpdate();
        if (filasInsertadas > 0) {
            System.out.println("Equipo insertado correctamente.");
        }
            
        } catch (Exception e) {
            System.out.println("Se ha producido un error al insertar el equipo en la base de datos");
        }
    }

    public void eliminarEquipos(Connection conexion, Integer id){
        String sql= "DELETE FROM equipo WHERE id= ?";
        try {PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, id);

        int filasEliminadas = statement.executeUpdate();
        if (filasEliminadas > 0) {
            System.out.println("Equipo dado de baja correctamente.");
        }
            
        } catch (Exception e) {
            System.out.println("Se ha producido un error al eliminar el equipo en la base de datos");
        }
    }

    public ArrayList<Entrenador> consultarEntrenadores(Connection conexion){
        String sql= "SELECT id,nombre,apellido, anioLicencia, idEquipo FROM entrenador";
        ArrayList<Entrenador> entrenadores= new ArrayList<>();
        try {PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet resultado= statement.executeQuery();

                while (resultado.next()) {
                    String nombre= resultado.getString("nombre");
                    String apellido= resultado.getString("Apellido");
                    int anioLicencia= resultado.getInt("anioLicencia");
                    int idEquipo= resultado.getInt("idEquipo");

                    Entrenador entrenador= new Entrenador(nombre,apellido,anioLicencia,idEquipo);
                    entrenadores.add(entrenador);     
                }
            
        } catch (Exception e) {
            System.out.println("Se ha producido un error al consultar los entrenadores en la base de datos");
        }
        return entrenadores;

    }

    public void insertarEntrenador(Connection conexion, Entrenador entrenador){
        String sql= "INSERT INTO entrenador(nombre, apellido, anioLicencia, idEquipo) VALUES (?,?,?,?)";
        try {PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, entrenador.getNombre());
            statement.setString(2, entrenador.getApellido());
            statement.setInt(3, entrenador.getAnioLicencia());
            statement.setInt(4, entrenador.getIdEquipo());

        int filasInsertadas = statement.executeUpdate();
        if (filasInsertadas > 0) {
            System.out.println("Entrenador insertado correctamente.");
        }
            
        } catch (Exception e) {
            System.out.println("Se ha producido un error al insertar el entrenador en la base de datos");
        }
    }

    public ArrayList<Jugador> consultarJugadores(Connection conexion){
        String sql= "SELECT id,nombre,apellido, altura, peso, dorsal, idEquipo FROM jugador";
        ArrayList<Jugador> jugadores= new ArrayList<>();
        try {PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet resultado= statement.executeQuery();

                while (resultado.next()) {
                    String nombre= resultado.getString("nombre");
                    String apellido= resultado.getString("apellido");
                    Float altura= resultado.getFloat("altura");
                    Float peso= resultado.getFloat("peso");
                    int dorsal= resultado.getInt("dorsal");
                    int idEquipo= resultado.getInt("idEquipo");

                    Jugador jugador= new Jugador(nombre,apellido,altura,peso,dorsal,idEquipo);
                    jugadores.add(jugador);     
                }
            
        } catch (Exception e) {
            System.out.println("Se ha producido un error al consultar los entrenadores en la base de datos");
        }
        return jugadores;

    }

    public void insertarJugadores(Connection conexion, ArrayList<Jugador> plantilla){
        Boolean insertado= false;
        for(Jugador jugador: plantilla){
            String sql= "INSERT INTO jugador(nombre, apellido, altura, peso, dorsal, idEquipo) VALUES (?,?,?,?,?,?)";
            try {PreparedStatement statement = conexion.prepareStatement(sql);
                statement.setString(1, jugador.getNombre());
                statement.setString(2, jugador.getApellido());
                statement.setFloat(3, jugador.getAltura());
                statement.setFloat(4, jugador.getPeso());
                statement.setInt(5, jugador.getDorsal());
                statement.setInt(6, jugador.getIdEquipo());

            int filasInsertadas = statement.executeUpdate();
            if (filasInsertadas > 0) {
                insertado= true;
            }
                
            } catch (Exception e) {
                System.out.println("Se ha producido un error al insertar jugador en la base de datos");
            }
        }
        if(insertado){
            System.out.println("Plantilla insertada correctamente.");
        }
    }

    public void eliminarEntrenador(Connection conexion, Integer idEquipo){
        String sql= "DELETE FROM entrenador WHERE idEquipo= ?";
        try {PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, idEquipo);

        int filasInsertadas = statement.executeUpdate();
        if (filasInsertadas > 0) {
            System.out.println("Entrenador dado de baja correctamente.");
        }
            
        } catch (Exception e) {
            System.out.println("Se ha producido un error al dar de baja al entrenador en la base de datos");
        }
    }

    public void eliminarJugadores(Connection conexion, Integer idEquipo){
            String sql= "DELETE FROM jugador WHERE idEquipo= ?";
            try {PreparedStatement statement = conexion.prepareStatement(sql);
                statement.setInt(1, idEquipo);

                int filasInsertadas = statement.executeUpdate();
                if (filasInsertadas > 0) {
                    System.out.println("Plantilla dada de baja correctamente.");
                }
                
            } catch (Exception e) {
                System.out.println("Se ha producido un error al dar de baja la plantilla en la base de datos");
            }
        }
    
        public void modificarNombreEquipo(Connection conexion, String nombre, Integer idEquipo){
            String sql= "UPDATE equipo SET nombre= ? WHERE id= ?";
            try {
                PreparedStatement statement= conexion.prepareStatement(sql);
                statement.setString(1, nombre);
                statement.setInt(2, idEquipo);
                int filasInsertadas = statement.executeUpdate();
                if (filasInsertadas > 0) {
                    System.out.println("Nombre modificado correctamente.");
                }
            } catch (Exception e) {
                System.out.println("Se ha producido un error al modificar el nombre del equipo en la base de datos");
            }
        }

        public ArrayList<Jugador> sacarPlantilla(Connection conexion, Integer idEquipo){
            ArrayList<Jugador> plantilla= new ArrayList<>();
            String sql= "Select nombre, apellido, altura, peso, dorsal, idEquipo From jugador where idEquipo= ?";
            try {
                PreparedStatement statement= conexion.prepareStatement(sql);
                statement.setInt(1, idEquipo);
                ResultSet resultado= statement.executeQuery();
                
                while (resultado.next()) {
                    String nombre= resultado.getString("nombre");
                    String apellido= resultado.getString("apellido");
                    Float altura= resultado.getFloat("altura");
                    Float peso= resultado.getFloat("peso");
                    int dorsal= resultado.getInt("dorsal");
                    int idTeam= resultado.getInt("idEquipo");

                    Jugador jugador= new Jugador(nombre,apellido,altura,peso,dorsal,idTeam);
                    plantilla.add(jugador);     
                }
            } catch (Exception e) {
                System.out.println("Se ha producido un error al consultar la plantilla del equipo en la base de datos");
            }
            return plantilla;
        }


}

