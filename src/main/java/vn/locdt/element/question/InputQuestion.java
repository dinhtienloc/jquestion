package vn.locdt.element.question;

import jline.console.ConsoleReader;
import vn.locdt.JQuestion;
import vn.locdt.event.InputEvent;
import vn.locdt.exception.ConsoleNotInitializeException;
import vn.locdt.exception.UndefinedQuestionException;
import vn.locdt.element.item.Input;
import vn.locdt.answer.Answer;
import vn.locdt.listener.InputListener;
import vn.locdt.util.ConsoleUtils;

import java.io.IOException;

public class InputQuestion extends Question {
    private InputListener inputListener;

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
    protected void registryListener() {
        inputListener = e -> {
            setAnswer(e.getInputValue());
            if (this.isPrintedResult) ConsoleUtils.printResult(this);
            return getAnswer();
        };
    }

    @Override
    public Answer prompt() throws IOException, ConsoleNotInitializeException {
        ConsoleReader console = JQuestion.getConsole();
        String result = console.readLine(item.getTitle() + " ");
        return inputListener.onInput(new InputEvent(result));
    }

}
