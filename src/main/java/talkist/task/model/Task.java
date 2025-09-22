package talkist.task.model;

/**
 * A <code>Task</code> is an object with a description String and
 * a boolean representing completion status.
 */
public abstract class Task {
    private String description;
    private boolean isDone;
    private String tag;

    /**
     * Returns the type prefix of the task.
     * "T" for Todo, "D" for Deadline, "E" for Event
     *
     * @return the task type prefix
     */
    protected abstract String typePrefix();

    /**
     * Creates a new task with the given description.
     *
     * @param description description of the task
     * @throws NullPointerException     if description is null
     * @throws IllegalArgumentException if description is empty or blank
     */
    public Task(String description) {
        if (description == null) {
            throw new NullPointerException("Description cannot be null");
        }
        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        this.description = description;
        this.isDone = false;
        tag = "";
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    /**
     * Checks if the task is isDone.
     *
     * @return true if the task is isDone, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the base string representation of the task, including
     * its completion status and description.
     *
     * @return formatted string with status and description
     */
    protected String base() {
        return String.format("[%s] %s #%s",
                isDone ? "X" : " ",
                description, tag);
    }

    /**
     * Returns the description of this task.
     *
     * @return description string of the task
     */
    public String getDescription() {
        return this.description;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return this.tag;
    }
}
