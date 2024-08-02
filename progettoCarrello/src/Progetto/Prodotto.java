package Progetto;

public abstract class Prodotto {
    protected String produttore;
    protected String modello;
    protected String descrizione;
    protected double prezzoAcquisto;
    protected double prezzoVendita;

    public String toStringDetailsClient() {
        return "Progetto.Prodotto{" +
                "produttore='" + produttore + '\'' +
                ", modello='" + modello +
                '}';
    }

    public Prodotto(String produttore, String modello, String descrizione, double prezzoAcquisto, double prezzoVendita) {
        this.produttore = produttore;
        this.modello = modello;
        this.descrizione = descrizione;
        this.prezzoAcquisto = prezzoAcquisto;
        this.prezzoVendita = prezzoVendita;

    }

    public String getProduttore() {
        return produttore;
    }

    public void setProduttore(String produttore) {
        this.produttore = produttore;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzoAcquisto() {
        return prezzoAcquisto;
    }

    public void setPrezzoAcquisto(double prezzoAcquisto) {
        this.prezzoAcquisto = prezzoAcquisto;
    }

    public double getPrezzoVendita() {
        return prezzoVendita;
    }

    public void setPrezzoVendita(double prezzoVendita) {
        this.prezzoVendita = prezzoVendita;
    }

    public abstract String stampaProdottoCliente();

    public abstract String stampaProdottoMagazzinieri();
}

