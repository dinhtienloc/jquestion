package vn.locdt.item;

public class Input extends Item {
    public Input(String title, String name, String value) {
        super(title, name, value);
        this.setRenderHeight(2);
    }

    public Input(String title, String name) {
        this(title, name, "");
    }
}
