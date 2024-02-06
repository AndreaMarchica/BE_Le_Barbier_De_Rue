package andreamarchica.BE_Le_Barbier_De_Rue.exceptions;

public class EmailAlreadyInDbException extends  RuntimeException{
    public EmailAlreadyInDbException(String email) {
        super(email+" gia esistente, selezionane un'altra");
    }
}
