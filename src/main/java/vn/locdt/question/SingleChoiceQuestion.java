package vn.locdt.question;

import org.fusesource.jansi.Ansi;
import vn.locdt.constant.CharConstants;
import vn.locdt.exception.UndefinedQuestionException;
import vn.locdt.item.Selector;
import vn.locdt.item.SingleChoice;
import vn.locdt.answer.Answer;
import vn.locdt.util.ConsoleUtils;
import vn.locdt.util.DetectArrowKey;
import vn.locdt.reader.NonBlockConsoleReader;

import java.io.IOException;
import java.util.List;

import static org.fusesource.jansi.Ansi.ansi;

public class SingleChoiceQuestion extends Question<SingleChoice> {
    public SingleChoiceQuestion(String title, String name) {
        super();
        this.item = new SingleChoice(title, name);
        try {
            this.answer = new Answer(item);
        } catch (UndefinedQuestionException e) {
            e.printStackTrace();
        }
    }

    public SingleChoiceQuestion(String title, String name, boolean isPrintedResult) {
        this(title, name);
        this.isPrintedResult = isPrintedResult;
    }

    public SingleChoiceQuestion(String title, String name, List<Selector> selectors, boolean isPrintedResult) throws IOException {
        this(title, name, isPrintedResult);
        this.item.setChoiceList(selectors);
    }

    public SingleChoiceQuestion(String title, String name, List<Selector> selectors) {
        this(title, name);
        this.item.setChoiceList(selectors);
    }

    public SingleChoiceQuestion addSelector(String value)  {
        item.addSelector(new Selector(value));
        return this;
    }

    public SingleChoiceQuestion addSelector(String value, boolean isActive) {
        Selector selector = new Selector(value);
        item.addSelector(selector);
        if (isActive)
            item.setActivedSelector(selector);
        return this;
    }

    public SingleChoiceQuestion addSelectors(List<Selector> selectors) {
        item.addSelectors(selectors);
        return this;
    }

    public SingleChoiceQuestion addSelectors(String... values) {
        item.addSelectors(values);
        return this;
    }

    @Override
    public Answer prompt() throws IOException {
        System.out.println(ansi().fg(Ansi.Color.DEFAULT).a(this));

        if (item.getChoiceList().size() == 0)
            this.setAnswer("");

        NonBlockConsoleReader.start(event -> {
            int charCode = (int)event.getAddedChar();
//                System.out.println(charCode);

            if (charCode == CharConstants.CHAR_ENTER) {
                this.setAnswer(item.getActivedSelector().getValue());
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

        return this.answer;
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
