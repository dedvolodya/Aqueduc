package program;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ui.MainWindowController;
import utils.Code;

import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import javafx.application.Application;

public class Main extends Application {

    public static void main(String[] args) {
//        CodeResolver cr = new CodeResolver("code1");
//        HashSet<Code> res = cr.loadAndContinueSearch();
//                 //cr.findNewCodes(8, 3);
//        Iterator resI = res.iterator();
//        System.out.print(resI.next());
//        System.out.print("next");
//        System.out.print(resI.next());
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/main-window.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        primaryStage.setTitle("Aqueduce");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
