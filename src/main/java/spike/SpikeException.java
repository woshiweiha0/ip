/**
 * Represents an exception specific to the Spike application.
 */
package spike;

public class SpikeException extends Exception {

    /**
     * Constructs a SpikeException with the given error message.
     *
     * @param message Error message to be shown.
     */
    public SpikeException(String message) {
        super(message);
    }
}