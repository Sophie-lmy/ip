public class Todo extends Task{
    Todo(String description) { super(description); }

    @Override
    protected String typePrefix() { return "T"; }
}
