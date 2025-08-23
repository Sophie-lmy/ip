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
                int marking = Character.getNumericValue(input.charAt(7));
                Task t = tasks[marking - 1];
                t.unmark();
                System.out.println("I've marked this task as not done. Please check.");
                System.out.println(t.toString());
                continue;
            }

            tasks[pointer++] = new Task(input);
            System.out.println("added: " + input);
        }

        sc.close();
    }
}