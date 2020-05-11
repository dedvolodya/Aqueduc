package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import program.CodeResolver;


import static ui.Utils.showCodeWindow;
import static ui.Utils.showErrorWindow;

public class NewSearchController {

    @FXML private Button startButton;
    @FXML private Button stopButton;
    @FXML private TextField bitRate;
    @FXML private TextField errorNumber;
    @FXML private Label computingLabel;

    public void startButtonClicked() {
        try {
            int bitRate = Integer.parseInt(this.bitRate.getText());
            int errorNumber = Integer.parseInt(this.errorNumber.getText());
            CodeResolver cr = new CodeResolver("code-name");
            computingLabel.setText("computing in process...");
            showCodeWindow(cr.findNewCodes(bitRate, errorNumber).iterator().next());
        } catch (NumberFormatException e) {
            System.err.print(e);
            showErrorWindow("Wrong input");
        }

    }

    public void stopButtonClicked() {
        computingLabel.setText("Results Saved");
    }
}
