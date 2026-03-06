package spike;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String LINE = "__________________________________________";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Spike");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showGoodbye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showError(String message) {
        showLine();
        System.out.println("OOPS!!! " + message);
        showLine();
    }

    public void showLoadingError(String message) {
        System.out.println("Warning: " + message);
        System.out.println("Starting with an empty task list.");
    }

    public void showTaskList(ArrayList<Task> tasks) {
        showLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i += 1) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        showLine();
    }

    public void showMark(Task task) {
        showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        showLine();
    }

    public void showUnmark(Task task) {
        showLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        showLine();
    }

    public void showAdd(Task task, int size) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        showLine();
    }

    public void showDelete(Task task, int size) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        showLine();
    }

    public void close() {
        scanner.close();
    }
}