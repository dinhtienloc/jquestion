package vn.locdt.question;

import vn.locdt.item.Item;
import vn.locdt.result.ResultHandler;

import java.io.IOException;

public abstract class Question<T extends Item> {
    protected T item;

    public Question() {
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public abstract void prompt() throws IOException;

    public String getValue() {
        ResultHandler result = item.getResultHandler();
        return result.getName() + ":" + result.getValue();
    }
}
