package vn.locdt.jquestion.answer;

import vn.locdt.jquestion.element.item.Item;
import vn.locdt.jquestion.element.question.Question;
import vn.locdt.jquestion.exception.UndefinedQuestionException;

import java.util.Collection;
import java.util.stream.Collectors;

public class Answer<T> {
    private Question question;
    private T result;

    public Answer(Question question) throws UndefinedQuestionException {
        if (question == null)
            throw new UndefinedQuestionException("Can't determine answer for undefined question.");
        this.question = question;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        if (result instanceof Collection) {
            return ((Collection)result).stream()
                    .collect(Collectors.joining(",", "[", "]")).toString();
        }
        else return result.toString();
    }
}
