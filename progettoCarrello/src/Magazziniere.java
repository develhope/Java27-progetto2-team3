public class Magazziniere extends Utente {
    Magazzino magazzino = new Magazzino();


    public Magazziniere(String nome, String email, int telefono, String password, Magazzino magazzino) {
        super(nome, email, telefono, password);
        super.setTipoUtente(tipoUtente.MAGAZZINIERE);
        this.magazzino = magazzino;
    }

    @Override
    public String getTipo() {
        return "Magazziniere";
    }


    // CalcoloMedioPerSingoloDispositivo - domanda Nico

    public void RimoviIDMagazzino(String id) {
        magazzino.rimuoveProdotto(id);
        System.out.println("Prodotto rimosso!");
    }

    public void RicercaPrezzoAcquisto(Double v) {
        for (int i = 0; i < magazzino.getProdotto().size(); i++) {
            if (v.equals(magazzino.getProdotto().get(i).prezzoAcquisto)) {
                System.out.println(magazzino.getProdotto().get(i));
            }
        }
    }

}
