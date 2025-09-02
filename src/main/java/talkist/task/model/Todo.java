package talkist.task.model;

/**
 * Represents a Todo task, a Task with only a description and no time.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo task with the given description.
     *
     * @param description description of the todo
     * @throws NullPointerException if description is null
     * @throws IllegalArgumentException if description is empty
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type prefix of this task: "T" for Todo.
     *
     * @return task type prefix
     */
    @Override
    protected String typePrefix() {
        return "T";
    }

    /**
     * Returns a string representation of the Todo task,
     * including its status and description.
     *
     * @return formatted string of the task
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", typePrefix(), base());
    }
}
