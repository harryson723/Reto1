package Modelo;

import Controller.Employed;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Model {

    private String userCorrect = "1";
    private String passCorrect = "1";
    private Conexion conexion;

    public Model() {
        conexion = new Conexion();
    }

    // verificar usuario y contrase;a
    public boolean verifyUserPass(String user, String pass) {

        if (user.isEmpty() && pass.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error ingrese un usuario y contraseña", "Error Inicio de sesion", 0);
        } else if (user.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error ingrese un usuario", "Error Inicio de sesion", 0);
        } else if (pass.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error ingrese una contraseña", "Error Inicio de sesion", 0);
        } else {
            if (user.equals(userCorrect) && pass.equals(passCorrect)) {
                JOptionPane.showMessageDialog(null, "Bienvenido a la aplicacion", "Login", 1);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Error ingrese un usuario y contraseña validos", "Error Inicio de sesion", 0);
            }

        }

        return false;
    }

    public boolean changePass(String pass1, String pass2) {
        if (pass1.isEmpty() || pass2.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error ingrese informacion en ambos campos");
        } else if (pass1.equals(pass2)) {
            passCorrect = pass1;
            JOptionPane.showMessageDialog(null, "Cambio de contraseña exitoso");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Error ingrese contraseñas iguales");
        }
        return false;
    }

    public void createEmployed(Employed employed, ArrayList<Object[]> arreglo, String sucursal) {
        if (employed.getFirtsNames().isEmpty() || employed.getLastNames().isEmpty() || employed.getDocument().isEmpty() || employed.getDocument().isEmpty() || employed.getMail().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese todos los datos", "Error al validar informacion", 2);
        } else {
            // crear un empleado con la informacion dada
            if (insertEmployed(employed, arreglo, sucursal)) {
                JOptionPane.showMessageDialog(null, "Registro Exitoso", "Registro exitoso", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Error con la base de datos", "Error con la base de datos", 0);
            }
        }
    }

    // insertar informacion en la base de datos
    private boolean insertEmployed(Employed employed, ArrayList<Object[]> arreglo, String sucursal) {
        int n = arreglo.size();
        int idSucursal = 0;
        for (int i = 0; i < n; i++) {
            if (arreglo.get(i)[1].equals(sucursal)) {
                idSucursal = Integer.parseInt(arreglo.get(i)[0].toString());
                break;
            }
        }
        try {
            String query = "INSERT INTO empleado (nombreEmp, apellidos, tipoDocumento, documento, correo, FK_idSucursal) "
                    + "VALUES (?,?,?,?,?,?)";
            Connection con = conexion.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, employed.getFirtsNames());
            pst.setString(2, employed.getLastNames());
            pst.setString(3, employed.getDocumentType());
            pst.setString(4, employed.getDocument());
            pst.setString(5, employed.getMail());
            pst.setInt(6, idSucursal);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    // crear la direccion en la base de datos
    public boolean createAddress(String departamento, String zona, String tipoCalle, String numero1, String numero2, String numero3) {
        if (departamento.isEmpty() || zona.isEmpty() || tipoCalle.isEmpty() || numero1.isEmpty() || numero2.isEmpty() || numero3.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error rellene todos los campos");
        } else {
            try {
                String query = "INSERT INTO direccion (zona, tipoCalle, numero1, numero2, numero3, nombreDepartamento) "
                        + "VALUES (?,?,?,?,?,?)";
                Connection con = conexion.getConnection();
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, zona);
                pst.setString(2, tipoCalle);
                pst.setString(3, numero1);
                pst.setString(4, numero2);
                pst.setString(5, numero3);
                pst.setString(6, departamento);
                pst.executeUpdate();
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error interno");
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    public DefaultTableModel updateTableAddress(TableModel modelTable, String info) {
        DefaultTableModel modelTableAux = (DefaultTableModel) modelTable;
        modelTableAux.setRowCount(0);
        String query = "";
        if (info.isEmpty()) {
            query = "SELECT nombreSucursal, nombreDepartamento FROM sucursal INNER JOIN direccion "
                    + "WHERE idDireccion = FK_idDireccion GROUP BY nombreDepartamento, nombreSucursal "
                    + "ORDER BY nombreDepartamento;";
        } else {
            query = "SELECT nombreSucursal, nombreDepartamento FROM sucursal INNER JOIN direccion "
                    + "WHERE (idDireccion = FK_idDireccion) AND (nombreDepartamento LIKE '%" + info + "%') "
                    + "GROUP BY nombreDepartamento, nombreSucursal "
                    + "ORDER BY nombreDepartamento;";
        }
       
        try {
            
            ResultSet rs = conexion.doQuery(query);
            System.out.println(rs);
            while (rs.next()) {
                System.out.println("hola");
                Object[] address = new Object[2];
                address[0] = rs.getString("nombreSucursal");
                address[1] = rs.getString("nombreDepartamento");
                modelTableAux.addRow(address);
            }
          
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return modelTableAux;
    }

    public int getIdAddress(String departamento, String zona, String tipoCalle, String numero1, String numero2, String numero3) {
        try {
            String query = "SELECT idDireccion FROM direccion WHERE zona = ? AND tipoCalle = ? AND numero1 = ? AND numero2 = ? AND numero3 = ? AND nombreDepartamento = ?";
            Connection con = conexion.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, zona);
            pst.setString(2, tipoCalle);
            pst.setString(3, numero1);
            pst.setString(4, numero2);
            pst.setString(5, numero3);
            pst.setString(6, departamento);
            ResultSet rs = pst.executeQuery();
            rs.next();
            return rs.getInt("idDireccion");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public void createSucursal(int idDireccion, String name) {
        try {
            String query = "INSERT INTO sucursal (nombreSucursal, FK_nit, FK_idDireccion) VALUES (?,?,?)";
            Connection con = conexion.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, name);
            pst.setInt(2, 1003516677);
            pst.setInt(3, idDireccion);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sucursal Registrada con exito");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error interno");
        }
    }

    public ArrayList<Object[]> enumSucursal() {
        try {
            String query = "SELECT * FROM sucursal";
            ResultSet rs = conexion.doQuery(query);
            ArrayList<Object[]> arreglo = new ArrayList<>();
            while (rs.next()) {
                Object[] sucursales = new Object[2];
                sucursales[0] = rs.getInt("idSucursal");
                sucursales[1] = rs.getString("nombreSucursal");
                arreglo.add(sucursales);
            }
            System.out.println(arreglo.get(0)[1]);
            return arreglo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error interno");
        }
        return null;
    }

    public TableModel updateTableEmployed(TableModel modelTable, String param) {

        try {
            String query = "";
            if (param.isEmpty()) {
                query = "SELECT * FROM empleado JOIN sucursal WHERE (empleado.FK_idSucursal = sucursal.idSucursal); ";
            } else {
                query = "SELECT * FROM empleado JOIN sucursal WHERE (nombreEmp LIKE '%" + param + "%' or '%" + param + "%') AND (empleado.FK_idSucursal = sucursal.idSucursal)";
            }

            ResultSet rs = conexion.doQuery(query);
            Object[] empleados = new Object[7];
            DefaultTableModel modelTableAux = (DefaultTableModel) modelTable;
            modelTableAux.setRowCount(0);
            while (rs.next()) {
                empleados[0] = rs.getString("idEmp");
                empleados[1] = rs.getString("nombreEmp");
                empleados[2] = rs.getString("apellidos");
                empleados[3] = rs.getString("tipoDocumento");
                empleados[4] = rs.getString("documento");
                empleados[5] = rs.getString("correo");
                empleados[6] = rs.getString("nombreSucursal");

                modelTableAux.addRow(empleados);

            }
            return modelTableAux;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error interno");
        }
        return null;

    }

    public void deleteEmployed(int idEmployed) {
        try {
            String query = "DELETE FROM empleado where idEmp = " + idEmployed;
            conexion.doUpdate(query);
            JOptionPane.showMessageDialog(null, "Eliminado con exito");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error interno");
        }
    }

    public void editEmployed(String fistName, String lastName, String mail, int idEmp) {
        try {
            String query = "UPDATE empleado SET nombreEmp = ?, apellidos = ?, correo = ? WHERE idEmp = ?";
            Connection con = conexion.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, fistName);
            pst.setString(2, lastName);
            pst.setString(3, mail);
            pst.setInt(4, idEmp);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Actualizacion exitosa");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error interno");
        }
    }

}
