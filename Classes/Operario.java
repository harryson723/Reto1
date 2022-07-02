package Classes;

public class Operario extends Empleado {

    private static int idOperario = 0;
    private boolean manejoMaquinariaPesada;
    private int cantidadHoraExtra;

    public Operario( boolean manejoMaquinariaPesada, int cantidadHoraExtra, double salario, String cargo, boolean afiliadoArl, int idPersona, String nombre, String apellidos, String tipoDocumento, String documento, String email) {
        super( salario, cargo, afiliadoArl,  nombre, apellidos, tipoDocumento, documento, email);
        this.idOperario++;
        this.manejoMaquinariaPesada = manejoMaquinariaPesada;
        this.cantidadHoraExtra = cantidadHoraExtra;
    }

    public int getIdOperario() {
        return idOperario;
    }

    public void setIdOperario(int idOperario) {
        this.idOperario = idOperario;
    }

    public boolean isManejoMaquinariaPesada() {
        return manejoMaquinariaPesada;
    }

    public void setManejoMaquinariaPesada(boolean manejoMaquinariaPesada) {
        this.manejoMaquinariaPesada = manejoMaquinariaPesada;
    }

    public int getCantidadHoraExtra() {
        return cantidadHoraExtra;
    }

    public void setCantidadHoraExtra(int cantidadHoraExtra) {
        this.cantidadHoraExtra = cantidadHoraExtra;
    }

    @Override
    public String toString() {
        return "Operario{" + "idOperario=" + idOperario + ", manejoMaquinariaPesada=" + manejoMaquinariaPesada + ", cantidadHoraExtra=" + cantidadHoraExtra + '}';
    }
    

}
