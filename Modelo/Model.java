package Modelo;

import Controller.Employed;
import Controller.JobType;
import Controller.Sucursal;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Model {

    private String userCorrect = "MisionTIC";
    private String passCorrect = "Ciclo2";
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

    public void createEmployed(Employed employed, Sucursal sucursal) {
        if (employed.getFirtsNames().isEmpty() || employed.getLastNames().isEmpty() || employed.getDocument().isEmpty() || employed.getDocument().isEmpty() || employed.getMail().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese todos los datos", "Error al validar informacion", 2);
        } else {
            // crear un empleado con la informacion dada
            if (insertEmployed(employed, sucursal)) {
                JOptionPane.showMessageDialog(null, "Registro Exitoso", "Registro exitoso", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Error con la base de datos", "Error con la base de datos", 0);
            }
        }
    }

    // insertar informacion en la base de datos
    private boolean insertEmployed(Employed employed, Sucursal sucursal) {
        
        try {
            String query = "INSERT INTO empleado (nombreEmp, apellidos, tipoDocumento, documento, correo, FK_idSucursal, FK_idPuestoTrabajo) "
                    + "VALUES (?,?,?,?,?,?,?)";
            Connection con = conexion.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, employed.getFirtsNames());
            pst.setString(2, employed.getLastNames());
            pst.setString(3, employed.getDocumentType());
            pst.setString(4, employed.getDocument());
            pst.setString(5, employed.getMail());
            pst.setInt(6, sucursal.getIdSucursal());
            pst.setInt(7, employed.getEmployedType().getIdJobType());
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
            query = "SELECT nombreSucursal, nombreDepartamento, "
                    + "CONCAT('Zona: ', zona, '. ', tipoCalle, ' ', numero1, ' #No', numero2, ' - ', numero3) AS direccionCom "
                    + "FROM sucursal INNER JOIN direccion "
                    + "WHERE idDireccion = FK_idDireccion GROUP BY nombreDepartamento, nombreSucursal "
                    + "ORDER BY nombreDepartamento;";
        } else {
            System.out.println(info);
            query = "SELECT nombreSucursal, nombreDepartamento, "
                    + "CONCAT('Zona: ', zona, '. ', tipoCalle, ' ', numero1, ' #No', numero2, ' - ', numero3) AS direccionCom "
                    + "FROM sucursal INNER JOIN direccion "
                    + "WHERE (idDireccion = FK_idDireccion) AND (nombreDepartamento LIKE '%" + info + "%') "
                    + "GROUP BY nombreDepartamento, nombreSucursal "
                    + "ORDER BY nombreDepartamento;";
        }

        try {

            ResultSet rs = conexion.doQuery(query);
            System.out.println(rs);
            while (rs.next()) {

                Object[] address = new Object[3];
                address[0] = rs.getString("nombreSucursal");
                address[1] = rs.getString("nombreDepartamento");
                address[2] = rs.getString("direccionCom");
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
            if (e.getMessage().equals("Index 0 out of bounds for length 0")) {
                JOptionPane.showMessageDialog(null, "Primero ingrese al menos una sucursal");
            } else {
                JOptionPane.showMessageDialog(null, "Error interno");
            }

        }
        return null;
    }

    public TableModel updateTableEmployed(TableModel modelTable, String param, boolean sucu) {

        try {
            String query = "";
            if (param.isEmpty()) {
                query = "SELECT * FROM empleado INNER JOIN sucursal, puestotrabajo "
                        + "WHERE (sucursal.idSucursal = empleado.FK_idSucursal) "
                        + "AND (empleado.FK_idPuestoTrabajo = puestotrabajo.idPuestoTrabajo)";
            } else if (sucu) {
                query = "SELECT * FROM empleado INNER JOIN sucursal, puestotrabajo "
                        + "WHERE (sucursal.idSucursal = empleado.FK_idSucursal) "
                        + "AND (empleado.FK_idPuestoTrabajo = puestotrabajo.idPuestoTrabajo) "
                        + "AND (sucursal.nombreSucursal = '"+ param + "') ";
            } else {
               query = "SELECT * FROM empleado INNER JOIN sucursal, puestotrabajo "
                        + "WHERE (sucursal.idSucursal = empleado.FK_idSucursal) "
                        + "AND (empleado.FK_idPuestoTrabajo = puestotrabajo.idPuestoTrabajo) "
                        + "AND (empleado.nombreEmp LIKE '%"+ param + "%' "
                        + "OR empleado.apellidos LIKE '%"+ param + "%')";
            }

            ResultSet rs = conexion.doQuery(query);
            Object[] empleados = new Object[7];
            DefaultTableModel modelTableAux = (DefaultTableModel) modelTable;
            modelTableAux.setRowCount(0);
            while (rs.next()) {
                empleados[0] = rs.getString("nombreEmp");
                empleados[1] = rs.getString("apellidos");
                empleados[2] = rs.getString("tipoDocumento");
                empleados[3] = rs.getString("documento");
                empleados[4] = rs.getString("correo");
                empleados[5] = rs.getString("nombreSucursal");
                empleados[6] = rs.getString("nombrePuestoTrabajo");
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

    public int getIdEmployed(String firtsNaame, String lastName, String document, String mail, String sucursal) {
        try {
            String query = "SELECT idEmp FROM empleado WHERE documento = " + document;
            ResultSet rs = conexion.doQuery(query);
            while (rs.next()) {
                return rs.getInt("idEmp");
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int getIdSucursal(String sucursalName, String sucursalDepart) {
        try {
            String query = "SELECT idSucursal FROM sucursal JOIN direccion "
                    + " WHERE (sucursal.FK_idDireccion = direccion.idDireccion) and (nombreSucursal = ?) and (nombreDepartamento = ?)";
            Connection con = conexion.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, sucursalName);
            pst.setString(2, sucursalDepart);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                return rs.getInt("idSucursal");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public String[] getSucursalInfo(int idSucursal) {
        try {
            String[] data = new String[7];
            String query = "SELECT nombreSucursal, nombreDepartamento, zona, tipoCalle, numero1, numero2, "
                    + "numero3 FROM `sucursal` INNER JOIN `direccion` WHERE FK_idDireccion = idDireccion "
                    + "AND idSucursal = " + idSucursal;
            ResultSet rs = conexion.doQuery(query);
            while (rs.next()) {
                data[0] = rs.getString("nombreSucursal");
                data[1] = rs.getString("nombreDepartamento");
                data[2] = rs.getString("zona");
                data[3] = rs.getString("tipoCalle");
                data[4] = rs.getString("numero1");
                data[5] = rs.getString("numero2");
                data[6] = rs.getString("numero3");
            }
            return data;
        } catch (Exception e) {
        }
        return new String[0];
    }

    public void updateSucursalInfo(String sucursal, String numero1, String numero2, String numero3, String calle, String zona, String departamento, int idSucursal) {
        try {
            String query = "UPDATE sucursal SET nombreSucursal = '" + sucursal + "' WHERE idSucursal = " + idSucursal;
            conexion.doUpdate(query);
            query = "SELECT FK_idDireccion FROM sucursal WHERE idSucursal = " + idSucursal;
            ResultSet rs = conexion.doQuery(query);
            int idDireccion = 0;
            while (rs.next()) {
                idDireccion = rs.getInt("FK_idDireccion");
            }
            query = "UPDATE direccion SET numero1 = ?, numero2 = ? , numero3 = ?, zona = ?, tipoCalle = ?, nombreDepartamento = ? WHERE idDireccion = " + idDireccion;
            Connection cn = conexion.getConnection();
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setString(1, numero1);
            pst.setString(2, numero2);
            pst.setString(3, numero3);
            pst.setString(4, zona);
            pst.setString(5, calle);
            pst.setString(6, departamento);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Informacion actualizada");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteSucursal(int idSucursal) {
        try {
            if (JOptionPane.showConfirmDialog(null, "Quiere eliminar la sucursal junto a los empleados y la direccion?") == JOptionPane.YES_OPTION) {
                String query = "SELECT FK_idDireccion FROM sucursal WHERE idSucursal = " + idSucursal;
                ResultSet rs = conexion.doQuery(query);
                int idDireccion = 0;
                while (rs.next()) {
                    idDireccion = rs.getInt("FK_idDireccion");
                }
                query = "DELETE FROM empleado WHERE FK_idSucursal = " + idSucursal;
                conexion.doUpdate(query);
                query = "DELETE FROM sucursal WHERE idSucursal = " + idSucursal;
                conexion.doUpdate(query);
                query = "DELETE FROM direccion WHERE idDireccion = " + idSucursal;
                conexion.doUpdate(query);
                JOptionPane.showMessageDialog(null, "Se elimino la sucursal junto a todos sus empleados");
            }
        } catch (Exception e) {
        }
    }

    public void insertJob(int idSucursal, String sucursal, String puesto, String salario) {

        try {
            String query = "INSERT INTO puestotrabajo (nombrePuestoTrabajo, salario, FK_idSucursal) VALUES (?,?,?)";
            Connection cn = conexion.getConnection();
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setString(1, puesto);
            pst.setFloat(2, Float.valueOf(salario));
            pst.setInt(3, idSucursal);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Puesto ingresado en la base de datos");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<JobType> getComboJobType(Sucursal sucursal) {

        try {
            String query = "SELECT * FROM `puestotrabajo` "
                    + "INNER JOIN sucursal WHERE "
                    + "(sucursal.idSucursal = puestotrabajo.FK_idSucursal) and (sucursal.idSucursal = " + sucursal.getIdSucursal() + ")";
            ResultSet rs = conexion.doQuery(query);
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Ingrese primero un puesto de trabajo en la sucursal");
            } else {
                ArrayList<JobType> arr = new ArrayList<>();
                JobType jobType = new JobType();
                   jobType.setIdJobType(rs.getInt("idPuestoTrabajo"));
                   jobType.setJobType(rs.getString("nombrePuestoTrabajo"));
                   arr.add(jobType);
                while (rs.next()) {
                     jobType = new JobType();
                    jobType.setIdJobType(rs.getInt("idPuestoTrabajo"));
                   jobType.setJobType(rs.getString("nombrePuestoTrabajo"));
                   arr.add(jobType);
                   
                }
                for(int i = 0; i < arr.size(); i++) {
                   System.out.println(arr.get(i).getJobType());
                }
                return arr;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
