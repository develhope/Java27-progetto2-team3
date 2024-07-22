public class Magazziniere extends Utente {

    Magazzino magazzino = new Magazzino();

    public Magazziniere(String nome, String email, int telefono, String password) {
        super(nome, email, telefono, password);
        super.setTipoUtente(TipoUtente.MAGAZZINIERE);
    }

    public Magazzino getMagazzino() {
        return magazzino;
    }

    public void setMagazzino(Magazzino magazzino) {
        this.magazzino = magazzino;
    }

    @Override
    public String getTipo() {
        return "Magazziniere";
    }

    public boolean rimuoviDalMagazzino(Prodotto id) throws RicercaNullaException {
       return magazzino.rimuoveProdotto(((Dispositivo)id).getIdDispositivo());
    }

    public void RicercaPrezzoAcquisto(Double v) {
        for (int i = 0; i < magazzino.getProdotto().size(); i++) {
            if (v.equals(magazzino.getProdotto().get(i).prezzoAcquisto)) {
                System.out.println(magazzino.getProdotto().get(i));
            }
        }
    }

}
