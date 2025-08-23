import java.util.Scanner;

public class Talkist {
    public static void main(String[] args) {
        String[] lst = new String[100];
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
                    System.out.println(String.format("%d. %s", i + 1, lst[i]));
                }
                continue;
            }

            lst[pointer++] = input;
            System.out.println("added: " + input);
        }

        sc.close();
    }
}