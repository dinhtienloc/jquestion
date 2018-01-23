package vn.locdt.jquestion.element.question;

import jline.console.ConsoleReader;
import vn.locdt.jquestion.JQuestion;
import vn.locdt.jquestion.event.InputEvent;
import vn.locdt.jquestion.exception.ConsoleNotInitializeException;
import vn.locdt.jquestion.exception.UndefinedQuestionException;
import vn.locdt.jquestion.element.item.Input;
import vn.locdt.jquestion.answer.Answer;
import vn.locdt.jquestion.listener.InputListener;
import vn.locdt.jquestion.util.ConsoleUtils;

import java.io.IOException;

public class InputQuestion extends Question implements InputListener{

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
        String result = console.readLine(item.getTitle() + " ");
        return onInput(new InputEvent(result));
    }

    @Override
    public Answer onInput(InputEvent e) {
        setAnswer(e.getInputValue());
        if (this.isPrintedResult) ConsoleUtils.printResult(this);
        return getAnswer();
    }
}
