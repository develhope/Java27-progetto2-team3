package Progetto;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Utente {
    private Carrello carrelloCliente;
    private List<Carrello> storicoAcquisti;

    public Cliente(String nome, String email, int telefono, String password) {
        super(nome, email, telefono, password);
        super.setTipoUtente(TipoUtente.CLIENTE);
        storicoAcquisti = new ArrayList<>();
        this.carrelloCliente = null;
    }

    public void setStoricoAcquisti(List<Carrello> storicoAcquisti) {
        for (Carrello i:storicoAcquisti){
            if (i!=null){
                this.storicoAcquisti.add(i);
            }
        }
    }

    public List<Carrello> getStoricoAcquisti() {
        return storicoAcquisti;
    }

    public Carrello getCarrelloCliente() {
        return carrelloCliente;
    }

    public boolean carrelloFinalizzatto(Carrello carrello) {
        return storicoAcquisti.add(carrello);
    }

    @Override
    public String getTipo() {
        return "Progetto.Cliente";
    }
}