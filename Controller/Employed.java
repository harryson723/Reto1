package Controller;

public class Employed {

    private String firtsNames;
    private String lastNames;
    private String mail;
    private String document;
    private String documentType;
    private int employedId;

    public Employed(String firtsNames, String lastNames, String mail, String document, String documentType, int employedId) {
        this.firtsNames = firtsNames;
        this.lastNames = lastNames;
        this.mail = mail;
        this.document = document;
        this.documentType = documentType;
        this.employedId = employedId;
    }

    public String getFirtsNames() {
        return firtsNames;
    }

    public void setFirtsNames(String firtsNames) {
        this.firtsNames = firtsNames;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public int getEmployedId() {
        return employedId;
    }

    public void setEmployedId(int employedId) {
        this.employedId = employedId;
    }
    
    
}
