package BaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class Conexion {

    public Connection getConexion(){

        Connection conexion = null;

        String url= "jdbc:mysql://localhost:3306/liga";
        String usuario= "root";
        String password= "rembombory";

        
        try {
            conexion= DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexión establecida con éxito");
            return conexion;
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }

        return conexion;
    }

    public void cerrarConexionDB(Connection conexion){
        if(conexion != null){
            try {
                conexion.close();
                System.out.println("Conexión cerrada con éxito");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: "+ e.getMessage());
            }
        }
    }
    
}
