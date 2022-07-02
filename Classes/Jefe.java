
package Classes;


public class Jefe  extends Empleado{
    private int idJefe;

    public Jefe(double salario, String cargo, boolean afiliadoArl,  String nombre, String apellidos, String tipoDocumento, String documento, String email) {
        super( salario, cargo, afiliadoArl,  nombre, apellidos, tipoDocumento, documento, email);
        this.idJefe++;
    }

    public int getIdJefe() {
        return idJefe;
    }

    public void setIdJefe(int idJefe) {
        this.idJefe = idJefe;
    }
    

    @Override
    public String toString() {
        return "Jefe{" + "idJefe=" + idJefe + '}';
    }
    
}
