//Only users with privileged access are allowed to add products


public class NotAuthorizedException extends Exception {
    public NotAuthorizedException(String message){
        super(message);
    }
}
