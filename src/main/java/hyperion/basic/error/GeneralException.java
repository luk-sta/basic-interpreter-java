package hyperion.basic.error;

/**
 * @author Lukas Stanek
 * @since 06.03.21
 */
public class GeneralException extends Exception {
    public GeneralException() {
    }

    public GeneralException(String message) {
        super(message);
    }
}
