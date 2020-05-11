package ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import program.CodeResolver;
import utils.Code;


import java.util.HashSet;
import java.util.concurrent.FutureTask;

import static ui.Utils.showCodeWindow;
import static ui.Utils.showErrorWindow;

public class NewSearchController {

    @FXML private Button startButton;
    @FXML private Button stopButton;
    @FXML private TextField bitRate;
    @FXML private TextField errorNumber;
    @FXML private Label computingLabel;
    private CodeResolver resolver;
    private FutureTask<HashSet<Code>> futureTask;

    private Runnable printer = new Runnable() {
        @Override
        public void run() {
            while(!futureTask.isDone()) {
            }
            if (!resolver.wasInterrupted()) {
                Platform.runLater(() -> {
                    computingLabel.setText("Computing finished.");
                    try {
                        showCodeWindow(futureTask.get().iterator().next());
                    } catch (Exception e) {
                        showErrorWindow("Unexpected error in algorithm");
                    }
                });
            }
        }
    };

    public void startButtonClicked() {
        try {
            int bitRate = Integer.parseInt(this.bitRate.getText());
            int errorNumber = Integer.parseInt(this.errorNumber.getText());
            computingLabel.setText("computing in process...");
            resolver = new CodeResolver("code-name").findNewCodes(bitRate, errorNumber);
            futureTask = new FutureTask<>(resolver);
            Thread algorithm = new Thread(futureTask);
            algorithm.start();
            new Thread(printer).start();
        } catch (NumberFormatException e) {
            System.err.println(e);
            showErrorWindow("Wrong input");
        }

    }

    public void stopButtonClicked() {
        if (resolver != null && futureTask != null) {
            resolver.stopExecuting();
            computingLabel.setText("Result Saved");
            try {
                showCodeWindow(futureTask.get().iterator().next());
            } catch (Exception e) {
                showErrorWindow("Unexpected error in algorithm");
            }
        }
    }
}
