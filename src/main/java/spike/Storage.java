package spike;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;
    private static final String DELIM = " \\| ";

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public ArrayList<Task> loadTasks() throws SpikeException {
        ensureParentFolderExists();

        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }

        List<String> lines;
        try {
            lines = Files.readAllLines(filePath);
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
                corruptedCount++;
            }
        }

        if (corruptedCount > 0) {
            System.out.println("Warning: " + corruptedCount + " corrupted line(s) were skipped while loading.");
        }

        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) throws SpikeException {
        ensureParentFolderExists();

        ArrayList<String> lines = new ArrayList<>();
        for (Task t : tasks) {
            lines.add(toSaveString(t));
        }

        try {
            Files.write(filePath, lines,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new SpikeException("Could not write save file: " + e.getMessage());
        }
    }

    private void ensureParentFolderExists() throws SpikeException {
        try {
            Files.createDirectories(filePath.getParent());
        } catch (IOException e) {
            throw new SpikeException("Could not create data folder: " + e.getMessage());
        }
    }

    private Task parseLine(String line) throws SpikeException {
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

    private String toSaveString(Task task) throws SpikeException {
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