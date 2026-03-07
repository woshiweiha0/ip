/**
 * Represents the list of tasks in the task manager.
 * Provides operations to add, delete, mark, unmark, and search tasks.
 */
package spike;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list using an existing list of tasks.
     *
     * @param tasks Existing list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the given index.
     *
     * @param index Index of the task.
     * @return Task at the given index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes and returns the task at the given index.
     *
     * @param index Index of the task to delete.
     * @return Removed task.
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index Index of the task to mark.
     */
    public void mark(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param index Index of the task to unmark.
     */
    public void unmark(int index) {
        tasks.get(index).unmark();
    }

    /**
     * Finds tasks whose descriptions contain the given keyword.
     *
     * @param keyword Keyword to search for.
     * @return List of matching tasks.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }
}