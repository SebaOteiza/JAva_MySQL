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

        //INSERTAMOS nuevos registro
        insertarRegistros("Hola desde JAVA, ESTOY LOCO", "Sebastían");
        //listarRegistros();
        //editar registro
        //editarRegistros("Poder conectar la db con java me tiene muy feliz", "Sebastián", 3);
        //eliminar registro
        eliminarRegistros(1);
        listarRegistros();

    }

    //listar registros
    static void listarRegistros() throws SQLException {
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

    //insertar registros
    static void insertarRegistros(String mensaje, String autor) throws SQLException {
        Connection conectar = DriverManager.getConnection(
                "jdbc:mysql://localhost/mensajes_bd?serverTimezone=UTC",
                "root",
                "seba12345OTEIZA");

        //mando a imprimir este mensaje para saber si la conexion se realizó con éxito
        System.out.println("Conexion correcta");

        //recuperar
        String sql = "INSERT INTO mensajes(mensaje, autor, fecha) VALUE(?,?, current_time())"; //
        PreparedStatement ps = conectar.prepareStatement(sql); //realizo la consulta, preparo la consulta
        ps.setString(1, mensaje);//ese 1 corresponde al primer ? que está en VALUE
        ps.setString(2, autor);//ese 2 cprresopnde al segundo ? que está en VALUE
        ps.executeUpdate(); //ejecutar la actualización, la insersión. Lo que hace es ejecutar e insertar el dato para actualizar la tabla

        //cerrar las conexiones
        ps.close();
        conectar.close();

    }

    static void editarRegistros(String mensaje, String autor, int id) throws SQLException {
        Connection conectar = DriverManager.getConnection(
                "jdbc:mysql://localhost/mensajes_bd?serverTimezone=UTC",
                "root",
                "seba12345OTEIZA");

        //mando a imprimir este mensaje para saber si la conexion se realizó con éxito
        System.out.println("Conexion correcta");

        //recuperar
        String sql = "UPDATE mensajes SET mensaje=?, autor=? WHERE id_mensaje = ?"; //
        PreparedStatement ps = conectar.prepareStatement(sql); //realizo la consulta, preparo la consulta
        ps.setString(1, mensaje);//ese 1 corresponde al primer ? que está en VALUE
        ps.setString(2, autor);//ese 2 cprresopnde al segundo ? que está en VALUE
        ps.setInt(3, id);
        ps.executeUpdate(); //ejecutar la actualización, la insersión. Lo que hace es ejecutar e insertar el dato para actualizar la tabla

        //cerrar las conexiones
        ps.close();
        conectar.close();

    }

    static void eliminarRegistros(int id) throws SQLException {
        Connection conectar = DriverManager.getConnection(
                "jdbc:mysql://localhost/mensajes_bd?serverTimezone=UTC",
                "root",
                "seba12345OTEIZA");

        //mando a imprimir este mensaje para saber si la conexion se realizó con éxito
        System.out.println("Conexion correcta");

        //recuperar
        String sql = "DELETE FROM mensajes WHERE id_mensajes = ? "; //
        PreparedStatement ps = conectar.prepareStatement(sql); //realizo la consulta, preparo la consulta

        ps.setInt(1, id);
        ps.executeUpdate(); 

        //cerrar las conexiones
        ps.close();
        conectar.close();

    }
}
