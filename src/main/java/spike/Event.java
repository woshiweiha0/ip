/**
 * Represents an event task.
 * An event has a start time and an end time.
 */
package spike;

public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an event task with the given description, start time, and end time.
     *
     * @param description Description of the event task.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of this event task.
     *
     * @return String form of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}