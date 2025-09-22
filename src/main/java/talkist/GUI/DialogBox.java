package talkist.GUI;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
	@FXML
	private Label dialog;
	@FXML
	private ImageView displayPicture;

	private DialogBox(String text, Image img) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
			fxmlLoader.setController(this);
			fxmlLoader.setRoot(this);
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		dialog.setText(text);
		displayPicture.setImage(img);
		dialog.setPadding(new Insets(10));
	}

	/**
	 * Flips the dialog box such that the ImageView is on the left and text on the right.
	 */
	private void flip() {
		ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
		Collections.reverse(tmp);
		getChildren().setAll(tmp);
		setAlignment(Pos.TOP_LEFT);
	}

	/**
	 * Returns a DialogBox for the user input with white background and black text.
	 * AI-assisted code: Used ChatGPT to provide the color setting logic.
	 */
	public static DialogBox getUserDialog(String text, Image img) {
		var db = new DialogBox(text, img);
		db.dialog.setBackground(
				new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY))
		);
		db.dialog.setTextFill(Color.BLACK);
		return db;
	}

	/**
	 * Returns a DialogBox for the bot output with black background and white text.
	 * AI-assisted code: Used ChatGPT to provide the flip logic and direct color settings.
	 */
	public static DialogBox getBotDialog(String text, Image img) {
		var db = new DialogBox(text, img);
		db.flip(); // Bot dialog on the left
		db.dialog.setBackground(
				new Background(new BackgroundFill(Color.BLACK, new CornerRadii(10), Insets.EMPTY))
		);
		db.dialog.setTextFill(Color.WHITE);
		return db;
	}
}
