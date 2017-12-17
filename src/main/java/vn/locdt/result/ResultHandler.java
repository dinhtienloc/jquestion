package vn.locdt.result;

import vn.locdt.item.Input;
import vn.locdt.item.Item;

public class ResultHandler {
    protected Item sourceItem;
    protected String value;

    public ResultHandler(Item sourceItem, String value) {
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
}
