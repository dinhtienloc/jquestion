package vn.locdt.event;

import vn.locdt.element.question.Question;

public abstract class ConsoleEvent {
    protected String value;
    protected Question firedQuestion;

    public String getValue() {
        return value;
    }

    public Question getFiredQuestion() {
        return firedQuestion;
    }
}
