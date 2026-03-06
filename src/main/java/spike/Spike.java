/**
 * Spike is a simple command-line task manager.
 * It supports Todo, Deadline, and Event tasks.
 */
package spike;

import java.util.ArrayList;

public class Spike {

    public static void main(String[] args) {
        Ui ui = new Ui();

        TaskList tasks;
        try {
            tasks = new TaskList(Storage.loadTasks());
        } catch (SpikeException e) {
            tasks = new TaskList();
            ui.showLoadingError(e.getMessage());
        }

        ui.showWelcome();

        while (true) {
            String input = ui.readCommand();

            try {
                String[] parts = input.split("\\s+", 2);
                String command = parts[0];
                String rest = (parts.length == 2) ? parts[1].trim() : "";

                switch (command) {
                case "bye":
                    ui.showGoodbye();
                    break;

                case "list":
                    ui.showTaskList(tasks.getTasks());
                    break;

                case "mark": {
                    int index = parseTaskIndex(rest, tasks.size());
                    tasks.mark(index);
                    Storage.saveTasks(tasks.getTasks());
                    ui.showMark(tasks.get(index));
                    break;
                }

                case "unmark": {
                    int index = parseTaskIndex(rest, tasks.size());
                    tasks.unmark(index);
                    Storage.saveTasks(tasks.getTasks());
                    ui.showUnmark(tasks.get(index));
                    break;
                }

                case "todo": {
                    if (rest.isEmpty()) {
                        throw new SpikeException("The description of a todo cannot be empty.");
                    }
                    Task todo = new Todo(rest);
                    tasks.add(todo);
                    Storage.saveTasks(tasks.getTasks());
                    ui.showAdd(todo, tasks.size());
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
                    Storage.saveTasks(tasks.getTasks());
                    ui.showAdd(deadline, tasks.size());
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
                    Storage.saveTasks(tasks.getTasks());
                    ui.showAdd(event, tasks.size());
                    break;
                }

                case "delete": {
                    int index = parseTaskIndex(rest, tasks.size());
                    Task removed = tasks.delete(index);
                    Storage.saveTasks(tasks.getTasks());
                    ui.showDelete(removed, tasks.size());
                    break;
                }

                default:
                    throw new SpikeException("I'm sorry, but I don't know what that means :-(");
                }

                if (command.equals("bye")) {
                    break;
                }

            } catch (SpikeException e) {
                ui.showError(e.getMessage());
            }
        }

        ui.close();
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
}