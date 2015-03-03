import java.io.IOException;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.util.Duration;

public class Preloader extends Application {
	public static final String SPLASH_IMAGE = "logo.png";

	public Stage primaryStage;
	public BorderPane rootLayout;
	public static void main(String[] args) throws Exception {
		launch(args);
	}

	
	/*The method used to show main stage and calls other methods*/
	@Override
	public void start(final Stage initStage) throws Exception {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"KDAF_Splash.fxml"));
		try {
			Parent root = fxmlLoader.load();

			Stage stage = new Stage();
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.setScene(new Scene(root, 600, 400));
			stage.show();
			PauseTransition delay = new PauseTransition(Duration.seconds(5));
			delay.setOnFinished(event -> {
				stage.close();
				showMainStage();
			});

			delay.play();

		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

	};

	private void showMainStage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Preloader.class.getResource("RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			Stage stage = new Stage();
			stage.setTitle("Activation");
			Scene scene = new Scene(rootLayout);
			stage.setScene(scene);
			stage.initStyle(StageStyle.UNDECORATED);

			stage.show();
			showPersonOverview();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showPersonOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource("activation wizard.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			personOverview.setPrefHeight(400);
		
			rootLayout.setCenter(personOverview);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
}
