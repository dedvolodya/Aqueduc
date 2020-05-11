package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.Code;

public class Utils {
    public static void showErrorWindow(String errorMsg) {
        Label secondLabel = new Label(errorMsg);
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);
        Scene secondScene = new Scene(secondaryLayout, 200, 100);
        Stage window = new Stage();
        window.setTitle("Error");
        window.setScene(secondScene);
        window.show();
    }

    public static void showCodeWindow(Code code) {
        GridPane gridPane = new GridPane();
        int[][] matrixCode = code.toMatrix();
        for (int i = 0; i < matrixCode.length; i++) {
            for (int j = 0; j < matrixCode[i].length; j++) {
                Label tf = new Label();
                tf.setPrefHeight(30);
                tf.setPrefWidth(30);
                tf.setAlignment(Pos.CENTER);
                tf.setText(String.valueOf(matrixCode[i][j]));
                gridPane.setRowIndex(tf, i);
                gridPane.setColumnIndex(tf, j);
                gridPane.getChildren().add(tf);
            }
        }

        Scene secondScene = new Scene(gridPane, 400, 400);
        Stage window = new Stage();
        window.setTitle("Maximum Code");
        window.setScene(secondScene);
        window.show();
    }
}
