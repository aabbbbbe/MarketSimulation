//Price only allowed within [0-infinity[

public class OutOfBoundsException extends Exception {
    public OutOfBoundsException(String message) {
        super(message);
    }
}
