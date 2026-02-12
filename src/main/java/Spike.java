/**
 * Spike is a simple command-line task manager.
 * It supports Todo, Deadline, and Event tasks.
 */

import java.util.Scanner;

public class Spike {
    private static final String LINE = "__________________________________________";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Stores all tasks using polymorphism
        Task[] tasks = new Task[100];
        int taskCount = 0;

        printLine();
        System.out.println("Hello! I'm Spike");
        System.out.println("What can I do for you?");
        printLine();

        // Main input loop
        while (true) {
            String input = scanner.nextLine().trim();

            try {
                // Split into: command + rest (at most 2 parts)
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
                    for (int i = 0; i < taskCount; i += 1) {
                        System.out.println((i + 1) + "." + tasks[i]);
                    }
                    printLine();
                    break;

                case "mark": {
                    int index = parseTaskIndex(rest, taskCount); // your helper method
                    tasks[index].markAsDone();
                    printLine();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks[index]);
                    printLine();
                    break;
                }

                case "unmark": {
                    int index = parseTaskIndex(rest, taskCount);
                    tasks[index].unmark();
                    printLine();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks[index]);
                    printLine();
                    break;
                }

                case "todo":
                    if (rest.isEmpty()) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    Task todo = new Todo(rest);
                    tasks[taskCount++] = todo;

                    printLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + todo);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    printLine();
                    break;

                case "deadline": {
                    // expecting: <desc> /by <time>
                    if (!rest.contains("/by")) {
                        throw new DukeException("A deadline must have '/by <time>'.");
                    }
                    String[] dParts = rest.split("/by", 2);
                    String description = dParts[0].trim();
                    String by = dParts[1].trim();

                    if (description.isEmpty()) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    }
                    if (by.isEmpty()) {
                        throw new DukeException("The time for a deadline cannot be empty. Use: deadline <desc> /by <time>");
                    }

                    Task deadline = new Deadline(description, by);
                    tasks[taskCount++] = deadline;

                    printLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + deadline);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    printLine();
                    break;
                }

                case "event": {
                    // expecting: <desc> /from <start> /to <end>
                    if (!rest.contains("/from") || !rest.contains("/to")) {
                        throw new DukeException("An event must have '/from <start>' and '/to <end>'.");
                    }

                    String[] fromSplit = rest.split("/from", 2);
                    String description = fromSplit[0].trim();

                    String[] toSplit = fromSplit[1].split("/to", 2);
                    String from = toSplit[0].trim();
                    String to = toSplit[1].trim();

                    if (description.isEmpty()) {
                        throw new DukeException("The description of an event cannot be empty.");
                    }
                    if (from.isEmpty() || to.isEmpty()) {
                        throw new DukeException("Event start/end time cannot be empty. Use: event <desc> /from <start> /to <end>");
                    }

                    Task event = new Event(description, from, to);
                    tasks[taskCount++] = event;

                    printLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + event);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    printLine();
                    break;
                }

                default:
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }

                if (command.equals("bye")) {
                    break;
                }

            } catch (DukeException e) {
                printLine();
                System.out.println("OOPS!!! " + e.getMessage());
                printLine();
            }
        }

        scanner.close();
    }


    private static int parseTaskIndex(String raw, int taskCount) throws DukeException {
        String trimmed = raw.trim();

        if (trimmed.isEmpty()) {
            throw new DukeException("Please specify a task number.");
        }

        int number;
        try {
            number = Integer.parseInt(trimmed);
        } catch (NumberFormatException e) {
            throw new DukeException("Task number must be a number.");
        }

        if (number < 1 || number > taskCount) {
            throw new DukeException("Task number is out of range.");
        }

        return number - 1;
    }


    private static void printLine() {
        System.out.println(LINE);
    }
}
