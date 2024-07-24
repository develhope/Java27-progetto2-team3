import java.util.ArrayList;

public class Cliente extends Utente {
    private Carrello carrelloCliente;
    private ArrayList<Carrello> storicoAcquisti;

    public Cliente(String nome, String email, int telefono, String password) {
        super(nome, email, telefono, password);
        super.setTipoUtente(TipoUtente.CLIENTE);
        storicoAcquisti = new ArrayList<>();
        this.carrelloCliente = null;
    }

    public ArrayList<Carrello> getStoricoAcquisti() {
        return storicoAcquisti;
    }

    public Carrello getCarrelloCliente() {
        return carrelloCliente;
    }

    public boolean carrelloFinalizzatto(Carrello carrello) {
        boolean result;
        if (storicoAcquisti.isEmpty()) {
            result = storicoAcquisti.add(new Carrello(1,carrello.getListaProdottiCarrello(), carrello.isChiuso()));
        } else {
            result = storicoAcquisti.add(new Carrello((storicoAcquisti.getLast().getIdCarrello() + 1),carrello.getListaProdottiCarrello(), carrello.isChiuso()));
        }
        carrello.cleanCarrello();
        return result;
    }

    @Override
    public String getTipo() {
        return "Cliente";
    }
}
