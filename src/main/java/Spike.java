import java.util.Scanner;

public class Spike {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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

            printLine();
            System.out.println(input);
            printLine();
        }

        scanner.close();
    }

    private static void printLine() {
        System.out.println("__________________________________________");
    }
}
