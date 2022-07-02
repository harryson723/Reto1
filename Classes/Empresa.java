
package Classes;


public class Empresa {
   private String nit;
   private String razonSocial;

    public Empresa(String nit, String razonSocial) {
        this.nit = nit;
        this.razonSocial = razonSocial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
   
}
