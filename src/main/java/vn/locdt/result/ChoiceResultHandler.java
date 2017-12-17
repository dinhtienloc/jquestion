package vn.locdt.result;

import vn.locdt.item.Item;

public class ChoiceResultHandler extends ResultHandler {
    public ChoiceResultHandler(Item item, String value) {
        super(item, value);
    }

    public ChoiceResultHandler(Item item) {
        super(item, "");
    }
}
