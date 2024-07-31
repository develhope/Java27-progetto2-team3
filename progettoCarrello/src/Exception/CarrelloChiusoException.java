package Exception;

public class CarrelloChiusoException extends Exception{
    public CarrelloChiusoException(){
        super("Questo carrello non può più essere modificato poiché è già stato finalizzato");
    }
    public CarrelloChiusoException(String error){
        super(error);
    }
}
