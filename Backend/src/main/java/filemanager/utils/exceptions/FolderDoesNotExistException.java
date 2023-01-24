package filemanager.utils.exceptions;

public class FolderDoesNotExistException extends RuntimeException{
    public FolderDoesNotExistException(String errorMessage){
        super(errorMessage);
    }
}
