package vn.locdt.listener;

import vn.locdt.event.ChangeSelectorEvent;
import vn.locdt.event.ChooseSelectorEvent;
import vn.locdt.question.SingleChoiceQuestion;
import vn.locdt.util.ConsoleUtils;

public class ChoiceListenerImpl implements ChoiceListener {
    private SingleChoiceQuestion question;

    public ChoiceListenerImpl(SingleChoiceQuestion question) {
        this.question = question;
    }

    @Override
    public void onChanged(ChangeSelectorEvent e) {
        ConsoleUtils.rerenderChoiceQuestion(question);
    }

    @Override
    public void onChosen(ChooseSelectorEvent e) {
        question.setAnswer(e.getSelector().getValue());
        if (question.isPrintedResult()) ConsoleUtils.printResult(question);
    }
}
