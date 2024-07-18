public class Dispositivo extends Prodotto {
    private double dimensioneDisplay;
    private int dimensioneSpazio;
    private String idDispositivo;
    private Enum tipoDispositivo;

    public Dispositivo(String produttore, String modello, String descrizione, double prezzoAcquisto, double prezzoVendita, Enum tipiProdotto, double dimensioneDisplay, int dimensioneSpazio, String idDispositivo) {
        super(produttore, modello, descrizione, prezzoAcquisto, prezzoVendita);
        this.dimensioneDisplay = dimensioneDisplay;
        this.dimensioneSpazio = dimensioneSpazio;
        this.idDispositivo = idDispositivo;
        this.tipoDispositivo = tipoDispositivo;
    }

    public double getDimensioneDisplay() {
        return dimensioneDisplay;
    }

    public int getDimensioneSpazio() {
        return dimensioneSpazio;
    }

    @Override
    public String getIdDispositivo() {
        return idDispositivo;
    }

    @Override
    public Enum getTipoDispositivo() {
        return tipoDispositivo;
    }

    @Override
    public String stampaProdottoCliente() {
        return "Produttore: " + produttore + " | Modello: " + modello + " | Descrizione: " + descrizione + " | Prezzo: " + prezzoVendita + " | Tipo: " + tipoDispositivo + " | Dimensione display: " + dimensioneDisplay + " | Dimensione di memoria: " + dimensioneSpazio;
    }

    @Override
    public String stampaProdottoMagazzinieri() {
        return "ID Dispositivo: " + idDispositivo + " | Produttore: " + produttore + " | Modello: " + modello + " | Descrizione: " + descrizione + " | Prezzo vendita: " + prezzoVendita + " | Prezzo acquisto: " + prezzoAcquisto + " | Tipo: " + tipoDispositivo + " | Dimensione display: " + dimensioneDisplay + " | Dimensione di memoria: " + dimensioneSpazio;
    }
}
