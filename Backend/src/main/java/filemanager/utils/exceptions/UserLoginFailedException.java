package filemanager.utils.exceptions;

public class UserLoginFailedException extends RuntimeException{
    public UserLoginFailedException(String errorMessage){
        super(errorMessage);
    }
}
