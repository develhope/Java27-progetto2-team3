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
        if (prodotto.size() != 0) {
            if (u.getTipo() == "Magazziniere") {
                for (int i = 0; i < prodotto.size(); i++) {
                    System.out.println(prodotto.get(i).stampaProdottoMagazzinieri());
                }
            } else {
                for (int i = 0; i < prodotto.size(); i++) {
                    if (prodotto.get(i) != null) {
                        System.out.println(prodotto.get(i).stampaProdottoCliente());
                    }
                }
            }
        } else {
            System.out.println("Non esiste prodotto nel magazzino!");
        }
        return null;
    }

    public void RicercaTipoDispositivo(TipoProdotto p) {
        int esiste = 0;
        if (prodotto.size() != 0) {
            for (int i = 0; i < prodotto.size(); i++) {
                if (p.equals(prodotto.get(i).tipiProdotto)) {
                    System.out.println(prodotto.get(i).stampaProdottoCliente());
                    esiste++;
                }
            }
            if (esiste == 0) {
                System.out.println("Non esiste prodotti di questo tipo!");
            }
        }
    }

    public void RicercaPerProduttori(String p) {
        if (prodotto.size() != 0) {
            for (int i = 0; i < prodotto.size(); i++) {
                if (p.equals(prodotto.get(i).produttore)) {
                    System.out.println(prodotto.get(i).stampaProdottoCliente());
                }
            }
        } else {
            System.out.println("Non esiste prodotto di questo produttori!");
        }
    }

    public void RicercaPerModelo(String m) {
        if (prodotto.size() != 0) {
            for (int i = 0; i < prodotto.size(); i++) {
                if (m.equals(prodotto.get(i).modello)) {
                    System.out.println(prodotto.get(i).stampaProdottoCliente());
                }
            }
        } else {
            System.out.println("Non esiste prodotto di questo modelo!");
        }
    }

    public void RicercaPrezzoVendita(Double v) {
        if (prodotto.size() != 0) {
            for (int i = 0; i < prodotto.size(); i++) {
                if (v.equals(prodotto.get(i).prezzoVendita)) {
                    System.out.println(prodotto.get(i).stampaProdottoCliente());
                }
            }
        } else {
            System.out.println("Non esiste prodotto con questo prezzo!");
        }
    }

    public void RicercaRangePrezzi(Double v1, Double v2) {
        if (prodotto.size() != 0) {
            for (int i = 0; i < prodotto.size(); i++) {
                if ((v1 > prodotto.get(i).prezzoVendita) && (v2 > prodotto.get(i).prezzoVendita)) {
                    System.out.println(prodotto.get(i).stampaProdottoCliente());
                } else if ((v2 > prodotto.get(i).prezzoVendita) && (v1 > prodotto.get(i).prezzoVendita)) {
                    System.out.println(prodotto.get(i).stampaProdottoCliente());
                }
            }
        } else {
            System.out.println("Non esiste prodotto in questa fascia di prezzo!");
        }
    }

    public Prodotto ricercaProdotto(String id) {
        if (prodotto.size() != 0) {
            for (int i = 0; i < prodotto.size(); i++) {
                if (id == prodotto.get(i).getIdDispositivo()) {
                    System.out.println(prodotto.get(i).stampaProdottoCliente());
                    return prodotto.get(i);
                }
            }
        } else {
            System.out.println("Non esiste prodotto con questo ID!");
        }
        return null;
    }

    public void rimuoveProdotto(Dispositivo id) {
        if (prodotto.size() != 0) {
            for (int i = 0; i < prodotto.size(); i++) {
                if (id.getIdDispositivo().equals(prodotto.get(i).getIdDispositivo())) {
                    prodotto.remove(i);
                }
            }
        }
    }

}
