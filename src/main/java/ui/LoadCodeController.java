package ui;

import program.CodeResolver;

import java.util.concurrent.FutureTask;

import static ui.WindowManager.showCodeWindow;
import static ui.WindowManager.showErrorWindow;

public class LoadCodeController extends SearchCodeAbstractController {


    public void startButtonClicked() {
        try {
            computingLabel.setText("computing in process...");
            resolver = new CodeResolver("code-name").loadAndContinueSearch();
            futureTask = new FutureTask<>(resolver);
            Thread algorithm = new Thread(futureTask);
            this.bitRate.setText(String.valueOf(resolver.getGraph().getBitRate()));
            this.codeDistance.setText(String.valueOf(resolver.getGraph().getDistance()));
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
                e.printStackTrace();
                showErrorWindow("Unexpected error in algorithm");
            }
        }
    }
}
