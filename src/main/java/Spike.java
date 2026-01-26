import java.util.Scanner;

public class Spike {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] items = new String[100];
        int itemCount = 0;

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
                for (int i = 0; i < itemCount; i += 1) {
                    System.out.println((i + 1) + ". " + items[i]);
                }
                printLine();
            } else {
                items[itemCount] = input;
                itemCount += 1;

                printLine();
                System.out.println("added: " + input);
                printLine();
            }
        }

        scanner.close();
    }

    private static void printLine() {
        System.out.println("__________________________________________");
    }
}
