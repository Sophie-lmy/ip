import java.util.Scanner;

public class Talkist {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("This is Talkist, a chat bot based on DUKE. How can I help you?");

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye! See you soon.");
                break;
            } else {
                System.out.println(input);
            }
        }

        sc.close();
    }
}