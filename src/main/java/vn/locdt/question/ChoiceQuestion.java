package vn.locdt.question;

import org.fusesource.jansi.Ansi;
import vn.locdt.constant.CharConstants;
import vn.locdt.eception.EmptySelectorListException;
import vn.locdt.item.Choice;
import vn.locdt.item.Selector;
import vn.locdt.result.ChoiceResultHandler;
import vn.locdt.result.InputResultHandler;
import vn.locdt.result.ResultHandler;
import vn.locdt.util.ConsoleUtils;
import vn.locdt.util.DetectArrowKey;
import vn.locdt.reader.NonBlockConsoleReader;

import java.io.IOException;
import java.util.List;

import static org.fusesource.jansi.Ansi.ansi;

public class ChoiceQuestion extends Question<Choice> {
    public ChoiceQuestion(String title, String name, boolean isPrintedResult) {
        super(isPrintedResult);
        this.item = new Choice(title, name);
    }

    public ChoiceQuestion(String title, String name) throws IOException {
        super();
        this.item = new Choice(title, name);
    }

    public ChoiceQuestion(String title, String name, List<Selector> selectors, boolean isPrintedResult) throws IOException {
        super(isPrintedResult);
        this.item.setChoiceList(selectors);
        this.isPrintedResult = isPrintedResult;
        prompt();
    }

    public ChoiceQuestion(String title, String name, List<Selector> selectors) throws IOException {
        super();
        this.item.setChoiceList(selectors);
        this.isPrintedResult = isPrintedResult;
        prompt();
    }

    public ChoiceQuestion addSelector(String value)  {
        item.addSelector(new Selector(value));
        return this;
    }

    public ChoiceQuestion addSelector(String value, boolean isActive) {
        Selector selector = new Selector(value);
        item.addSelector(selector);
        if (isActive)
            item.setActivedSelector(selector);
        return this;
    }

    public ChoiceQuestion addSelectors(List<Selector> selectors) {
        item.addSelectorList(selectors);
        return this;
    }

    @Override
    public ResultHandler prompt() throws IOException {
        System.out.println(ansi().fg(Ansi.Color.DEFAULT).a(this));

        if (item.getChoiceList().size() == 0)
            return new ChoiceResultHandler(item);

        NonBlockConsoleReader.start(event -> {
            int charCode = (int)event.getAddedChar();
//                System.out.println(charCode);

            if (charCode == CharConstants.CHAR_ENTER) {
                item.setResultHandler(new ChoiceResultHandler(item, item.getActivedSelector().getValue()));
                if (this.isPrintedResult) ConsoleUtils.printResult(this);
                event.cancelLoop();
            }
            else if (charCode == 27 && !DetectArrowKey.detecting) {
                DetectArrowKey.detect();
            }
            else if (DetectArrowKey.detecting) {
                CharConstants.ArrowKey arrowKey = DetectArrowKey.update(charCode);
                if (arrowKey != null) {
                    changeActiveSelector(arrowKey);
                }
            }
            else if(event.getAddedChar() == CharConstants.CHAR_CTRL_D)
                event.cancelLoop();
        });

        ChoiceResultHandler resultHandler = item.getResultHandler();
        return resultHandler == null ? new ChoiceResultHandler(item) : resultHandler;
    }

    private void changeActiveSelector(CharConstants.ArrowKey arrowKey) {
        int cursor;
        List<Selector> selectors = item.getChoiceList();
        switch (arrowKey) {
            case UP:
                cursor = item.indexOfActivedSelector();
                if (cursor > 0)
                    item.setActivedSelector(selectors.get(cursor - 1));
                ConsoleUtils.rerenderChoiceQuestion(this);
                break;
            case DOWN:
                cursor = item.indexOfActivedSelector();
                if (cursor < selectors.size() - 1)
                    item.setActivedSelector(selectors.get(cursor + 1));
                ConsoleUtils.rerenderChoiceQuestion(this);
                break;
        }
    }

    @Override
    public String toString() {
        String str = "";
        str += item.getTitle() + "\n";
        List<Selector> selectors = item.getChoiceList();
        if (selectors.size() == 0)
            return str;

        if (item.getActivedSelector() == null)
            item.setActivedSelector(item.getChoiceList().get(0));

        for (Selector selector : selectors) {
            str += ConsoleUtils.printSelector(selector) + "\n";
        }

        return str;
    }
}
