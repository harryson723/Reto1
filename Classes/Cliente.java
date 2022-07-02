package Classes;

public class Cliente extends Persona {

    private static int idCliente = 0;
    private boolean clientePreferencial;
    private String direccionDomicilio;
    private String ciudadDomicilio;

    public Cliente(int idCliente, boolean clientePreferencial, String direccionDomicilio, String ciudadDomicilio,  String nombre, String apellidos, String tipoDocumento, String documento, String email) {
        super( nombre, apellidos, tipoDocumento, documento, email);
        this.idCliente++;
        this.clientePreferencial = clientePreferencial;
        this.direccionDomicilio = direccionDomicilio;
        this.ciudadDomicilio = ciudadDomicilio;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public boolean isClientePreferencial() {
        return clientePreferencial;
    }

    public void setClientePreferencial(boolean clientePreferencial) {
        this.clientePreferencial = clientePreferencial;
    }

    public String getDireccionDomicilio() {
        return direccionDomicilio;
    }

    public void setDireccionDomicilio(String direccionDomicilio) {
        this.direccionDomicilio = direccionDomicilio;
    }

    public String getCiudadDomicilio() {
        return ciudadDomicilio;
    }

    public void setCiudadDomicilio(String ciudadDomicilio) {
        this.ciudadDomicilio = ciudadDomicilio;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", clientePreferencial=" + clientePreferencial + ", direccionDomicilio=" + direccionDomicilio + ", ciudadDomicilio=" + ciudadDomicilio + '}';
    }
    
}
