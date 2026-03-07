/**
 * Handles all user interactions such as reading input and displaying output.
 */
package spike;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String LINE = "__________________________________________";
    private final Scanner scanner;

    /**
     * Constructs a UI object that reads user input from standard input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a divider line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Spike");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Reads one command from the user.
     *
     * @return User input trimmed of leading and trailing spaces.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays an error message.
     *
     * @param message Error message to display.
     */
    public void showError(String message) {
        showLine();
        System.out.println("OOPS!!! " + message);
        showLine();
    }

    /**
     * Displays an error message encountered while loading saved tasks.
     *
     * @param message Error message to display.
     */
    public void showLoadingError(String message) {
        System.out.println("Warning: " + message);
        System.out.println("Starting with an empty task list.");
    }

    /**
     * Displays all tasks in the list.
     *
     * @param tasks Tasks to display.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        showLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i += 1) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        showLine();
    }

    /**
     * Displays all tasks that match a keyword.
     *
     * @param tasks Matching tasks to display.
     */
    public void showMatchingTasks(ArrayList<Task> tasks) {
        showLine();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i += 1) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        showLine();
    }

    /**
     * Displays the message for a task being marked as done.
     *
     * @param task Task that was marked.
     */
    public void showMark(Task task) {
        showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        showLine();
    }

    /**
     * Displays the message for a task being unmarked.
     *
     * @param task Task that was unmarked.
     */
    public void showUnmark(Task task) {
        showLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        showLine();
    }

    /**
     * Displays the message for adding a task.
     *
     * @param task Task that was added.
     * @param size Current number of tasks after adding.
     */
    public void showAdd(Task task, int size) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        showLine();
    }

    /**
     * Displays the message for deleting a task.
     *
     * @param task Task that was deleted.
     * @param size Current number of tasks after deleting.
     */
    public void showDelete(Task task, int size) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        showLine();
    }

    /**
     * Closes the scanner used for reading user input.
     */
    public void close() {
        scanner.close();
    }
}