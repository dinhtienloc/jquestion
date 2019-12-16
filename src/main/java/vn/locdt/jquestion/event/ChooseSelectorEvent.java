package vn.locdt.jquestion.event;

import vn.locdt.jquestion.element.item.choice.Selector;

public class ChooseSelectorEvent {
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
