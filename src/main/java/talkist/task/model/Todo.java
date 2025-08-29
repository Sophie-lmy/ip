package talkist.task.model;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    protected String typePrefix() { return "T"; }

    @Override
    public String toString() {
        return String.format("[%s]%s",
                typePrefix(),
                base());
    }
}
