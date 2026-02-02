/**
 * Represents an Event task.
 * An Event has a start time and an end time.
 */
public class Event extends Task {
    protected String from;  // Start time
    protected String to;    // End time

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    // Includes the start and end times
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";   // Returns the string representation of an Event task
    }
}

