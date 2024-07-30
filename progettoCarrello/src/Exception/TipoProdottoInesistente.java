package Exception;

public class TipoProdottoInesistente extends Exception{
    public TipoProdottoInesistente(){
        super("Questo tipo di prodotto non esiste");
    }
    public TipoProdottoInesistente(String error){
        super(error);
    }
}
