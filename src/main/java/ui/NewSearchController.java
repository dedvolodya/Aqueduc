package ui;

import program.CodeResolver;


import java.util.concurrent.FutureTask;

import static ui.WindowManager.showCodeWindow;
import static ui.WindowManager.showErrorWindow;

public class NewSearchController extends SearchCodeAbstractController {

    public void startButtonClicked() {
        try {
            int bitRate = Integer.parseInt(this.bitRate.getText());
            int errorNumber = Integer.parseInt(this.codeDistance.getText());
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
}
