package talkist.GUI;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import talkist.Talkist;

/**
 * A GUI for Talkist using FXML.
 */
public class Main extends Application {

	private Talkist talkist = new Talkist("./data/Talkist.txt");

	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
			AnchorPane ap = fxmlLoader.load();
			Scene scene = new Scene(ap);
			stage.setScene(scene);
			stage.setMinHeight(220);
			stage.setMinWidth(417);
			fxmlLoader.<MainWindow>getController().setTalkist(talkist);  // inject the instance
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
