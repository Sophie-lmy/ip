package talkist.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import talkist.Talkist;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private VBox dialogContainer;
	@FXML
	private TextField userInput;
	@FXML
	private Button sendButton;

	private Talkist talkist;

	private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
	private Image botImage = new Image(this.getClass().getResourceAsStream("/images/chatbot.png"));

	/**
	 * Initialize method called by JavaFX after FXML elements are loaded.
	 * Binds the scroll bar to follow new messages automatically.
	 */
	@FXML
	public void initialize() {
		scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

		String welcomeMessage = "Konnichiwa! This is Talkist.";
		dialogContainer.getChildren().add(
				DialogBox.getBotDialog(welcomeMessage, botImage)
		);
	}


	/**
	 * Injects the Talkist instance into this controller.
	 * @param t Talkist instance containing the chatbot logic
	 */
	public void setTalkist(Talkist t) {
		talkist = t;
	}

	/**
	 * Processes the user’s input through Talkist and displays the chatbot’s response.
	 */
	@FXML
	private void handleUserInput() {
		String input = userInput.getText();

		String response = talkist.getResponse(input);

		// Add user and bot dialog boxes to the VBox container
		dialogContainer.getChildren().addAll(
				DialogBox.getUserDialog(input, userImage),
				DialogBox.getBotDialog(response, botImage)
		);

		userInput.clear();
	}
}
