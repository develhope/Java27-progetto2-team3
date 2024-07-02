import java.util.ArrayList;

public class Cliente extends Utente {
    private Carrello carrelloCliente;
    private ArrayList StoricoAcquisti;

    public Cliente(String nome, String email, int telefono, String password) {
        super(nome, email, telefono, password);
        super.setTipoUtente(tipoUtente.CLIENTE);
        this.carrelloCliente = null;
        StoricoAcquisti = null;
    }

    public ArrayList getStoricoAcquisti() {
        return StoricoAcquisti;
    }

    public Carrello getCarrelloCliente() {
        return carrelloCliente;
    }

    @Override
    public String getTipo() {
        return "Cliente";
    }
}
