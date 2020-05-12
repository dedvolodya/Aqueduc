package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MainWindowController {

    @FXML private TextField codeName;

    public void startNewButtonClick() {
        try {
            WindowManager.showNewCodeWindow(codeName.getText());
        } catch (IOException e) {
            e.printStackTrace();
            WindowManager.showErrorWindow("Wrong code name");
        }
    }


    public void loadCodeButtonClick() {
        try {
            WindowManager.showLoadCodeWindow(codeName.getText());
        } catch (IOException e) {
            e.printStackTrace();
            WindowManager.showErrorWindow("Wrong code name");
        }
    }
}
