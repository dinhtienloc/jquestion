package vn.locdt.question;

import vn.locdt.JQuestion;
import vn.locdt.item.Input;
import vn.locdt.item.Item;
import vn.locdt.result.InputResultHandler;
import vn.locdt.result.ResultHandler;
import vn.locdt.util.ConsoleUtils;

import java.io.IOException;

public class InputQuestion extends Question<Input> {
    public InputQuestion(String title, String name, boolean isPrintedResult) throws IOException {
        super(isPrintedResult);
        this.item = new Input(title, name);
    }

    public InputQuestion(String title, String name) throws IOException {
        super();
        this.item = new Input(title, name);
    }

    public void setContext(String title, String name) {
        this.item = new Input(title, name);
    }

    @Override
    public ResultHandler prompt(){
        try {
            String result = JQuestion.getConsole().readLine(item.getTitle());
            InputResultHandler resultHandler = new InputResultHandler(item, result);
            item.setResultHandler(resultHandler);
            if (this.isPrintedResult) ConsoleUtils.printResult(this);
            return item.getResultHandler();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
