package Progetto;

public abstract class Utente{
    protected String nome;
    protected String email;
    protected int telefono;
    protected String password;
    protected TipoUtente tipoUtente;

    @Override
    public String toString() {
        return "Progetto.Utente{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefono=" + telefono +
                ", password='" + password + '\'' +
                ", tipoUtente=" + tipoUtente +
                '}';
    }

    public Utente(String nome, String email, int telefono, String password) {
        this.nome = nome;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
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

    public abstract String getTipo();
}