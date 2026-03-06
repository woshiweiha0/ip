package spike;

public class Parser {

    public static String getCommandWord(String input) {
        String[] parts = input.trim().split("\\s+", 2);
        return parts[0];
    }

    public static String getArguments(String input) {
        String[] parts = input.trim().split("\\s+", 2);
        return parts.length == 2 ? parts[1].trim() : "";
    }

    public static int parseTaskIndex(String raw, int taskCount) throws SpikeException {
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

    public static Todo parseTodo(String rest) throws SpikeException {
        if (rest.isEmpty()) {
            throw new SpikeException("The description of a todo cannot be empty.");
        }
        return new Todo(rest);
    }

    public static Deadline parseDeadline(String rest) throws SpikeException {
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

        return new Deadline(description, by);
    }

    public static Event parseEvent(String rest) throws SpikeException {
        if (!rest.contains("/from") || !rest.contains("/to")) {
            throw new SpikeException("An event must have '/from <start>' and '/to <end>'.");
        }

        String[] fromSplit = rest.split("/from", 2);
        String description = fromSplit[0].trim();

        String[] toSplit = fromSplit[1].split("/to", 2);
        if (toSplit.length < 2) {
            throw new SpikeException("An event must have '/from <start>' and '/to <end>'.");
        }

        String from = toSplit[0].trim();
        String to = toSplit[1].trim();

        if (description.isEmpty()) {
            throw new SpikeException("The description of an event cannot be empty.");
        }
        if (from.isEmpty() || to.isEmpty()) {
            throw new SpikeException("Event start/end time cannot be empty. Use: event <desc> /from <start> /to <end>");
        }

        return new Event(description, from, to);
    }
}
