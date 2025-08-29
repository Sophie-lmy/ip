import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class Talkist {
    public static void main(String[] args) {
        Storage storage = new Storage("./data/Talkist.txt");

        ArrayList<Task> tasks = storage.load();

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
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(String.format("%d. %s", i + 1, tasks.get(i).toString()));
                    }
                    continue;
                }

                if (input.startsWith("mark ")) {
                    String numStr = input.substring(5).trim();
                    if (numStr.isEmpty()) throw new IllegalArgumentException();
                    int marking = Integer.parseInt(numStr);
                    if (marking < 1 || marking > tasks.size()) throw new ArrayIndexOutOfBoundsException();
                    Task t = tasks.get(marking - 1);
                    t.mark();
                    System.out.println("I've marked this task as done. Please check.");
                    System.out.println(t.toString());
                    storage.save(tasks);
                    continue;
                }

                if (input.startsWith("unmark ")) {
                    String numStr = input.substring(7).trim();
                    if (numStr.isEmpty()) throw new IllegalArgumentException();
                    int marking = Integer.parseInt(numStr);
                    if (marking < 1 || marking > tasks.size()) throw new ArrayIndexOutOfBoundsException();
                    Task t = tasks.get(marking - 1);
                    t.unmark();
                    System.out.println("I've marked this task as not done. Please check.");
                    System.out.println(t.toString());
                    storage.save(tasks);
                    continue;
                }

                if (input.startsWith("delete ")) {
                    String numStr = input.substring(7).trim();
                    if (numStr.isEmpty()) throw new IllegalArgumentException();
                    int remove = Integer.parseInt(numStr);
                    if (remove < 1 || remove > tasks.size()) throw new ArrayIndexOutOfBoundsException();
                    Task t = tasks.remove(remove - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + t.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    storage.save(tasks);
                    continue;
                }

                if (input.startsWith("todo ")) {
                    String description = input.substring(5).trim();
                    if (description.isEmpty()) throw new IllegalArgumentException();
                    Task t = new Todo(description);
                    tasks.add(t);
                    System.out.println("added todo: " + description);
                    storage.save(tasks);
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
                    tasks.add(t);
                    System.out.println("added Deadline: " + description + " by " + by);
                    storage.save(tasks);
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
                    tasks.add(t);
                    System.out.println("added Event: " + description + " from " + from + " to " + to);
                    storage.save(tasks);
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
