package utils;


import java.io.IOException;
import java.util.Scanner;

public class KeyPressThread extends Thread {

    private Scanner inputReader = new Scanner(System.in);
    private String stopKey;
    private volatile boolean exit = false;

    public KeyPressThread(String stopKey) {
        this.stopKey = stopKey;
    }

    public void run() {
        try {
            while (!exit) {
                if (System.in.available() != 0 && inputReader.hasNext()) {
                    String input = inputReader.next();
                    if (input.equalsIgnoreCase(stopKey)) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.print("KeyPressThread::run error :");
            e.printStackTrace();
        }
    }

    public void exit() {
        exit = true;
    }

}
