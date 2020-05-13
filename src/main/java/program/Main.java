package program;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.net.URL;

import javafx.application.Application;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/main-window.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        primaryStage.setTitle("Aqueduct");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
