package com.oregon.mensajes;

//api de SQL
import java.sql.*;

public class Main {

    /* Para realizar la conexoon, debo utilizar el CONNECTION y pongo un nombre x = drivermanager.getconcection
        en los parametros va la URL, que sería "jdbc:mysql://localhost/mensajes_bd?serverTimezone=UTC"
        ese localhost es porque el host está en nuestro pc/mensajes_bd es el nombre de nuestra base de datos
        ese ?serverTimezone=UTC es para obtener la zona hoaria actual, debe ir siempre para que no arroje error 
        USER: es root (así aparece en mysql) y el password es el que ponemos al inicio cuando creamos la instalación de mysql
     */
    public static void main(String[] args) throws SQLException {
        //llamamos la función
        
        listarRegistros();

        
    }
    
    static void listarRegistros() throws SQLException{
        Connection conectar = DriverManager.getConnection(
                "jdbc:mysql://localhost/mensajes_bd?serverTimezone=UTC",
                "root",
                "seba12345OTEIZA");

        //mando a imprimir este mensaje para saber si la conexion se realizó con éxito
        System.out.println("Conexion correcta");

        //recuperar
        String sql = "SELECT  * FROM mensajes"; //recupero todos los registros de la tabla (mensajes) no es necesario incluir el nombre de la DB 
        PreparedStatement ps = conectar.prepareStatement(sql); //realizo la consulta, preparo la consulta
        ResultSet rs = ps.executeQuery(); //recupero el resultado de la consulta

        //para iterar
        while (rs.next()) {
            int id = rs.getInt("id_mensaje");//esto tiene que ser igual que los campos que tiene la DB
            String mensaje = rs.getString("mensaje");
            String autor = rs.getString("autor");
            String fecha = rs.getString("fecha");
            
            //para mostrar los resultados por pantalla
            
            System.out.printf("%d %s %s %s\n", id, mensaje, autor, fecha);
        }

        //cerrar las conexiones
        rs.close();
        ps.close();
        conectar.close();
        
    }

}
