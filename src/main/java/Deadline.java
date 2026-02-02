/**
 * Represents a Deadline task.
 * A Deadline must be completed by a certain time.
 */
public class Deadline extends Task {
    protected String by;    // Deadline time (treated as a string)

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    // Includes the deadline time
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";  // Returns the string representation of a Deadline task
    }
}
