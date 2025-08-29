import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param filePath Path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     * If the file or directory does not exist, it will be created,
     * and an empty task list will be returned.
     *
     * @return A list of tasks loaded from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return tasks;
            }

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the provided task list to the storage file.
     *
     * @param tasks List of tasks to save.
     * @throws IOException If an I/O error occurs while saving.
     */
    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Parse a task from its toString() representation.
     *
     * @param line One line from the storage file.
     * @return Task object.
     * @throws IllegalArgumentException If the line format is invalid.
     */
    private Task parseTask(String line) {
        try {
            // Formats references:
            // [T][X] desc
            // [D][ ] desc (by: time)
            // [E][ ] desc (from: time1 to: time2)

            String type = line.substring(1, 2);     // T/D/E
            boolean done = line.charAt(4) == 'X';

            if (type.equals("T")) {
                String desc = line.substring(7); // after "] "
                Todo t = new Todo(desc);
                if (done) t.mark();
                return t;
            } else if (type.equals("D")) {
                int idx = line.indexOf("(by:");
                String desc = line.substring(7, idx).trim();
                String by = line.substring(idx + 5, line.length() - 1).trim();
                Deadline d = new Deadline(desc, by);
                if (done) d.mark();
                return d;
            } else if (type.equals("E")) {
                int idxFrom = line.indexOf("(from:");
                int idxTo = line.indexOf("to:");
                String desc = line.substring(7, idxFrom).trim();
                String from = line.substring(idxFrom + 6, idxTo).replace("to", "").trim();
                String to = line.substring(idxTo + 3, line.length() - 1).trim();
                Event e = new Event(desc, from, to);
                if (done) e.mark();
                return e;
            }
        } catch (Exception e) {
            System.out.println("Error parsing line: " + line);
        }
        return null;
    }
}