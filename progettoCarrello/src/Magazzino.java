import java.util.ArrayList;

public class Magazzino {
    private ArrayList<Dispositivo> prodotto;

    public Magazzino(ArrayList<Dispositivo> prodotto) {
        this.prodotto = prodotto;
    }

    public Magazzino() {

    }

    public ArrayList<Dispositivo> getProdotto() {
        return prodotto;
    }

    public ArrayList<String> VisualizzaDispositivi(Utente u) {
        ArrayList<String> prodotti = null;
        if (u.getTipo() == "Magazziniere") {
            for (int i = 0; i < prodotto.size(); i++) {
                prodotti.add(prodotto.get(i).stampaProdottoMagazzinieri());
            }
        } else {
            for (int i = 0; i < prodotto.size(); i++) {
                prodotti.add(prodotto.get(i).stampaProdottoCliente());
            }
        }
        return prodotti;
    }

    public void RicercaTipoDispositivo(TipoProdotto p) {
        for (int i = 0; i < prodotto.size(); i++) {
            if (p.equals(prodotto.get(i).tipiProdotto)) {
                System.out.println(prodotto.get(i));
            }
        }
    }

    public void RicercaPerProduttori(String p) {
        for (int i = 0; i < prodotto.size(); i++) {
            if (p.equals(prodotto.get(i).produttore)) {
                System.out.println(prodotto.get(i));
            }
        }
    }

    public void RicercaPerModelo(String m) {
        for (int i = 0; i < prodotto.size(); i++) {
            if (m.equals(prodotto.get(i).modello)) {
                System.out.println(prodotto.get(i));
            }
        }
    }

    public void RicercaPrezzoVendita(Double v) {
        for (int i = 0; i < prodotto.size(); i++) {
            if (v.equals(prodotto.get(i).prezzoVendita)) {
                System.out.println(prodotto.get(i));
            }
        }
    }

    public void RicercaRangePrezzi(Double v1, Double v2) {
        for (int i = 0; i < prodotto.size(); i++) {
            if ((v1 > prodotto.get(i).prezzoVendita) && (v2 > prodotto.get(i).prezzoVendita)) {
                System.out.println(prodotto.get(i));
            } else if ((v2 > prodotto.get(i).prezzoVendita) && (v1 > prodotto.get(i).prezzoVendita)) {
                System.out.println(prodotto.get(i));
            } else {
                System.out.println("Non esiste prodotto in questa fascia di prezzo!");
            }
        }
    }

    public Dispositivo ricercaProdotto(String id){
        for (int i = 0; i < prodotto.size(); i++) {
            if (id==prodotto.get(i).getIdDispositivo()){
                return prodotto.get(i);
            }
        }
        return null;
    }

    public void rimuoveProdotto(String id){
        for (int i = 0; i < prodotto.size(); i++) {
            if (id==prodotto.get(i).getIdDispositivo()){
                prodotto.remove(i);
            }
        }
    }

}
