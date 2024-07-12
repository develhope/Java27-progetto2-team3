import java.util.ArrayList;
import java.util.List;

public class Carrello {
    int idCarrello;
    List<Prodotto> listaProdottiCarrello;
    Magazzino magazzino;
    boolean chiuso;

    public Carrello() {
        listaProdottiCarrello = new ArrayList<Prodotto>();
        chiuso = false;
    }

    public Prodotto AggiungeIDDispositivoAlCarrello(String idDispositivo) throws RicercaNullaException, CarrelloChiusoException {
        if (chiuso) {
            throw new CarrelloChiusoException();
        }
        Prodotto item = magazzino.rimuovereDispositivoAlMagazzino();
        if (item != null) {
            listaProdottiCarrello.add(item);
            return item;
        }
        return null;
    }

    public Prodotto RimoviIDDispositivoAlCarrello(String idDispositivo) throws CarrelloChiusoException {
        if (chiuso) {
            throw new CarrelloChiusoException();
        }
        Prodotto item = listaProdottiCarrello.remove(idDispositivo);
        aggiungereDispositivoAlMagazzino(item);
        return item;
    }


    public Double TotaleCarrello() {
        double sumTotal = 0;
        listaProdottiCarrello.forEach(i -> sumTotal += i.getprezzoVendita());
        return sumTotal;
    }


    public int finalizaCompra() {
        this.chiuso = true;
        return idCarrello;
    }


}
