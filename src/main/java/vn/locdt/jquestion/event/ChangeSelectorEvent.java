package vn.locdt.jquestion.event;

import vn.locdt.jquestion.element.item.Selector;

public class ChangeSelectorEvent extends ChoiceEvent {
    private Selector previousSelector;
    private Selector chosenSelector;

    public ChangeSelectorEvent(Selector previousSelector, Selector chosenSelector) {
        this.previousSelector = previousSelector;
        this.chosenSelector = chosenSelector;
    }

    public Selector getPreviousSelector() {
        return previousSelector;
    }

    public void setPreviousSelector(Selector previousSelector) {
        this.previousSelector = previousSelector;
    }

    public Selector getChosenSelector() {
        return chosenSelector;
    }

    public void setChosenSelector(Selector chosenSelector) {
        this.chosenSelector = chosenSelector;
    }
}
