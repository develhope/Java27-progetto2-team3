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

    @Override
    public String getTipo() {
        return "Cliente";
    }
}
