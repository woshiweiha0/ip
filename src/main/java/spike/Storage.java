package spike;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final Path FILE_PATH = Paths.get("data", "spike.txt");
    private static final String DELIM = " \\| ";

    public static ArrayList<Task> loadTasks() throws SpikeException {
        ensureParentFolderExists();

        if (!Files.exists(FILE_PATH)) {
            // First run: no file yet -> start empty
            return new ArrayList<>();
        }

        List<String> lines;
        try {
            lines = Files.readAllLines(FILE_PATH);
        } catch (IOException e) {
            throw new SpikeException("Could not read save file: " + e.getMessage());
        }

        ArrayList<Task> tasks = new ArrayList<>();
        int corruptedCount = 0;

        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.isEmpty()) {
                continue;
            }

            try {
                Task t = parseLine(trimmed);
                tasks.add(t);
            } catch (Exception ex) {
                // Stretch goal: handle corrupted lines gracefully
                corruptedCount++;
            }
        }

        if (corruptedCount > 0) {
            System.out.println("Warning: " + corruptedCount + " corrupted line(s) were skipped while loading.");
        }

        return tasks;
    }

    public static void saveTasks(ArrayList<Task> tasks) throws SpikeException {
        ensureParentFolderExists();

        ArrayList<String> lines = new ArrayList<>();
        for (Task t : tasks) {
            lines.add(toSaveString(t));
        }

        try {
            Files.write(FILE_PATH, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new SpikeException("Could not write save file: " + e.getMessage());
        }
    }

    private static void ensureParentFolderExists() throws SpikeException {
        try {
            Files.createDirectories(FILE_PATH.getParent());
        } catch (IOException e) {
            throw new SpikeException("Could not create data folder: " + e.getMessage());
        }
    }

    private static Task parseLine(String line) throws SpikeException {
        // We split on " | " (space-pipe-space) to match the Spike example format
        String[] parts = line.split(DELIM);

        if (parts.length < 3) {
            throw new SpikeException("Corrupted line (too few parts): " + line);
        }

        String type = parts[0].trim();
        String doneStr = parts[1].trim();
        String desc = parts[2].trim();

        boolean isDone;
        if (doneStr.equals("1")) {
            isDone = true;
        } else if (doneStr.equals("0")) {
            isDone = false;
        } else {
            throw new SpikeException("Corrupted done flag: " + line);
        }

        Task task;
        switch (type) {
        case "T":
            task = new Todo(desc);
            break;
        case "D":
            if (parts.length < 4) {
                throw new SpikeException("Corrupted deadline line: " + line);
            }
            task = new Deadline(desc, parts[3].trim());
            break;
        case "E":
            if (parts.length < 5) {
                throw new SpikeException("Corrupted event line: " + line);
            }
            task = new Event(desc, parts[3].trim(), parts[4].trim());
            break;
        default:
            throw new SpikeException("Unknown task type: " + line);
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    private static String toSaveString(Task task) throws SpikeException {
        String done = task.isDone ? "1" : "0";

        if (task instanceof Todo) {
            return "T | " + done + " | " + task.description;
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return "D | " + done + " | " + d.description + " | " + d.by;
        } else if (task instanceof Event) {
            Event e = (Event) task;
            return "E | " + done + " | " + e.description + " | " + e.from + " | " + e.to;
        } else {
            throw new SpikeException("Unknown task type when saving.");
        }
    }
}
