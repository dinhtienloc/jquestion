package vn.locdt;

import jline.console.ConsoleReader;

import java.io.IOException;

public class JQuestion {
    private static ConsoleReader rootConsole;

    public static ConsoleReader createConsole() {
        try {
            if (JQuestion.rootConsole == null)
                JQuestion.rootConsole = new ConsoleReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JQuestion.rootConsole;
    }

    public static ConsoleReader getConsole() {
        return createConsole();
    }
}
