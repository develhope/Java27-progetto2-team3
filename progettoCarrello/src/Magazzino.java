import javax.tools.Diagnostic;
import java.util.ArrayList;
import java.util.List;

public class Magazzino {
    private ArrayList<Prodotto> listaProdotti;

    public Magazzino(ArrayList<Prodotto> prodotto) {
        this.listaProdotti = prodotto;
    }

    public Magazzino() {
        listaProdotti = new ArrayList<Prodotto>();
    }

    public ArrayList<Prodotto> getProdotto() {
        return listaProdotti;
    }

    //listaDispositivo, e quando richiamato può essere visualizzato dall'utente a seconda della sua tipologia
    public ArrayList<Prodotto> visualizzaDispositivi() throws RicercaNullaException {
        if (listaProdotti.isEmpty()) {
            throw new RicercaNullaException();
        }
        return listaProdotti;
    }

    public List<Prodotto> ricercaTipoDispositivo(TipoDispositivo p) throws RicercaNullaException {
        List<Prodotto> arrayTipo = new ArrayList<>();
        if (listaProdotti.isEmpty()) {
            throw new RicercaNullaException();
        }
        for (Prodotto i : listaProdotti) {
            if (p.equals(((Dispositivo)i).getTipoDispositivo())) {
                arrayTipo.add(i);
            }
        }
        if (arrayTipo.isEmpty()) {
            throw new RicercaNullaException("Non esiste prodotto di questo tipo!");
        }
        return arrayTipo;
    }

    public List<Prodotto> ricercaPerProduttori(String p) throws RicercaNullaException {
        List<Prodotto> arrayProduttore = new ArrayList<>();
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

    public List<Prodotto> ricercaPerModelo(String m) throws RicercaNullaException {
        List<Prodotto> arrayModelo = new ArrayList<>();

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

    public List<Prodotto> ricercaPrezzoVendita(Double v) throws RicercaNullaException {
        List<Prodotto> arrayPrezzoVendita = new ArrayList<>();

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


    public List<Prodotto> ricercaRangePrezzi(Double v1, Double v2) throws RicercaNullaException {
        List<Prodotto> arrayRangePrezzo = new ArrayList<>();

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
            if (id == ((Dispositivo)i).getIdDispositivo()) {
                return i;
            }
        }
        throw new RicercaNullaException("Non esiste prodotto con questo ID!");

    }

    public boolean rimuoveProdotto(String idDispositivo) throws RicercaNullaException {
        if (listaProdotti.isEmpty()) {
            throw new RicercaNullaException();
        }

        for (Prodotto i : listaProdotti) {
            if (idDispositivo == ((Dispositivo)i).getIdDispositivo()) {
                return listaProdotti.remove(i);
            }
        }
        throw new RicercaNullaException("Non esiste prodotto con questo ID!");
    }
}