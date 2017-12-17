package vn.locdt.question;

import vn.locdt.JQuestion;
import vn.locdt.item.Input;
import vn.locdt.item.Item;
import vn.locdt.result.InputResultHandler;
import vn.locdt.util.ConsoleUtils;

import java.io.IOException;

public class InputQuestion extends Question<Input> {
    public InputQuestion(String title, String name) throws IOException {
        super();
        this.item = new Input(title, name);
        prompt();
    }

    public void setContext(String title, String name) {
        this.item = new Input(title, name);
    }

    @Override
    public void prompt() throws IOException {
        //TODO: process prompt here
        String result = JQuestion.getConsole().readLine(item.getTitle());
        item.setResultHandler(new InputResultHandler(item, result));
        ConsoleUtils.printResult(this);
    }

}
