package GSON.FormDate;

import java.util.List;
import Progetto.*;

public class TypeGSONCliente {
    List<TypeGSONCarrello> listCarrello;
    String nome;
    String email;
    int telefono;
    String password;
    TipoUtente tipoUtente;

    public TypeGSONCliente(Cliente cliente, List<TypeGSONCarrello> listCarrello) {
        this.listCarrello = listCarrello;
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.telefono = cliente.getTelefono();
        this.password = cliente.getPassword();
        this.tipoUtente = cliente.getTipoUtente();
    }

    public List<TypeGSONCarrello> getListCarrello() {
        return listCarrello;
    }

    public void setListCarrello(List<TypeGSONCarrello> listCarrello) {
        this.listCarrello = listCarrello;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUtente getTipoUtente() {
        return tipoUtente;
    }

    public void setTipoUtente(TipoUtente tipoUtente) {
        this.tipoUtente = tipoUtente;
    }
}
