/**
 * Spike is a simple command-line task manager.
 * It supports Todo, Deadline, and Event tasks.
 */
package spike;

import java.util.Scanner;
import java.util.ArrayList;

public class Spike {
    private static final String LINE = "__________________________________________";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> tasks;
        try {
            tasks = Storage.loadTasks();
        } catch (SpikeException e) {
            // If loading fails, start with empty but still allow app to run
            tasks = new ArrayList<>();
            System.out.println("Warning: " + e.getMessage());
            System.out.println("Starting with an empty task list.");
        }

        printLine();
        System.out.println("Hello! I'm Spike");
        System.out.println("What can I do for you?");
        printLine();

        while (true) {
            String input = scanner.nextLine().trim();

            try {
                String[] parts = input.split("\\s+", 2);
                String command = parts[0];
                String rest = (parts.length == 2) ? parts[1].trim() : "";

                switch (command) {
                case "bye":
                    printLine();
                    System.out.println("Bye. Hope to see you again soon!");
                    printLine();
                    break;

                case "list":
                    printLine();
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i += 1) {
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }
                    printLine();
                    break;

                case "mark": {
                    int index = parseTaskIndex(rest, tasks.size());
                    tasks.get(index).markAsDone();

                    Storage.saveTasks(tasks);

                    printLine();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(index));
                    printLine();
                    break;
                }

                case "unmark": {
                    int index = parseTaskIndex(rest, tasks.size());
                    tasks.get(index).unmark();

                    Storage.saveTasks(tasks);

                    printLine();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(index));
                    printLine();
                    break;
                }

                case "todo": {
                    if (rest.isEmpty()) {
                        throw new SpikeException("The description of a todo cannot be empty.");
                    }
                    Task todo = new Todo(rest);
                    tasks.add(todo);

                    Storage.saveTasks(tasks);

                    printLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + todo);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    printLine();
                    break;
                }

                case "deadline": {
                    if (!rest.contains("/by")) {
                        throw new SpikeException("A deadline must have '/by <time>'.");
                    }
                    String[] dParts = rest.split("/by", 2);
                    String description = dParts[0].trim();
                    String by = dParts[1].trim();

                    if (description.isEmpty()) {
                        throw new SpikeException("The description of a deadline cannot be empty.");
                    }
                    if (by.isEmpty()) {
                        throw new SpikeException("The time for a deadline cannot be empty. Use: deadline <desc> /by <time>");
                    }

                    Task deadline = new Deadline(description, by);
                    tasks.add(deadline);

                    Storage.saveTasks(tasks);

                    printLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + deadline);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    printLine();
                    break;
                }

                case "event": {
                    if (!rest.contains("/from") || !rest.contains("/to")) {
                        throw new SpikeException("An event must have '/from <start>' and '/to <end>'.");
                    }

                    String[] fromSplit = rest.split("/from", 2);
                    String description = fromSplit[0].trim();

                    String[] toSplit = fromSplit[1].split("/to", 2);
                    String from = toSplit[0].trim();
                    String to = toSplit[1].trim();

                    if (description.isEmpty()) {
                        throw new SpikeException("The description of an event cannot be empty.");
                    }
                    if (from.isEmpty() || to.isEmpty()) {
                        throw new SpikeException("Event start/end time cannot be empty. Use: event <desc> /from <start> /to <end>");
                    }

                    Task event = new Event(description, from, to);
                    tasks.add(event);

                    Storage.saveTasks(tasks);

                    printLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + event);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    printLine();
                    break;
                }

                case "delete": {
                    int index = parseTaskIndex(rest, tasks.size());
                    Task removed = tasks.remove(index);

                    Storage.saveTasks(tasks);

                    printLine();
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + removed);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    printLine();
                    break;
                }

                default:
                    throw new SpikeException("I'm sorry, but I don't know what that means :-(");
                }

                if (command.equals("bye")) {
                    break;
                }

            } catch (SpikeException e) {
                printLine();
                System.out.println("OOPS!!! " + e.getMessage());
                printLine();
            }
        }

        scanner.close();
    }

    private static int parseTaskIndex(String raw, int taskCount) throws SpikeException {
        String trimmed = raw.trim();

        if (trimmed.isEmpty()) {
            throw new SpikeException("Please specify a task number.");
        }

        int number;
        try {
            number = Integer.parseInt(trimmed);
        } catch (NumberFormatException e) {
            throw new SpikeException("Task number must be a number.");
        }

        if (number < 1 || number > taskCount) {
            throw new SpikeException("Task number is out of range.");
        }

        return number - 1;
    }

    private static void printLine() {
        System.out.println(LINE);
    }
}
