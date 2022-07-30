package Controller;

/**
 *
 * @author HarryFora
 */
public class Gerente extends Employed{
    private int idGerente;
    private int personasACargo;
        
    public Gerente(String firtsNames, String lastNames, String mail, String document, String documentType, JobType employedType, int employedId) {
        super(firtsNames, lastNames, mail, document, documentType, employedType, employedId);
    }

    public Gerente(int idGerente, int personasACargo, String firtsNames, String lastNames, String mail, String document, String documentType, JobType employedType, int employedId) {
        super(firtsNames, lastNames, mail, document, documentType, employedType, employedId);
        this.idGerente = idGerente;
        this.personasACargo = personasACargo;
    }

    public int getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(int idGerente) {
        this.idGerente = idGerente;
    }

    public int getPersonasACargo() {
        return personasACargo;
    }

    public void setPersonasACargo(int personasACargo) {
        this.personasACargo = personasACargo;
    }
    
    
    
}
