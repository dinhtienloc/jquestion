package vn.locdt.question;

import vn.locdt.item.Item;
import vn.locdt.answer.Answer;
import vn.locdt.listener.InputListener;

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

    public boolean isPrintedResult() {
        return isPrintedResult;
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
