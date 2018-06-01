package vn.locdt.jquestion.event;

import vn.locdt.jquestion.element.item.Selector;

public class ChooseSelectorEvent extends ChoiceEvent {
    private Selector selector;

    public ChooseSelectorEvent(Selector selector) {
        this.selector = selector;
    }

    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }
}
