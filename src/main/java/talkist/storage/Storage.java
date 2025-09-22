package talkist.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import talkist.parser.DateTimeParser;
import talkist.task.model.Deadline;
import talkist.task.model.Event;
import talkist.task.model.Task;
import talkist.task.model.Todo;

/**
 * Save a TaskList of user to hard disk, and load from a fixed filepath.
 * Also parse the task loaded from the storage file.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     * If the file or directory does not exist, it will be created,
     * and an empty task list will be returned.
     * @return An ArrayList of Task Object
     */
    public ArrayList<Task> load() {
        ensureFileExists();
        List<String> lines = readLinesFromFile();
        return parseTasksFromLines(lines);
    }

    /**
     * Ensures that the storage file and its parent directories exist.
     * Creates them if they do not exist.
     */
    private void ensureFileExists() {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating storage file: " + e.getMessage());
        }
    }

    /**
     * Reads all lines from the storage file.
     * @return A List of Strings, each representing a line in the file
     */
    private List<String> readLinesFromFile() {
        List<String> lines = new ArrayList<>();
        File file = new File(filePath);

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error reading tasks from file: " + e.getMessage());
        }

        return lines;
    }

    /**
     * Parses a list of task strings into Task objects.
     * @param lines List of strings representing tasks
     * @return An ArrayList of Task objects
     */
    private ArrayList<Task> parseTasksFromLines(List<String> lines) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            Task task = parseTask(line);
            if (task != null) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * Saves the provided task list to the storage file.
     * Uses Task.toString() for all tasks.
     */
    public void save(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private Task parseTask(String line) {
        try {
            String type = line.substring(1, 2);
            boolean done = line.charAt(4) == 'X';
            String tag = "";
            if (type.equals("T")) {
                int hashIdx = line.indexOf("#");
                if (hashIdx != -1) {
                    tag = line.substring(hashIdx + 1).trim();
                    line = line.substring(0, hashIdx).trim();
                }
                String desc = line.substring(7).trim();
                Todo todoTask = new Todo(desc);
                if (done) {
                    todoTask.mark();
                }
                if (!tag.isEmpty()) {
                    todoTask.setTag(tag);
                }
                return todoTask;

            } else if (type.equals("D")) {
                int idx = line.indexOf("(by:");
                int hashIdx = line.indexOf("#");
                if (hashIdx != -1 && hashIdx < idx) {
                    tag = line.substring(hashIdx + 1, idx).trim();
                    line = line.substring(0, hashIdx) + line.substring(idx);
                }
                String desc = line.substring(7, line.indexOf("(by:")).trim();
                String byStr = line.substring(line.indexOf("(by:") + 5, line.length() - 1).trim();
                LocalDateTime by = DateTimeParser.parseFromStorage(byStr);
                Deadline deadlineTask = new Deadline(desc, by);
                if (done) {
                    deadlineTask.mark();
                }
                if (!tag.isEmpty()) {
                    deadlineTask.setTag(tag);
                }
                return deadlineTask;

            } else if (type.equals("E")) {
                int idxFrom = line.indexOf("(from:");
                int idxTo = line.indexOf("to:");
                int hashIdx = line.indexOf("#");
                String desc;
                if (hashIdx != -1 && hashIdx < idxFrom) {
                    desc = line.substring(7, hashIdx).trim();
                    tag = line.substring(hashIdx + 1, idxFrom).trim();
                } else {
                    desc = line.substring(7, idxFrom).trim();
                }

                String fromStr = line.substring(idxFrom + 6, idxTo).trim();
                String toStr = line.substring(idxTo + 3, line.length() - 1).trim();
                LocalDateTime from = DateTimeParser.parseFromStorage(fromStr);
                LocalDateTime to = DateTimeParser.parseFromStorage(toStr);
                Event eventTask = new Event(desc, from, to);
                if (done) {
                    eventTask.mark();
                }
                if (!tag.isEmpty()) {
                    eventTask.setTag(tag);
                }
                return eventTask;
            }
        } catch (Exception e) {
            System.out.println("Error parsing line: " + line);
        }
        return null;
    }
}
