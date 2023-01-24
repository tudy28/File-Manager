package filemanager.utils.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String errorMessage){
        super(errorMessage);
    }
}
