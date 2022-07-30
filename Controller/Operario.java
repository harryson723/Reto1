package Controller;

/**
 *
 * @author HarryFora
 */
public class Operario extends Employed{
    private int idOperario;
    private boolean manejoMaquinariaPesada;

    public Operario(String firtsNames, String lastNames, String mail, String document, String documentType, JobType employedType, int employedId) {
        super(firtsNames, lastNames, mail, document, documentType, employedType, employedId);
    }

    public Operario(int idOperario, boolean manejoMaquinariaPesada, String firtsNames, String lastNames, String mail, String document, String documentType, JobType employedType, int employedId) {
        super(firtsNames, lastNames, mail, document, documentType, employedType, employedId);
        this.idOperario = idOperario;
        this.manejoMaquinariaPesada = manejoMaquinariaPesada;
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
    
    
}
