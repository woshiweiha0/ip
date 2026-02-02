/**
 * Represents a Todo task.
 * A Todo has no date or time attached to it.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    // Add the [T] task type indicator in front
    @Override
    public String toString() {
        return "[T]" + super.toString();    // Returns a string representation of a Todo task
    }
}
