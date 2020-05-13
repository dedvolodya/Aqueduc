package ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import program.CodeResolver;
import utils.Code;

import java.util.HashSet;
import java.util.concurrent.FutureTask;

import static ui.WindowManager.showCodeWindow;
import static ui.WindowManager.showErrorWindow;

public abstract class SearchCodeAbstractController {

    @FXML protected TextField codeDistance;
    @FXML protected TextField bitRate;
    @FXML protected Label computingLabel;
    @FXML protected CodeResolver resolver;
    @FXML protected FutureTask<HashSet<Code>> futureTask;

    protected String codeName;

    protected Runnable printer = new Runnable() {
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
                        e.printStackTrace();
                        showErrorWindow("Unexpected error in algorithm");
                    }
                });
            }
        }
    };

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public void stopButtonClicked() {
        if (resolver != null && futureTask != null) {
            resolver.stopExecuting();
            computingLabel.setText("Result Saved");
            try {
                showCodeWindow(futureTask.get().iterator().next());
            } catch (Exception e) {
                e.printStackTrace();
                showErrorWindow("Unexpected error in algorithm");
            }
        }
    }

}
