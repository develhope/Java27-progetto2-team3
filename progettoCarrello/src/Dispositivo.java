enum TipoProdotto {
    TABLET,
    SMARTPHONE,
    NOTEBOOK
}

public class Dispositivo extends Prodotto {
        private double dimensioneDisplay;
        private int dimensioneSpazio;
        private String idDispositivo;

        public Dispositivo(String produttore, String modello, String descrizione, double prezzoAcquisto, double prezzoVendita, Enum tipiProdotto, double dimensioneDisplay, int dimensioneSpazio, String idDispositivo) {
            super(produttore, modello, descrizione, prezzoAcquisto, prezzoVendita, tipiProdotto);
            this.dimensioneDisplay = dimensioneDisplay;
            this.dimensioneSpazio = dimensioneSpazio;
            this.idDispositivo = idDispositivo;
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

        @Override
        public String stampaProdottoCliente() {
            return "Produttore: " + produttore + " | Modello: " + modello + " | Descrizione: " + descrizione + " | Prezzo: " + prezzoVendita + " | Tipo: " + tipiProdotto + " | Dimensione display: " + dimensioneDisplay + " | Dimensione di memoria: " + dimensioneSpazio;
        }

        @Override
        public String stampaProdottoMagazzinieri() {
            return "ID Dispositivo: " + idDispositivo + " | Produttore: " + produttore + " | Modello: " + modello + " | Descrizione: " + descrizione + " | Prezzo vendita: " + prezzoVendita + " | Prezzo acquisto: " + prezzoAcquisto + " | Tipo: " + tipiProdotto + " | Dimensione display: " + dimensioneDisplay + " | Dimensione di memoria: " + dimensioneSpazio;
        }
    }
