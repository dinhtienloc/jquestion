package vn.locdt.question;

import vn.locdt.item.Item;
import vn.locdt.listener.ConsoleListener;
import vn.locdt.result.ResultHandler;

import java.io.IOException;

public abstract class Question<T extends Item> {
    private ConsoleListener listener;
    protected T item;

    public Question() {
    }

    public void addConsoleListener(ConsoleListener listener) {
        this.listener = listener;
    }

    public abstract void prompt() throws IOException;

    public String getValue() {
        ResultHandler result = item.getResultHandler();
        return result.getName() + ":" + result.getValue();
    }
}
