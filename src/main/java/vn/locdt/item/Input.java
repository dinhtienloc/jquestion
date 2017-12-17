package vn.locdt.item;

import vn.locdt.result.InputResultHandler;

public class Input extends Item<InputResultHandler> {
    public Input(String title, String name, String value) {
        super(title, name, value);
        this.setRenderHeight(1);
    }

    public Input(String title, String name) {
        this(title, name, "");
    }
}
