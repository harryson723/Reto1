package Controller;

/**
 *
 * @author HarryFora
 */
public class Domiciliario  extends Employed{
    private int idDomiciliario;
    private String tipoTransporte;
    
    public Domiciliario(String firtsNames, String lastNames, String mail, String document, String documentType, JobType employedType, int employedId) {
        super(firtsNames, lastNames, mail, document, documentType, employedType, employedId);
    }

    public Domiciliario(int idDomiciliario, String tipoTransporte, String firtsNames, String lastNames, String mail, String document, String documentType, JobType employedType, int employedId) {
        super(firtsNames, lastNames, mail, document, documentType, employedType, employedId);
        this.idDomiciliario = idDomiciliario;
        this.tipoTransporte = tipoTransporte;
    }

    public int getIdDomiciliario() {
        return idDomiciliario;
    }

    public void setIdDomiciliario(int idDomiciliario) {
        this.idDomiciliario = idDomiciliario;
    }

    public String getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(String tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }
    
    
    
}
