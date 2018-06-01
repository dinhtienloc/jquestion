package vn.locdt.jquestion;

import jline.console.ConsoleReader;
import vn.locdt.jquestion.answer.Answer;
import vn.locdt.jquestion.element.question.*;
import vn.locdt.jquestion.exception.ConsoleNotInitializeException;
import vn.locdt.jquestion.exception.ContainerNotInitializeException;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JQuestion {
    private static ConsoleReader console;
    static {
        try {
            console = new ConsoleReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static InputQuestion input(String title) {
        return new InputQuestion(title);
    }

    public static ConfirmQuestion confirm(String title) {
        return new ConfirmQuestion(title);
    }

    public static MaskInputQuestion maskInput(String title) {
        return new MaskInputQuestion(title);
    }

    public static SingleChoiceQuestion singleChoice(String title) {
        return new SingleChoiceQuestion(title);
    }

    public static MultipleChoiceQuestion multipleChoice(String title) {
        return new MultipleChoiceQuestion(title);
    }

    public static ConsoleReader getConsole() throws ConsoleNotInitializeException {
        if (console == null) throw new ConsoleNotInitializeException("Console is not initialized.");
        return console;
    }
}
