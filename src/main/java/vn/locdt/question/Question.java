package vn.locdt.question;

import vn.locdt.item.Item;
import vn.locdt.result.ResultHandler;

import java.io.IOException;

public abstract class Question<T extends Item> {
    protected T item;
    protected boolean isPrintedResult = false;

    public Question() {}

    public Question(boolean isPrintedResult) {
        this.isPrintedResult = isPrintedResult;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public boolean isPrintedResult() {
        return isPrintedResult;
    }

    public void setPrintedResult(boolean printedResult) {
        isPrintedResult = printedResult;
    }

    public abstract ResultHandler prompt() throws IOException;

    public String getValue() {
        ResultHandler result = item.getResultHandler();
        return result.getName() + ":" + result.getValue();
    }

    public ResultHandler result() {
        return item.getResultHandler();
    }
}
