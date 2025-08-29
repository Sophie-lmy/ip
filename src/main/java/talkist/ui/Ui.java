package talkist.ui;

import java.util.Scanner;

public class Ui {
	private final Scanner sc;

	public Ui() {
		sc = new Scanner(System.in);
	}

	/**
	 * Reads the next line of user input.
	 *
	 * @return the user's input
	 */
	public String readCommand() {
		return sc.nextLine();
	}

	/** Prints a message to the user. */
	public void show(String message) {
		System.out.println(message);
	}

	/** Closes the scanner when exiting. */
	public void close() {
		sc.close();
	}
}