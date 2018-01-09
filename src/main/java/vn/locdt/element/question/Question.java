package vn.locdt.element.question;

import vn.locdt.element.RenderElement;
import vn.locdt.exception.ConsoleNotInitializeException;
import vn.locdt.element.item.Item;
import vn.locdt.answer.Answer;

import java.io.IOException;

public abstract class Question<T extends Item> extends RenderElement {
    protected T item;
    protected boolean isPrintedResult = true;
    protected Answer answer;

    public Question() {
        registryListener();
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

    protected abstract void registryListener();
    public abstract Answer prompt() throws IOException, ConsoleNotInitializeException;

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

    @Override
    public void updateRenderHeight() {
        setRenderHeight(getItem().getRenderHeight());
    }
}
