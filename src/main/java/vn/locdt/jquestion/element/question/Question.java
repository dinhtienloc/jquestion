package vn.locdt.jquestion.element.question;

import vn.locdt.jquestion.element.RenderElement;
import vn.locdt.jquestion.exception.ConsoleNotInitializeException;
import vn.locdt.jquestion.element.item.Item;
import vn.locdt.jquestion.answer.Answer;

import java.io.IOException;

public abstract class Question<T extends Item> extends RenderElement {
    protected T item;
    protected boolean isPrintedResult = true;
    protected Answer answer;

    public Question() {}

    public Question(boolean isPrintedResult) {
        this.isPrintedResult = isPrintedResult;
    }

    public T getItem() {
        return item;
    }

    public boolean isPrintedResult() {
        return isPrintedResult;
    }

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
