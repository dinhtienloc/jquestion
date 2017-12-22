package vn.locdt.result;

import vn.locdt.eception.AmbiguousAnswerException;
import vn.locdt.item.Input;
import vn.locdt.item.Item;

public class Answer {
    private Item sourceItem;
    private String value;

    public Answer(Item sourceItem) throws AmbiguousAnswerException {
        if (sourceItem == null)
            throw new AmbiguousAnswerException("Can't determine answer for undefined question.");
        this.sourceItem = sourceItem;
        this.value = "";
    }

    public Answer(Item sourceItem, String value) throws AmbiguousAnswerException {
        if (sourceItem == null)
            throw new AmbiguousAnswerException("Can't determine answer for undefined question.");

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
