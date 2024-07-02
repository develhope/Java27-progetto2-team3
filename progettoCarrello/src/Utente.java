public abstract class Utente {
    protected String nome;
    protected String Email;
    protected int Telefono;
    protected String Password;
    protected TipoUtente tipoUtente;

    public Utente(String nome, String email, int telefono, String password) {
        this.nome = nome;
        this.Email = email;
        this.Telefono = telefono;
        this.Password = password;
    }

    public String getPassword() {
        return Password;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return Email;
    }

    public TipoUtente getTipoUtente() {
        return tipoUtente;
    }

    public void setTipoUtente(TipoUtente tipoUtente) {
        this.tipoUtente = tipoUtente;
    }

    public int getTelefono() {
        return Telefono;
    }

    public abstract String getTipo();
}
