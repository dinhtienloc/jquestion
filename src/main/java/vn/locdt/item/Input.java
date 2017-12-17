package vn.locdt.item;

import vn.locdt.result.InputResultHandler;

public class Input extends Item<InputResultHandler> {
    public Input(String title, String name, String value) {
        super(title, name, value);
    }

    public Input(String title, String name) {
        super(title, name, "");
    }
}
