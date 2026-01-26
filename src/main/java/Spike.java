import java.util.Scanner;

public class Spike {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int taskCount = 0;

        printLine();
        System.out.println("Hello! I'm Spike");
        System.out.println("What can I do for you?");
        printLine();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            }

            if (input.equals("list")) {
                printLine();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i += 1) {
                    System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                }
                printLine();
                continue;
            }

            if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                tasks[index].markAsDone();

                printLine();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + tasks[index].getDescription());
                printLine();
                continue;
            }

            if(input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                tasks[index].unmark();

                printLine();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[ ] " + tasks[index].getDescription());
                printLine();
                continue;
            }

            tasks[taskCount] = new Task(input);
            taskCount += 1;

            printLine();
            System.out.println("added: " + input);
            printLine();

        }

        scanner.close();
    }

    private static void printLine() {
        System.out.println("__________________________________________");
    }
}
