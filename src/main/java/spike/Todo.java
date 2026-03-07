/**
 * Represents a todo task.
 * A todo has only a description and no associated date or time.
 */
package spike;

public class Todo extends Task {

    /**
     * Constructs a todo task with the given description.
     *
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of this todo task.
     *
     * @return String form of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}