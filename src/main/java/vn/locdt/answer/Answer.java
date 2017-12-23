package vn.locdt.answer;

import vn.locdt.exception.UndefinedQuestionException;
import vn.locdt.item.Item;

public class Answer {
    private Item sourceItem;
    private String value;

    public Answer(Item sourceItem) throws UndefinedQuestionException {
        if (sourceItem == null)
            throw new UndefinedQuestionException("Can't determine answer for undefined question.");
        this.sourceItem = sourceItem;
        this.value = "";
    }

    public Answer(Item sourceItem, String value) throws UndefinedQuestionException {
        if (sourceItem == null)
            throw new UndefinedQuestionException("Can't determine answer for undefined question.");

        this.sourceItem = sourceItem;
        this.value = value;
    }

    public String getName() {
        return sourceItem.getName();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{\""+ sourceItem.getName() + ":" + "\"" + value + "\"}";
    }
}
