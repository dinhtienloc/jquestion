package vn.locdt.question;

import jline.console.ConsoleReader;
import vn.locdt.JQuestion;
import vn.locdt.exception.ConsoleNotInitializeException;
import vn.locdt.exception.UndefinedQuestionException;
import vn.locdt.item.Input;
import vn.locdt.answer.Answer;
import vn.locdt.util.ConsoleUtils;

import java.io.Console;
import java.io.IOException;

public class InputQuestion extends Question {
    public InputQuestion(String title, String name, boolean isPrintedResult) throws IOException {
        super(isPrintedResult);
        this.item = new Input(title, name);
        try {
            this.answer = new Answer(item);
        } catch (UndefinedQuestionException e) {
            e.printStackTrace();
        }
    }

    public InputQuestion(String title, String name) {
        super();
        this.item = new Input(title, name);
        try {
            this.answer = new Answer(item);
        } catch (UndefinedQuestionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Answer prompt() throws IOException, ConsoleNotInitializeException {
        ConsoleReader console = JQuestion.getConsole();
        String result = console.readLine(item.getTitle() + "\n");
        this.setAnswer(result);
        if (this.isPrintedResult) ConsoleUtils.printResult(this);
        return this.getAnswer();
    }

}
