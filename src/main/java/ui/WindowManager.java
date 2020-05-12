package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.Code;

import java.io.IOException;
import java.net.URL;

public class WindowManager {

    public static void showErrorWindow(String errorMsg) {
        showSimpleInfoWindow("Error", errorMsg);
    }

    public static void showSimpleInfoWindow(String title, String msg) {
        Label secondLabel = new Label(msg);
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);
        Scene secondScene = new Scene(secondaryLayout, 200, 100);
        Stage window = new Stage();
        window.setTitle(title);
        window.setScene(secondScene);
        window.show();
    }

    private static void updateCode(Code code, TextField[][] textFields) {
        int[][] matrixCode = code.toMatrix();
        for(int i = 0; i < code.getSize(); i++) {
            for (int j = 0; j < code.getBitRate(); j++) {
                textFields[i][j].setText(String.valueOf(matrixCode[i][j]));
            }
        }
    }

    public static void showCodeWindow(Code code) {
        GridPane gridPane = new GridPane();
        int[][] matrixCode = code.toMatrix();
        int mHeight = code.getSize();
        int mWidth = code.getBitRate();
        int x = 30, y = 30;
        int i = 0;
        int j = 0;
        int leftWidth = 150;
        TextField[][] textFields = new TextField[mHeight][mWidth];
        for (i = 0; i < mHeight; i++) {
            Label wordCount = new Label("word " + i + " :");
            wordCount.setPrefWidth(leftWidth);
            wordCount.setAlignment(Pos.CENTER);
            gridPane.setRowIndex(wordCount, i);
            gridPane.setColumnIndex(wordCount, 0);
            gridPane.getChildren().add(wordCount);
            for (j = 0; j < mWidth; j++) {
                TextField tf = new TextField();
                tf.setPrefHeight(y);
                tf.setPrefWidth(x);
                tf.setAlignment(Pos.CENTER);
                tf.setText(String.valueOf(matrixCode[i][j]));
                gridPane.setRowIndex(tf, i);
                gridPane.setColumnIndex(tf, j + 1);
                textFields[i][j] = tf;
                gridPane.getChildren().add(tf);
            }
        }
        Button sortButton = new Button("sort");
        sortButton.setPrefWidth(leftWidth);
        gridPane.setRowIndex(sortButton, ++i);
        gridPane.setColumnIndex(sortButton, 0);
        sortButton.setOnAction(actionEvent -> { code.sort(); updateCode(code, textFields); });
        gridPane.getChildren().add(sortButton);

        Button codeDistanceButton = new Button("code distance");
        codeDistanceButton.setPrefWidth(leftWidth);
        gridPane.setRowIndex(codeDistanceButton, ++i);
        gridPane.setColumnIndex(codeDistanceButton, 0);
        codeDistanceButton.setOnAction(actionEvent -> { code.sort(); showSimpleInfoWindow("Code distance",
                String.valueOf(code.getDistance())); });
        gridPane.getChildren().add(codeDistanceButton);

        TextField distanceField = new TextField();
        distanceField.setPrefHeight(y);
        distanceField.setPrefWidth(x);
        distanceField.setAlignment(Pos.CENTER);
        gridPane.setRowIndex(distanceField, ++i);
        gridPane.setColumnIndex(distanceField,  1);
        gridPane.getChildren().add(distanceField);
        Button wordDistanceButton = new Button("word distance");
        wordDistanceButton.setPrefWidth(leftWidth);
        gridPane.setRowIndex(wordDistanceButton, i);
        gridPane.setColumnIndex(wordDistanceButton, 0);
        wordDistanceButton.setOnAction(actionEvent -> { code.sort(); showSimpleInfoWindow("Word distance",
                String.valueOf(code.getDistance(Integer.parseInt(distanceField.getText())))); });
        gridPane.getChildren().add(wordDistanceButton);

        Scene secondScene = new Scene(gridPane, x*mWidth + leftWidth, y*mHeight + x * 3);
        Stage window = new Stage();
        window.setTitle("Maximum Code");
        window.setScene(secondScene);
        window.show();
    }

    public static void showNewCodeWindow(String codeName) throws IOException {
        showCodeManipulationWindow("/find-new-code.fxml", "Search new code", codeName);
    }

    public static void showLoadCodeWindow(String codeName) throws IOException {
        showCodeManipulationWindow("/load-code.fxml", "Load saved code", codeName);
    }


    private static void showCodeManipulationWindow(String file, String title, String codeName) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = WindowManager.class.getResource(file);
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        SearchCodeAbstractController controller = loader.getController();
        controller.setCodeName(codeName);

        Stage window = new Stage();
        window.setTitle(title);
        window.setScene(new Scene(root));
        window.show();
    }
}
