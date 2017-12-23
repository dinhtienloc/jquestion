package vn.locdt.event;

import vn.locdt.item.Selector;

public class ChangeSelectorEvent {
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
