import javax.tools.Diagnostic;
import java.util.ArrayList;

public class Magazzino {
    private ArrayList<Prodotto> listaProdotti;

    public Magazzino(ArrayList<Prodotto> prodotto) {
        this.listaProdotti = prodotto;
    }

    public Magazzino() {
    }

    public ArrayList<Prodotto> getProdotto() {
        return listaProdotti;
    }

    //listaDispositivo, e quando chamada, consegue ser visualizada pelo usuario dependendo do tipo dele
    public ArrayList<Prodotto> visualizzaDispositivi() throws RicercaNullaException {
        ArrayList<Prodotto> prodotti = null;
        if (listaProdotti.isEmpty()) {
            throw new RicercaNullaException();
        }
        for (Prodotto i : listaProdotti) {
            prodotti.add(i);
        }
        return prodotti;
    }

    public ArrayList<Prodotto> ricercaTipoDispositivo(TipoProdotto p) throws RicercaNullaException {
        ArrayList<Prodotto> arrayTipo = new ArrayList<Dispositivo>();
        if (listaProdotti.isEmpty()) {
            throw new RicercaNullaException();
        }
        for (Prodotto i : listaProdotti) {
            if (p.equals(i.getTipiProdotto())) {
                arrayTipo.add(i);
            }
        }
        if (arrayTipo.isEmpty()) {
            throw new RicercaNullaException("Non esiste prodotto di questo tipo!");
        }
        return arrayTipo;
    }

    public ArrayList<Prodotto> ricercaPerProduttori(String p) throws RicercaNullaException {
        ArrayList<Prodotto> arrayProduttore = new ArrayList<Dispositivo>();
        if (listaProdotti.isEmpty()) {
            throw new RicercaNullaException();
        }
        for (Prodotto i : listaProdotti) {
            if (p.equals(i.getProduttore())) {
                arrayProduttore.add(i);
            }
        }
        if (arrayProduttore.isEmpty()) {
            throw new RicercaNullaException("Non esiste prodotto di questo produttori!");
        }
        return arrayProduttore;
    }

    public ArrayList<Prodotto> ricercaPerModelo(String m) throws RicercaNullaException {
        ArrayList<Prodotto> arrayModelo = new ArrayList<Prodotto>();

        if (listaProdotti.isEmpty()) {
            throw new RicercaNullaException();
        }
        for (Prodotto i : listaProdotti) {
            if (m.equals(i.getModello())) {
                arrayModelo.add(i);
            }
        }
        if (arrayModelo.isEmpty()) {
            throw new RicercaNullaException("Non esiste prodotto di questo modello!");
        }
        return arrayModelo;
    }

    public ArrayList<Prodotto> ricercaPrezzoVendita(Double v) throws RicercaNullaException {
        ArrayList<Prodotto> arrayPrezzoVendita = new ArrayList<Prodotto>();

        if (listaProdotti.isEmpty()) {
            throw new RicercaNullaException();
        }
        for (Prodotto i : listaProdotti) {
            if (v.equals(i.getPrezzoVendita())) {
                arrayPrezzoVendita.add(i);
            }
        }
        if (arrayPrezzoVendita.isEmpty()) {
            throw new RicercaNullaException("Non esiste prodotto con questo prezzo!");
        }
        return arrayPrezzoVendita;
    }


    public ArrayList<Prodotto> ricercaRangePrezzi(Double v1, Double v2) throws RicercaNullaException {

        ArrayList<Prodotto> arrayRangePrezzo = new ArrayList<Prodotto>();
        if (listaProdotti.isEmpty()) {
            throw new RicercaNullaException();
        }
        for (Prodotto i : listaProdotti) {
            if ((v1 > i.getPrezzoVendita()) && (v2 > i.getPrezzoVendita())) {
                arrayRangePrezzo.add(i);
            } else if ((v2 > i.getPrezzoVendita()) && (v1 > i.getPrezzoVendita())) {
                arrayRangePrezzo.add(i);
            }
        }
        if (listaProdotti.isEmpty()) {
            throw new RicercaNullaException("Non esiste prodotto in questa fascia di prezzo!");
        }
        return arrayRangePrezzo;
    }

    public Prodotto ricercaProdotto(String id) throws RicercaNullaException {
        if (listaProdotti.isEmpty()) {
            throw new RicercaNullaException();
        }
        for (Prodotto i : listaProdotti) {
            if (id == i.getIdDispositivo()) {
                return i;
            }
        }
        throw new RicercaNullaException("Non esiste prodotto con questo ID!");

    }

    public Prodotto rimuoveProdotto(String id) throws RicercaNullaException {
        if (listaProdotti.isEmpty()) {
            throw new RicercaNullaException();
        }

        for (Prodotto i : listaProdotti) {
            if (id == i.getIdDispositivo()) {
                return listaProdotti.remove(i);
            }
        }
        throw new RicercaNullaException("Non esiste prodotto con questo ID!");
    }
}