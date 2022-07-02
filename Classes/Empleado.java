
package Classes;


public class Empleado extends Persona{
    protected static int idEmpleado = 0;
    protected double salario;
    protected String cargo;
    protected boolean afiliadoArl;

    public Empleado( double salario, String cargo, boolean afiliadoArl,  String nombre, String apellidos, String tipoDocumento, String documento, String email) {
        super( nombre, apellidos, tipoDocumento, documento, email);
        this.idEmpleado++;
        this.salario = salario;
        this.cargo = cargo;
        this.afiliadoArl = afiliadoArl;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean isAfiliadoArl() {
        return afiliadoArl;
    }

    public void setAfiliadoArl(boolean afiliadoArl) {
        this.afiliadoArl = afiliadoArl;
    }
    
}
