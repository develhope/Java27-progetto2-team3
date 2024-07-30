import java.util.UUID;

public class Dispositivo extends Prodotto {
    private double dimensioneDisplay;
    private int dimensioneSpazio;
    private UUID idDispositivo;
    private TipoDispositivo tipoDispositivo;

    @Override
    public String toStringDetailsClient() {
        return "Dispositivo{" +
                ", idDispositivo='" + idDispositivo + '\'' +
                ", tipoDispositivo=" + tipoDispositivo +
                ", produttore='" + produttore + '\'' +
                ", modello='" + modello + '\'' +
                ", prezzoAcquisto=" + prezzoAcquisto +
                '}';
    }

    public Dispositivo(String produttore, String modello, String descrizione, double prezzoAcquisto, double prezzoVendita, TipoDispositivo tipoDispositivo, double dimensioneDisplay, int dimensioneSpazio) {
        super(produttore, modello, descrizione, prezzoAcquisto, prezzoVendita);
        this.dimensioneDisplay = dimensioneDisplay;
        this.dimensioneSpazio = dimensioneSpazio;
        this.idDispositivo = UUID.randomUUID();
        this.tipoDispositivo = tipoDispositivo;
    }

    public double getDimensioneDisplay() {
        return dimensioneDisplay;
    }

    public int getDimensioneSpazio() {
        return dimensioneSpazio;
    }

    public UUID getIdDispositivo() {
        return idDispositivo;
    }

    public TipoDispositivo getTipoDispositivo() {
        return tipoDispositivo;
    }

    @Override
    public String stampaProdottoCliente() {
        return "Produttore: " + produttore + " | Modello: " + modello + " | Descrizione: " + descrizione + " | Prezzo: " + prezzoVendita + " | Tipo: " + tipoDispositivo + " | Dimensione display: " + dimensioneDisplay + " | Dimensione di memoria: " + dimensioneSpazio + " | ID dispositivo: " + idDispositivo;
    }

    @Override
    public String stampaProdottoMagazzinieri() {
        return "ID Dispositivo: " + idDispositivo + " | Produttore: " + produttore + " | Modello: " + modello + " | Descrizione: " + descrizione + " | Prezzo vendita: " + prezzoVendita + " | Prezzo acquisto: " + prezzoAcquisto + " | Tipo: " + tipoDispositivo + " | Dimensione display: " + dimensioneDisplay + " | Dimensione di memoria: " + dimensioneSpazio;
    }
}
