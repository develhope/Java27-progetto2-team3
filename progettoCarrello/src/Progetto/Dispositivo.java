<<<<<<< HEAD:progettoCarrello/src/Progetto/Dispositivo.java
package Progetto;

public class Dispositivo extends Prodotto {
    protected double dimensioneDisplay;
    protected int dimensioneSpazio;
    protected String idDispositivo;
    protected TipoDispositivo tipoDispositivo;
=======
import java.util.UUID;

public class Dispositivo extends Prodotto {
    private double dimensioneDisplay;
    private int dimensioneSpazio;
    private UUID idDispositivo;
    private TipoDispositivo tipoDispositivo;
>>>>>>> 2d0ddd304e861e4298139014b40afd151976f680:progettoCarrello/src/Dispositivo.java

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
        return "ID Progetto.Dispositivo: " + idDispositivo + " | Produttore: " + produttore + " | Modello: " + modello + " | Descrizione: " + descrizione + " | Prezzo vendita: " + prezzoVendita + " | Prezzo acquisto: " + prezzoAcquisto + " | Tipo: " + tipoDispositivo + " | Dimensione display: " + dimensioneDisplay + " | Dimensione di memoria: " + dimensioneSpazio;
    }
}
