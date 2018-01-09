package vn.locdt.element.question;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import vn.locdt.constant.VKConstants;
import vn.locdt.event.ChangeSelectorEvent;
import vn.locdt.event.ChooseSelectorEvent;
import vn.locdt.event.NonBlockInputEvent;
import vn.locdt.exception.ConsoleNotInitializeException;
import vn.locdt.exception.UndefinedQuestionException;
import vn.locdt.element.item.Selector;
import vn.locdt.element.item.SingleChoice;
import vn.locdt.answer.Answer;
import vn.locdt.listener.ChoiceListener;
import vn.locdt.listener.ChoiceListenerImpl;
import vn.locdt.listener.NonBlockInputListener;
import vn.locdt.util.ConsoleUtils;
import vn.locdt.util.DetectArrowKey;
import vn.locdt.util.RawConsoleInput;

import java.io.IOException;
import java.util.List;

import static org.fusesource.jansi.Ansi.ansi;

public class SingleChoiceQuestion extends Question<SingleChoice> {
    private ChoiceListener choiceListener;
    private NonBlockInputListener nonBlockInputListener;

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
        updateRenderHeight();
    }

    public SingleChoiceQuestion(String title, String name, List<Selector> selectors, boolean isPrintedResult) throws IOException {
        this(title, name, isPrintedResult);
        this.item.setChoiceList(selectors);
        updateRenderHeight();
    }

    public SingleChoiceQuestion(String title, String name, List<Selector> selectors) {
        this(title, name);
        this.item.setChoiceList(selectors);
        updateRenderHeight();
    }

    private void registryListener() {
        choiceListener = new ChoiceListenerImpl(this);
        nonBlockInputListener = e -> {
            int charCode = e.getAddedChar();
//            System.out.println(charCode);
            return handleInput(charCode, e);
        };
    }

    public SingleChoiceQuestion addSelector(String value)  {
        item.addSelector(new Selector(value));
        updateRenderHeight();
        return this;
    }

    public SingleChoiceQuestion addSelector(String value, boolean isActive) {
        Selector selector = new Selector(value);
        item.addSelector(selector);
        if (isActive)
            item.setActivedSelector(selector);

        updateRenderHeight();
        return this;
    }

    public SingleChoiceQuestion addSelectors(List<Selector> selectors) {
        item.addSelectors(selectors);
        updateRenderHeight();
        return this;
    }

    public SingleChoiceQuestion addSelectors(String... values) {
        item.addSelectors(values);
        updateRenderHeight();
        return this;
    }

    @Override
    public Answer prompt() throws IOException, ConsoleNotInitializeException {
        registryListener();
        AnsiConsole.out.print(ansi().fg(Ansi.Color.DEFAULT).a(this));

        if (item.getChoiceList().size() == 0)
            this.setAnswer("");

        // read input
        int input;
        boolean stop;
        while (true) {
            input = RawConsoleInput.read(true);
            stop = nonBlockInputListener.onInput(new NonBlockInputEvent(input));
            if (stop) break;
        }

        RawConsoleInput.resetConsoleMode();
        return this.answer;
    }

    private void changeActiveSelector(VKConstants.ArrowKey arrowKey) {
        int cursor = item.indexOfActivedSelector();
        Selector lastSelector = item.getActivedSelector();
        Selector nextSelector;

        List<Selector> selectors = item.getChoiceList();
        switch (arrowKey) {
            case VK_UP:
                if (cursor > 0) cursor--;
                break;
            case VK_DOWN:
                if (cursor < selectors.size() - 1) cursor++;
                break;
        }

        nextSelector = selectors.get(cursor);
        if (nextSelector != lastSelector) {
            item.setActivedSelector(nextSelector);
            choiceListener.onChanged(new ChangeSelectorEvent(lastSelector, nextSelector));
        }
    }

    private boolean handleInput(int charCode, NonBlockInputEvent e) {
        if (charCode == VKConstants.VK_ENTER) {
            choiceListener.onChosen(new ChooseSelectorEvent(item.getActivedSelector()));
            e.stop();
        } else if (charCode == 27 && !DetectArrowKey.detecting) {
            DetectArrowKey.detect();
        } else if (DetectArrowKey.detecting) {
            VKConstants.ArrowKey arrowKey = DetectArrowKey.update(charCode);
            if (arrowKey != null)
                changeActiveSelector(arrowKey);
        } else if (e.getAddedChar() == VKConstants.VK_CTRL_D) {
            e.stop();
        }
        else if (ConsoleUtils.isWindowOS()) {
            handleWindowInput(charCode, e);
        }

        return e.isStop();
    }

    private void handleWindowInput(int charCode, NonBlockInputEvent e) {
        VKConstants.ArrowKey arrowKey = null;
        switch (charCode) {
            case VKConstants.WindowOS.VK_DOWN:
                arrowKey = VKConstants.ArrowKey.VK_DOWN;
                break;
            case VKConstants.WindowOS.VK_UP:
                arrowKey = VKConstants.ArrowKey.VK_UP;
                break;
            case VKConstants.WindowOS.VK_LEFT:
                arrowKey = VKConstants.ArrowKey.VK_LEFT;
                break;
            case VKConstants.WindowOS.VK_RIGHT:
                arrowKey = VKConstants.ArrowKey.VK_RIGHT;
                break;
        }

        if (arrowKey != null)
            changeActiveSelector(arrowKey);
    }

    @Override
    public String toString() {
        String str = item.getTitle() + "\n";
        List<Selector> selectors = item.getChoiceList();
        if (selectors.size() == 0)
            return str;

        if (item.getActivedSelector() == null)
            item.setActivedSelector(item.getChoiceList().get(0));

        for (Selector selector : selectors) {
            str += ConsoleUtils.printSelector(selector);
            if (selectors.indexOf(selector) < selectors.size() - 1)
                str += "\n";
        }

        return str;
    }
}
