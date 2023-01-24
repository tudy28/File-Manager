package filemanager.utils.exceptions;

public class UserDoesNotExistException extends RuntimeException{
    public UserDoesNotExistException(String errorMessage){
        super(errorMessage);
    }
}