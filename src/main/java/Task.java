/**
 * Represents a generic task.
 * All task types (Todo, Deadline, Event) share these common properties.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    // Constructs a task with the given description
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    // Subclasses extend this via super.toString()
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;  // Return the basic string representation of a task
    }
}
