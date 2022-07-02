package Classes;

public class Domiciliario {

    private String tipoVehiculo;
    private static int idDomiciliaro = 0;
    private boolean licenciaConduccion;
    private boolean papelesAlDia;

    public Domiciliario(String tipoVehiculo,  boolean licenciaConduccion, boolean papelesAlDia) {
        this.tipoVehiculo = tipoVehiculo;
        this.idDomiciliaro++;
        this.licenciaConduccion = licenciaConduccion;
        this.papelesAlDia = papelesAlDia;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public int getIdDomiciliaro() {
        return idDomiciliaro;
    }

    public void setIdDomiciliaro(int idDomiciliaro) {
        this.idDomiciliaro = idDomiciliaro;
    }

    public boolean isLicenciaConduccion() {
        return licenciaConduccion;
    }

    public void setLicenciaConduccion(boolean licenciaConduccion) {
        this.licenciaConduccion = licenciaConduccion;
    }

    public boolean isPapelesAlDia() {
        return papelesAlDia;
    }

    public void setPapelesAlDia(boolean papelesAlDia) {
        this.papelesAlDia = papelesAlDia;
    }

    @Override
    public String toString() {
        return "Domiciliario{" + "tipoVehiculo=" + tipoVehiculo + ", idDomiciliaro=" + idDomiciliaro + ", licenciaConduccion=" + licenciaConduccion + ", papelesAlDia=" + papelesAlDia + '}';
    }

}
