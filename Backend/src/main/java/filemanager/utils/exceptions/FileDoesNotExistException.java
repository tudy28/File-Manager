package filemanager.utils.exceptions;

public class FileDoesNotExistException extends RuntimeException{
    public FileDoesNotExistException(String errorMessage){
        super(errorMessage);
    }
}
