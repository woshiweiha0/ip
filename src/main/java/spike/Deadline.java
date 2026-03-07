/**
 * Represents a deadline task.
 * A deadline must be completed by a specified time.
 */
package spike;

public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a deadline task with the given description and deadline.
     *
     * @param description Description of the deadline task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of this deadline task.
     *
     * @return String form of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}