public class Deadline extends Task {
    private String by;

    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    protected String typePrefix() { return "D"; }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", typePrefix(), base(), by);
    }
}
