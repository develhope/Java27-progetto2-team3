public abstract class Prodotto {
    protected String  produttore;
    protected String modello;
    protected String descrizione;
    protected double prezzoAcquisto;
    protected double prezzoVendita;
    protected Enum tipiProdotto ;

    public Prodotto(String produttore, String modello, String descrizione, double prezzoAcquisto, double prezzoVendita, Enum tipiProdotto) {
        this.produttore = produttore;
        this.modello = modello;
        this.descrizione = descrizione;
        this.prezzoAcquisto = prezzoAcquisto;
        this.prezzoVendita = prezzoVendita;
        this.tipiProdotto = tipiProdotto;
    }


    public abstract String stampaProdottoCliente();

    public abstract String stampaProdottoMagazzinieri();
}
