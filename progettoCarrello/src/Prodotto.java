public abstract class Prodotto {
    protected String  produttore;
    protected String modello;
    protected String descrizione;
    protected double prezzoAcquisto;
    protected double prezzoVendita;


    public Prodotto(String produttore, String modello, String descrizione, double prezzoAcquisto, double prezzoVendita) {
        this.produttore = produttore;
        this.modello = modello;
        this.descrizione = descrizione;
        this.prezzoAcquisto = prezzoAcquisto;
        this.prezzoVendita = prezzoVendita;

    }


    public abstract String stampaProdottoCliente();

    public abstract String stampaProdottoMagazzinieri();
}

