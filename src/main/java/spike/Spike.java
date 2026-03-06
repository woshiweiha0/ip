/**
 * Spike is a simple command-line task manager.
 * It supports Todo, Deadline, and Event tasks.
 */
package spike;

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
                String command = Parser.getCommandWord(input);
                String rest = Parser.getArguments(input);

                switch (command) {
                case "bye":
                    ui.showGoodbye();
                    break;

                case "list":
                    ui.showTaskList(tasks.getTasks());
                    break;

                case "mark": {
                    int index = Parser.parseTaskIndex(rest, tasks.size());
                    tasks.mark(index);
                    Storage.saveTasks(tasks.getTasks());
                    ui.showMark(tasks.get(index));
                    break;
                }

                case "unmark": {
                    int index = Parser.parseTaskIndex(rest, tasks.size());
                    tasks.unmark(index);
                    Storage.saveTasks(tasks.getTasks());
                    ui.showUnmark(tasks.get(index));
                    break;
                }

                case "todo": {
                    Task todo = Parser.parseTodo(rest);
                    tasks.add(todo);
                    Storage.saveTasks(tasks.getTasks());
                    ui.showAdd(todo, tasks.size());
                    break;
                }

                case "deadline": {
                    Task deadline = Parser.parseDeadline(rest);
                    tasks.add(deadline);
                    Storage.saveTasks(tasks.getTasks());
                    ui.showAdd(deadline, tasks.size());
                    break;
                }

                case "event": {
                    Task event = Parser.parseEvent(rest);
                    tasks.add(event);
                    Storage.saveTasks(tasks.getTasks());
                    ui.showAdd(event, tasks.size());
                    break;
                }

                case "delete": {
                    int index = Parser.parseTaskIndex(rest, tasks.size());
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
}