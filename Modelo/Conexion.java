package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    Connection connection;
    
    String url = "jdbc:mysql://localhost:3306/";
    String db = "programacionmisiontic";
    String user = "root";
    String password = "root";
    public Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url + db, user, password);
            if (connection != null) {
                System.out.println("Connected" + connection);

            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "Error Base de Datos", 0);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet doQuery(String query) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url + db, user, password);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs;
        } catch (Exception e) {
        }
        return null;
    }
    
    public void doUpdate(String query) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url + db, user, password);
             Statement st = connection.createStatement();
             st.executeUpdate(query);
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }

}
