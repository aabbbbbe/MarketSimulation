//Price only allowed within [0-infinity[

public class PriceOutOfBoundsException extends Exception {
    public PriceOutOfBoundsException(String message) {
        super(message);
    }
}
