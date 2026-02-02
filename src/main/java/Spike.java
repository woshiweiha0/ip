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
            String input = scanner.nextLine();

            // Exit command
            if (input.equals("bye")) {
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            }

            // List all tasks
            if (input.equals("list")) {
                printLine();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i += 1) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
                printLine();
                continue;
            }

            // Mark a task as done
            if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                tasks[index].markAsDone();

                printLine();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[index]);
                printLine();
                continue;
            }

            // Mark a task as not done
            if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                tasks[index].unmark();

                printLine();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[index]);
                printLine();
                continue;
            }

            // Add a Todo task
            if (input.startsWith("todo ")) {
                String description = input.substring(5);
                Task task = new Todo(description);

                tasks[taskCount] = task;
                taskCount++;

                printLine();
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                printLine();
                continue;
            }

            // Add a Deadline task
            if (input.startsWith("deadline ")) {
                String rest = input.substring(9);
                String[] parts = rest.split("/by");
                Task task = new Deadline(parts[0], parts[1]);

                tasks[taskCount] = task;
                taskCount++;

                printLine();
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                printLine();
                continue;
            }

            // Add an Event task
            if (input.startsWith("event ")) {
                String rest = input.substring(6);
                String[] fromSplit = rest.split("/from");
                String description = fromSplit[0];
                String[] toSplit = fromSplit[1].split("/to");

                Task task = new Event(description, toSplit[0], toSplit[1]);

                tasks[taskCount] = task;
                taskCount++;

                printLine();
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                printLine();
                continue;
            }
        }

        scanner.close();
    }

    private static void printLine() {
        System.out.println(LINE);
    }
}
