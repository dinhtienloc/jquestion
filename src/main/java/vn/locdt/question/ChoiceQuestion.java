package vn.locdt.question;

import vn.locdt.eception.EmptySelectorListException;
import vn.locdt.event.CharacterInputEvent;
import vn.locdt.event.ChoiceChangeEvent;
import vn.locdt.item.Choice;
import vn.locdt.item.Selector;
import vn.locdt.util.CharConstants;
import vn.locdt.util.DetectArrowKey;
import vn.locdt.util.NonBlockConsoleReader;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class ChoiceQuestion extends Question<Choice> {
    public ChoiceQuestion(String title, String name) {
        super();
        this.item = new Choice(title, name);
    }

    public ChoiceQuestion addSelector(String value) {
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
    public void prompt() throws IOException {
        try {
            showChoiceList();
            NonBlockConsoleReader.start(event -> {
                int charCode = (int)event.getAddedChar();
//                System.out.println(charCode);
                if (charCode == 27 && !DetectArrowKey.detectArrowKey) {
                    DetectArrowKey.detect();
                }
                else if (DetectArrowKey.detectArrowKey) {
                    CharConstants.ArrowKey arrowKey = DetectArrowKey.update(charCode);
                    if (arrowKey != null) {
                        changeActiveSelector(arrowKey);

                    }
                }
//                System.out.println("buffer: " + event.getCurrentBuffer());
                if(event.getAddedChar() == CharConstants.CHAR_CTRL_D)
                    event.cancelLoop();
            });
        } catch (EmptySelectorListException e) {
            e.printStackTrace();
        }
    }

    private void changeActiveSelector(CharConstants.ArrowKey arrowKey) {
        try {
            int cursor;
            List<Selector> selectors = item.getChoiceList();
            switch (arrowKey) {
                case UP:
                    cursor = item.indexOfActivedSelector();
                    if (cursor > 0)
                        item.setActivedSelector(selectors.get(cursor - 1));
                    showChoiceList();
                    break;
                case DOWN:
                    cursor = item.indexOfActivedSelector();
                    if (cursor < selectors.size() - 1)
                        item.setActivedSelector(selectors.get(cursor + 1));
                    showChoiceList();
                    break;
            }
        } catch (EmptySelectorListException e) {
            e.printStackTrace();
        }
    }
    private void showChoiceList() throws EmptySelectorListException {
        System.out.println(item.getTitle());
        List<Selector> selectors = item.getChoiceList();
        if (selectors.size() == 0)
            throw new EmptySelectorListException("Choice list is empty");

        if (item.getActivedSelector() == null)
            item.setActivedSelector(item.getChoiceList().get(0));

        for (Selector selector : selectors) {
            System.out.println(selector);
        }
    }


}
