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
        int mHeight = code.getSize();
        int mWidth = code.getBitRate();
        int x = 30, y = 30;
        for (int i = 0; i < mHeight; i++) {
            for (int j = 0; j < mWidth; j++) {
                Label tf = new Label();
                tf.setPrefHeight(y);
                tf.setPrefWidth(x);
                tf.setAlignment(Pos.CENTER);
                tf.setText(String.valueOf(matrixCode[i][j]));
                gridPane.setRowIndex(tf, i);
                gridPane.setColumnIndex(tf, j);
                gridPane.getChildren().add(tf);
            }
        }

        Scene secondScene = new Scene(gridPane, x*mWidth, y*mHeight);
        Stage window = new Stage();
        window.setTitle("Maximum Code");
        window.setScene(secondScene);
        window.show();
    }
}
