import java.util.Scanner;

public class Talkist {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int pointer = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println("This is Talkist, a chat bot based on DUKE. How can I help you?");

        while (true) {
            String input = sc.nextLine();

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
                int marking = Character.getNumericValue(input.charAt(5));
                Task t = tasks[marking - 1];
                t.mark();
                System.out.println("I've marked this task as done. Please check.");
                System.out.println(t.toString());
                continue;
            }

            if (input.startsWith("unmark ")) {
                int marking = Integer.parseInt(input.substring(7).trim());
                Task t = tasks[marking - 1];
                t.unmark();
                System.out.println("I've marked this task as not done. Please check.");
                System.out.println(t.toString());
                continue;
            }

            if (input.startsWith("todo ")) {
                String description = input.substring(5).trim();
                Task t = new Todo(description);
                tasks[pointer++] = t;
                System.out.println("added todo: " + description);
                continue;
            }

            if (input.startsWith("deadline ")) {
                String rest = input.substring(9).trim();
                int at = rest.indexOf("/by");
                String description = rest.substring(0, at).trim();
                String by = rest.substring(at + 3).trim();
                Task t = new Deadline(description, by);
                tasks[pointer++] = t;
                System.out.println("added Deadline: " + description + " by " + by);
                continue;
            }

            if (input.startsWith("event ")) {
                String rest = input.substring(6).trim();
                int fromAt = rest.indexOf("/from");
                int toAt = rest.indexOf("/to");
                String description = rest.substring(0, fromAt).trim();
                String from = rest.substring(fromAt + 5, toAt).trim();
                String to = rest.substring(toAt + 3).trim();
                Task t = new Event(description, from, to);
                tasks[pointer++] = t;
                System.out.println("added Event: " + description + " from " + from + " to " + to);
                continue;
            }
        }

        sc.close();
    }
}