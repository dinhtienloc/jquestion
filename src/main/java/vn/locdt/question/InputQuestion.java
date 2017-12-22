package vn.locdt.question;

import vn.locdt.JQuestion;
import vn.locdt.exception.AmbiguousAnswerException;
import vn.locdt.item.Input;
import vn.locdt.answer.Answer;
import vn.locdt.util.ConsoleUtils;

import java.io.IOException;

public class InputQuestion extends Question {
    public InputQuestion(String title, String name, boolean isPrintedResult) throws IOException {
        super(isPrintedResult);
        this.item = new Input(title, name);
        try {
            this.answer = new Answer(item);
        } catch (AmbiguousAnswerException e) {
            e.printStackTrace();
        }
    }

    public InputQuestion(String title, String name) {
        super();
        this.item = new Input(title, name);
        try {
            this.answer = new Answer(item);
        } catch (AmbiguousAnswerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Answer prompt() throws IOException {
        String result = JQuestion.getConsole().readLine(item.getTitle() + "\n");
        this.setAnswer(result);
        if (this.isPrintedResult) ConsoleUtils.printResult(this);
        return this.getAnswer();
    }

}
