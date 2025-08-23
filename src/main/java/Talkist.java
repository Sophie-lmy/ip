import java.util.Scanner;

public class Talkist {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int pointer = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println("This is Talkist, a chat bot based on DUKE. How can I help you?");

        while (true) {
            String input = sc.nextLine();

            try {
                if (input.equals("bye")) {
                    System.out.println("Bye! See you soon.");
                    break;
                }

                if (input.equals("list")) {
                    for (int i = 0; i < pointer; i++) {
                        System.out.println(String.format("%d. %s", i + 1, tasks[i].toString()));
                    }
                    continue;
                }

                if (input.startsWith("mark ")) {
                    String numStr = input.substring(5).trim();
                    if (numStr.isEmpty()) throw new IllegalArgumentException();
                    int marking = Integer.parseInt(numStr);
                    if (marking < 1 || marking > pointer) throw new ArrayIndexOutOfBoundsException();
                    Task t = tasks[marking - 1];
                    t.mark();
                    System.out.println("I've marked this task as done. Please check.");
                    System.out.println(t.toString());
                    continue;
                }

                if (input.startsWith("unmark ")) {
                    String numStr = input.substring(7).trim();
                    if (numStr.isEmpty()) throw new IllegalArgumentException();
                    int marking = Integer.parseInt(numStr);
                    if (marking < 1 || marking > pointer) throw new ArrayIndexOutOfBoundsException();
                    Task t = tasks[marking - 1];
                    t.unmark();
                    System.out.println("I've marked this task as not done. Please check.");
                    System.out.println(t.toString());
                    continue;
                }

                if (input.startsWith("todo ")) {
                    String description = input.substring(5).trim();
                    if (description.isEmpty()) throw new IllegalArgumentException();
                    Task t = new Todo(description);
                    tasks[pointer++] = t;
                    System.out.println("added todo: " + description);
                    continue;
                }

                if (input.startsWith("deadline ")) {
                    String rest = input.substring(9).trim();
                    int at = rest.indexOf("/by");
                    if (at == -1) throw new IllegalArgumentException();
                    String description = rest.substring(0, at).trim();
                    String by = rest.substring(at + 3).trim();
                    if (description.isEmpty() || by.isEmpty()) throw new IllegalArgumentException();
                    Task t = new Deadline(description, by);
                    tasks[pointer++] = t;
                    System.out.println("added Deadline: " + description + " by " + by);
                    continue;
                }

                if (input.startsWith("event ")) {
                    String rest = input.substring(6).trim();
                    int fromAt = rest.indexOf("/from");
                    int toAt = rest.indexOf("/to");
                    if (fromAt == -1 || toAt == -1 || toAt <= fromAt) throw new IllegalArgumentException();
                    String description = rest.substring(0, fromAt).trim();
                    String from = rest.substring(fromAt + 5, toAt).trim();
                    String to = rest.substring(toAt + 3).trim();
                    if (description.isEmpty() || from.isEmpty() || to.isEmpty()) throw new IllegalArgumentException();
                    Task t = new Event(description, from, to);
                    tasks[pointer++] = t;
                    System.out.println("added Event: " + description + " from " + from + " to " + to);
                    continue;
                }

                System.out.println("I didn't get that command. Try: list / todo / deadline / event / mark / unmark / bye");
            } catch (IllegalArgumentException e) {
                System.out.println("The command is incomplete. Please try again.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("That task number doesn't exist.");
            }
        }

        sc.close();
    }
}
