package Progetto;

public class Dispositivo extends Prodotto {
    protected double dimensioneDisplay;
    protected int dimensioneSpazio;
    protected String idDispositivo;
    protected TipoDispositivo tipoDispositivo;

    @Override
    public String toStringDetailsClient() {
        return "Progetto.Dispositivo{" +
                ", idDispositivo='" + idDispositivo + '\'' +
                ", tipoDispositivo=" + tipoDispositivo +
                ", produttore='" + produttore + '\'' +
                ", modello='" + modello + '\'' +
                ", prezzoAcquisto=" + prezzoAcquisto +
                '}';
    }

    public Dispositivo(String produttore, String modello, String descrizione, double prezzoAcquisto, double prezzoVendita, TipoDispositivo tipoDispositivo, double dimensioneDisplay, int dimensioneSpazio, String idDispositivo) {
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

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public TipoDispositivo getTipoDispositivo() {
        return tipoDispositivo;
    }

    @Override
    public String stampaProdottoCliente() {
        return "Produttore: " + produttore + " | Modello: " + modello + " | Descrizione: " + descrizione + " | Prezzo: " + prezzoVendita + " | Tipo: " + tipoDispositivo + " | Dimensione display: " + dimensioneDisplay + " | Dimensione di memoria: " + dimensioneSpazio;
    }

    @Override
    public String stampaProdottoMagazzinieri() {
        return "ID Progetto.Dispositivo: " + idDispositivo + " | Produttore: " + produttore + " | Modello: " + modello + " | Descrizione: " + descrizione + " | Prezzo vendita: " + prezzoVendita + " | Prezzo acquisto: " + prezzoAcquisto + " | Tipo: " + tipoDispositivo + " | Dimensione display: " + dimensioneDisplay + " | Dimensione di memoria: " + dimensioneSpazio;
    }
}
