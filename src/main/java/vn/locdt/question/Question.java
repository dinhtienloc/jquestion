package vn.locdt.question;

import vn.locdt.item.Item;
import vn.locdt.result.Answer;

import java.io.IOException;

public abstract class Question<T extends Item> {
    protected T item;
    protected boolean isPrintedResult = true;
    protected Answer answer;

    public Question() {
    }

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

    public abstract Answer prompt() throws IOException;

    public Answer getAnswer() {
        return this.answer;
    }

    public String getAnswerAsJson() {
        return answer.getName() + ":" + answer.getValue();
    }

    public String getAnswerValue() {
        return answer.getValue();
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public void setAnswer(String value) {
        this.answer.setValue(value);
    }
}
